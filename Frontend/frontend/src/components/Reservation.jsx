import {useState} from "react";
import ChangeRoomForHotel from "./ChangeRoomForHotel.jsx";
import Card from "./ui/Card.jsx";
import classes from "./Reservation.module.css";

const options = {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric',
    hour12: false
};

const Reservation = ({id, time, hotelId, hotelName, roomId, roomNumber, type, price, fetchRes}) => {

    const [showAvailableRoomsForHote, setShowAvaiableRoomsForHotel] = useState(false);
    const cancelReservationApi = async () => {
        const response = await fetch('http://localhost:8080/api/reservations/cancel', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: id
            })
        })
        const responseData = await response.json();
        fetchRes(responseData.message);
    }

    const cancelReservation = () => {
        console.log("Canceling reservation");
        cancelReservationApi().catch((error) => {
            console.log(error)
        })
    }

    const changeReservation = () => {
        console.log("Changing reservation");
        setShowAvaiableRoomsForHotel(!showAvailableRoomsForHote)
    }

    if (type === 1) {
        type = "Single Room";
    } else if (type === 2) {
        type = "Double Room";
    } else type = "Suite Room"

    const date = new Date(time);
    const formattedDate = date.toLocaleString('en-US', options);

    return <li>
        <Card>
            {/*<p>Id: {id}</p>*/}
            <p>Reservation Time: {formattedDate}</p>
            {/*<p>{hotelId}</p>*/}
            <p>Hotel: {hotelName}</p>
            {/*<p>{roomId}</p>*/}
            <p>RoomNumber: {roomNumber}</p>
            <p>Type: {type}</p>
            <p>Price: {price}</p>
            <div className={classes.buttons}>
                <button onClick={cancelReservation}>Cancel Reservation</button>
                <button onClick={changeReservation}>Change Reservation</button>
            </div>

            {showAvailableRoomsForHote && <div>
                <ChangeRoomForHotel reservationId={id} roomId={roomId} hotelId={hotelId} fetchRes={fetchRes}/>
            </div>}
        </Card>

    </li>
}

export default Reservation;