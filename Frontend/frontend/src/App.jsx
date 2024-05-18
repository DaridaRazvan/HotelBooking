import './App.css'
import Search from "./pages/Search.jsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HotelDetails from "./pages/HotelDetails.jsx";
import Reservations from "./pages/Reservations.jsx";

function App() {
//npm run dev
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route exact path="/" element={<Search/>}/>
                    <Route exact path="/hotel/:id" element={<HotelDetails/>}/>
                    <Route exact path="/reservations" element={<Reservations/>}/>
                </Routes>

            </BrowserRouter>
        </>
    )
}

export default App
