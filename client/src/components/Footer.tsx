import React from 'react';
import VkIcon from "../icons/VkIcon";
import OkIcon from "../icons/OkIcon";
import TgIcon from "../icons/TgIcon";
import RssIcon from "../icons/RssIcon";

const Footer = () => {
    return (
        <div className="absolute bottom-0 left-0 right-0 h-32 bg-gray-300">
            <div className="absolute left-0 p-6">
                <p className="text-base select-none">Минкультуры России в социальных сетях:</p>
                <div className="flex justify-between mt-4">
                    <VkIcon/>
                    <OkIcon/>
                    <TgIcon/>
                    <RssIcon/>
                </div>
            </div>
        </div>
    );
};

export default Footer;