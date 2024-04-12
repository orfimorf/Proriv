import React from 'react';
import UploadImage from "./UploadImage";
import Button from "@mui/material/Button";

const UploadFileContainer = () => {
    return (
        <div className="bg-gray-300 h-60 mt-10">
            <div className="flex justify-center flex-col items-center relative h-64">
                <UploadImage/>
                <div className="my-3">
                    <Button  variant="outlined">Поиск</Button>
                </div>
            </div>
        </div>
    );
};

export default UploadFileContainer;