import React, {createContext} from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import MuseumStore from "./store/MuseumStore";


const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

export const Context = createContext<MuseumStore | null>(null)

root.render(
    <Context.Provider value={new MuseumStore()}>
        <App/>
    </Context.Provider>
);
