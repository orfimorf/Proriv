import MuseumService from "../services/MuseumService";

export default class MuseumStore {
    private image: string;

    constructor() {
        this.image = ''
    }

    async sendImageForSearch(formData: FormData){
        await MuseumService.postImageForSearch(formData)
    }
}