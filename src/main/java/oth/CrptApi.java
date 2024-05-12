package oth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Servlet config:
 * <pre> {@code
 *
 *     public final CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 10);
 *
 *     @Bean
 *     public ServletRegistrationBean<CrptApi.CrptApiServlet> crptApiServletRegistrationBean() {
 *         return new ServletRegistrationBean<>(crptApi.getServlet(), CrptApi.CRPT_API_PATH);
 *     }
 *
 *     @Bean
 *     public FilterRegistrationBean<Filter> myFilterRegistrationBean() {
 *         FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
 *         registrationBean.setFilter(crptApi.getRequestFilter());
 *         registrationBean.addUrlPatterns(CrptApi.CRPT_API_PATH);
 *         registrationBean.setOrder(1);
 *         return registrationBean;
 *     }
 * } </pre>
 *
 * @author Ontheheavens
 * @since 12.05.2024
 */
public class CrptApi {

    public static final String CRPT_API_PATH = "/api/v3/lk/documents/create";

    private final RequestLimitFilter requestFilter;

    private final CrptApiServlet servlet;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.requestFilter = new RequestLimitFilter(timeUnit, requestLimit);
        this.servlet = new CrptApiServlet();
    }

    public Filter getRequestFilter() {
        return requestFilter;
    }

    public CrptApiServlet getServlet() {
        return servlet;
    }

    @SuppressWarnings({"UseOfSystemOutOrSystemErr", "MethodOnlyUsedFromInnerClass", "MethodMayBeStatic"})
    private void createDocument(CrptDocument document, String signature) {
            System.out.println("Document created: " + document.doc_type);
            System.out.println("Signature: " + signature);
    }

    @SuppressWarnings("PublicInnerClass")
    @WebServlet(CRPT_API_PATH)
    public final class CrptApiServlet extends HttpServlet {

        private final ObjectMapper objectMapper;

        private CrptApiServlet() {
            this.objectMapper = new ObjectMapper();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String signature = req.getParameter("signature");
            PrintWriter writer = resp.getWriter();

            try {
                String body = CrptApiServlet.readBody(req);
                CrptDocument document = this.deserializeDocument(body);
                CrptApi.this.createDocument(document, signature);

                resp.setContentType("text/plain");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.println("Document created");
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writer.println("Failed to create document: " + e.getMessage());
            }
        }

        private static String readBody(ServletRequest request) throws IOException {
            StringBuilder jsonPayload = new StringBuilder(10);
            try (BufferedReader reader = request.getReader()) {
                // Because while ((line = reader.readLine()) != null) is not desirable.
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    jsonPayload.append(line);
                }
            }
            return jsonPayload.toString();
        }

        @SuppressWarnings("UseOfSystemOutOrSystemErr")
        private CrptDocument deserializeDocument(String json) {
            CrptDocument document = null;
            try {
                document = objectMapper.readValue(json, CrptDocument.class);
            } catch (JsonMappingException e) {
                System.err.println("Failed to map JSON: " + e.getMessage());
            } catch (JsonProcessingException e) {
                System.err.println("Failed to process JSON: " + e.getMessage());
            }
            return document;
        }
    }

    /**
     * A record representing a CRPT document. Is not a robust approach:
     * Changes to JSON structure sent via CRPT API will break this class.
     * More error-proof way is to use Map<String, Object> deserialized with Jackson.
     */
    @SuppressWarnings({"InnerClassTooDeeplyNested", "PackageVisibleInnerClass",
            "PublicInnerClass", "ClassWithTooManyFields"})
    public record CrptDocument(
            Description description,
            String doc_id,
            String doc_status,
            String doc_type,
            boolean importRequest,
            String owner_inn,
            String participant_inn,
            String producer_inn,
            String production_date,
            String production_type,
            Product[] products,
            String reg_date,
            String reg_number
    ) {
        record Description(String participantInn) {}

        record Product(
                String certificate_document,
                String certificate_document_date,
                String certificate_document_number,
                String owner_inn,
                String producer_inn,
                String production_date,
                String tnved_code,
                String uit_code,
                String uitu_code
        ) {}
    }

    @SuppressWarnings({"UseOfSystemOutOrSystemErr"})
    private static class RequestLimitFilter implements Filter {

        private final Semaphore requestSemaphore;
        private final ScheduledExecutorService scheduler;
        private final long releaseIntervalMillis;

        private final int requestLimit;

        RequestLimitFilter(TimeUnit timeUnit, int inputRequestLimit) {
            if (inputRequestLimit <= 0) {
                throw new IllegalArgumentException("Request limit must be a positive value");
            }

            long timeFrameMillis = timeUnit.toMillis(1);
            if (timeFrameMillis <= 0) {
                throw new IllegalArgumentException("Time unit conversion must result in a positive value");
            }

            long ratio = inputRequestLimit / timeFrameMillis;
            int permits = (int) Math.max(ratio, 1);

            this.requestLimit = inputRequestLimit;
            this.requestSemaphore = new Semaphore(permits, true);
            this.scheduler = new ScheduledThreadPoolExecutor(1);
            this.releaseIntervalMillis = timeFrameMillis / inputRequestLimit;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(releaseIntervalMillis);
            String releaseInterval = "release interval: " + seconds + " seconds";
            System.out.println("Request limit set to: " + inputRequestLimit + ", " + releaseInterval);
            schedulePermitRelease();
        }

        private void schedulePermitRelease() {
            scheduler.scheduleAtFixedRate(this::releasePermit, releaseIntervalMillis,
                    releaseIntervalMillis, TimeUnit.MILLISECONDS);
            requestSemaphore.release(this.requestLimit);
        }

        private void releasePermit() {
            if (requestSemaphore.availablePermits() < this.requestLimit) {
                System.out.println("Releasing permit, available permits: " + requestSemaphore.availablePermits());
                requestSemaphore.release();
            }
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            if (requestSemaphore.tryAcquire()) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(429); // Set 429 status code: Too Many Requests.
                PrintWriter writer = httpResponse.getWriter();
                writer.write("Too Many Requests");
            }
        }

        @Override
        public void init(FilterConfig filterConfig) {}

        @Override
        public void destroy() {
            scheduler.shutdown();
        }
    }

}
