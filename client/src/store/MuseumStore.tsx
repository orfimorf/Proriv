import MuseumService from "../services/MuseumService";
import {makeAutoObservable} from "mobx";

export class Data {
    image: string[];
    exhibitName: string;
    exhibitDescription: string;
    exhibitGroup: string;

    constructor(exhibitName: string, exhibitGroup: string, exhibitDescription: string, image: string[]) {
        this.exhibitName = exhibitName
        this.exhibitGroup = exhibitGroup
        this.exhibitDescription = exhibitDescription
        this.image = image
    }
}



export default class MuseumStore {
    museumItems: Data[];

    constructor() {
        this.museumItems = []
        makeAutoObservable(this);
    }

    setMuseumItems(museumItems: Data[]) {
        this.museumItems = museumItems
    }

    get MuseumItems(){
        return this.museumItems
    }

    async sendImageForSearch(formData: FormData) {
        const images: Data[] = []

        const response = await MuseumService.postImageForSearch(formData)

        // @ts-ignore
        if (response.data == undefined) return images
        // @ts-ignore
        response.data.forEach((obj: Data) => {
            const image = new Data(obj.exhibitName, obj.exhibitGroup, obj.exhibitDescription, obj.image)
            images.push(image)
        })

        return images
    }
}