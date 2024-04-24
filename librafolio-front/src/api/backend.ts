import axios, {AxiosResponse} from 'axios';

const axiosApi = axios.create({
    baseURL: `/api`,
    timeout: 10000,
    // signal: AbortSignal.timeout(5000),
    headers: { 'Content-Type': 'application/json' }
});

// Add global request interceptor
axiosApi.interceptors.request.use(
    (config) => {
        console.log(config.headers)
        // Modify request config here, e.g., add headers
        return config;
    },
    (error) => {
        console.log(error.toString());
        return Promise.reject(error);
    }
);


interface Message {
    content: string;
}

export default {
    hello(): Promise<AxiosResponse<Message>> {
        let hello = axiosApi.get(`/hello`);
        console.log(hello)
        return hello;
    },
}