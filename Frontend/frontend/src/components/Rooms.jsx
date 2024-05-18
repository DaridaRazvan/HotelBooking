import {useEffect, useState} from "react";
import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Room from "./Room.jsx";
import classes from "./Rooms.module.css";

const Rooms = (props) => {
    const [rooms, setRooms] = useState([])

    useEffect(() => {
        setRooms(props.rooms)
    }, [props.rooms])

    const fetchHotels = () => {
        props.fetchHotelsAgain();
        toast("Room booked!");
    }

    const roomsList = rooms.map(room => <Room key={room.id} id={room.id} hotelId={props.hotelId}
                                              roomNumber={room.roomNumber} type={room.type} price={room.price}
                                              fetchHotels={fetchHotels}/>);

    return <ul className={classes.rooms}>
        {roomsList}
        <ToastContainer/>
    </ul>
}

export default Rooms;