import * as React from 'react';
import {styled} from '@mui/material/styles';
import Button from '@mui/material/Button';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import {useContext, useState} from "react";
import {Context} from "../index";

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
    const [load, setLoad] = useState<boolean>(true)
    const [imageDisplay, setImageDisplay] = useState<string>('')

    const handleUpload = (e: any) => {
        if (e.target.files && e.target.files[0]) {
            setImage(e.target.files[0])
            setImageDisplay(URL.createObjectURL(e.target.files[0]))
            setLoad(false)
        }
    }

    const museum = useContext(Context)
    const sendImage = async () => {
        if (museum) {
            try {
                const formData = new FormData()
                formData.append('image', image)
                const response = await museum.sendImageForSearch(formData)

                museum.setMuseumItems(response)

            } catch (e) {
                console.log(e)
            }
        }
    }

    return (
        <>
            <img className="h-28" src={imageDisplay}  alt={"Загруженная картинка"}/>
            {load ?
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
                :
                <>
                </>
            }

            <div className="my-3">
                <Button disabled={load} variant="outlined" onClick={sendImage}>Поиск</Button>
            </div>
        </>
    );
}