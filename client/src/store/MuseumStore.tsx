import MuseumService from "../services/MuseumService";
import {makeAutoObservable} from "mobx";
export type Data = {
    image: File;
    title: string;
    description: string;
    category: string;
}

type MuseumItems = Data[]
export default class MuseumStore {
    private museumItems: MuseumItems;
    constructor() {
        this.museumItems = []
        makeAutoObservable(this);
    }

    setMuseumItems(museumItems: MuseumItems) {
        this.museumItems = museumItems
    }
    async sendImageForSearch(formData: FormData){
       await MuseumService.postImageForSearch(formData)
    }
}