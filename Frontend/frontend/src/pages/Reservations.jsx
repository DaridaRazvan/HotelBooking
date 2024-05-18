import {useEffect, useState} from "react";
import ReservationItems from "../components/ReservationItems.jsx";
import {Link} from "react-router-dom";
import classes from "./Reservations.module.css";

const Reservations = () => {
    const [reservations, setReservations] = useState([]);

    useEffect(() => {
        fetchReservations().catch((error) => {
            console.log(error);
        })
    }, []);

    const fetchReservations = async () => {
        const response = await fetch('http://localhost:8080/api/reservations/all');

        if (!response.ok) {
            throw new Error('Something went wrong!');
        }

        const responseData = await response.json();

        const loadedReservations = []
        for (const key in responseData) {
            loadedReservations.push({
                id: responseData[key].id,
                time: responseData[key].time,
                hotelId: responseData[key].hotelId,
                hotelName: responseData[key].hotelName,
                roomId: responseData[key].roomId,
                roomNumber: responseData[key].roomNumber,
                type: responseData[key].type,
                price: responseData[key].price
            })
        }
        setReservations(loadedReservations);
    }

    return <>
        <div className={classes.header}>
            <Link to="/"> Search </Link>
        </div>
        <h2>Current Reservations:</h2>
        <ReservationItems reservations={reservations} fetchRes={fetchReservations}/>
    </>
}

export default Reservations;