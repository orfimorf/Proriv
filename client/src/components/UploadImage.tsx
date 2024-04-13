import * as React from 'react';
import {styled} from '@mui/material/styles';
import Button from '@mui/material/Button';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import {useState} from "react";
import MuseumStore from "../store/MuseumStore";
import SwiperImages from "./SwiperImages";

const VisuallyHiddenInput = styled('input')({
    clipPath: 'inset(50%)',
    height: 1,
    overflow: 'hidden',
    position: 'absolute',
    bottom: 0,
    left: 0,
    whiteSpace: 'nowrap',
    width: 1,
});

export default function UploadImage() {
    const [image, setImage] = useState("")

    const handleUpload = (e: any) => {
        if (e.target.files && e.target.files[0] && e.target.files[0].type == "image/jpeg") {
            setImage(e.target.files[0])
        } else {
            alert("Не тот тип файла, нужен jpg")
        }
    }


    const sendImage = () => {
        try {
            const formData = new FormData()
            formData.append('image', image)

            const museum = new MuseumStore()

            museum.sendImageForSearch(formData)

        } catch (e) {
            console.log(e)
        }
    }

    return (
        <>
            <Button
                component="label"
                role={undefined}
                variant="contained"
                tabIndex={-1}
                startIcon={<CloudUploadIcon/>}
            >
                Загрузить картинку
                <VisuallyHiddenInput type="file"
                                     onChange={handleUpload}/>
            </Button>
            <div className="my-3">
                <Button variant="outlined" onClick={sendImage}>Поиск</Button>
            </div>
            <div className="">
                <SwiperImages/>
            </div>
        </>
    );
}