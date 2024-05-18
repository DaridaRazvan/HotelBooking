import Reservation from "./Reservation.jsx";
import {useEffect, useState} from "react";
import classes from "./ReservationItems.module.css"

import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const ReservationItems = (props) => {
    const [reserv, setReserv] = useState([])

    useEffect(() => {
        setReserv(props.reservations)
    }, [props.reservations])

    const fetchReservations = (message) => {
        props.fetchRes();
        toast(message);
    }

    const reservationsList = reserv.map(r => <Reservation key={r.id} id={r.id} time={r.time} hotelId={r.hotelId}
                                                          hotelName={r.hotelName} roomId={r.roomId}
                                                          roomNumber={r.roomNumber} type={r.type} price={r.price}
                                                          fetchRes={fetchReservations}/>)

    return <ul className={classes.reservations}>
        {reservationsList}
        <ToastContainer/>
    </ul>
}

export default ReservationItems;