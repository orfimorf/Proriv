import MuseumService from "../services/MuseumService";

export default class MuseumStore {
    private image: string;

    constructor() {
        this.image = ''
    }


    async sendImageForSearch(image:string){
        const response = await MuseumService.sendImageForSearch(image)

        return response
    }

    async create(formData: FormData){
        await MuseumService.create(formData)
    }
}