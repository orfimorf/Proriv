import {$host} from "../http";

export default class MuseumService {
    static async create(formData: FormData) {
        return new Promise((resolve) => resolve($host.post('', formData)))

    }

    static async sendImageForSearch(image: string) {
        return new Promise((resolve) => resolve($host.post('', image)))
    }
}