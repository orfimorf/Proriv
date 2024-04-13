import React from 'react';
import UploadImage from "./UploadImage";

const UploadFileContainer = () => {
    return (
        <div className="bg-gray-300 h-64 mt-10">
            <div className="flex justify-center flex-col items-center relative h-64">
                <UploadImage/>
            </div>
        </div>
    );
};

export default UploadFileContainer;