import Header from "./components/Header";
import UploadFileContainer from "./components/UploadFileContainer";
import Footer from "./components/Footer";
import SwiperImages from "./components/SwiperImages";
import * as React from "react";

function App() {
    return (
        <>
            <Header/>
            <UploadFileContainer/>
            <div className="flex justify-center align-middle">
                <SwiperImages/>
            </div>
            <Footer/>
        </>
    );
}

export default App;
