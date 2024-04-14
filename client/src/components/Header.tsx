import LogoIcon from "./LogoIcon";

const Header = () => {
    return (
        <header className="bg-gray-300 shadow-xl p-1.5">
            <div className="relative flex ms-24 h-16 items-center ">
                <LogoIcon/>
                <p className="ms-5 text-base select-none">
                    МИНИСТЕРСТВО КУЛЬТУРЫ<p>РОССИЙСКОЙ ФЕДЕРАЦИИ</p>
                </p>
            </div>
        </header>
    );
};

export default Header;