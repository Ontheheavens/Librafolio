import axios, {AxiosResponse} from 'axios';

const axiosApi = axios.create({
    baseURL: `/api`,
    timeout: 10000,
    headers: { 'Content-Type': 'application/json' }
});

interface Message {
    text: string;
}

export default {
    hello(): Promise<AxiosResponse<Message>> {
        let hello = axiosApi.get(`/hello`);
        console.log(hello)
        return hello;
    },
}