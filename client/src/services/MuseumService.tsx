import {$host} from "../http";

export default class MuseumService {
    static async postImageForSearch(formData: FormData) {
        return new Promise((resolve) => resolve($host.post('api/main/analyze', formData)))
    }
}