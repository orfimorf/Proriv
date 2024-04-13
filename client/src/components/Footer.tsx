import React from 'react';
import TelegramIcon from '@mui/icons-material/Telegram';
import Button from "@mui/material/Button";
import VkIcon from "../icons/VkIcon";
import OkIcon from "../icons/OkIcon";
import TgIcon from "../icons/TgIcon";
import RssIcon from "../icons/RssIcon";

const Footer = () => {
    return (
        <div className="absolute bottom-0 left-0 right-0 h-40 bg-gray-300">
            <div className="absolute left-0 p-12">
                <p className="text-base">Минкультуры России в социальных сетях:</p>
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