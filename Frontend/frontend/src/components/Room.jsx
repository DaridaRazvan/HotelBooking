import Card from "./ui/Card.jsx";
import classes from "./Room.module.css";

const Room = ({id, hotelId, roomNumber, type, price, fetchHotels}) => {
    const reserveRoom = async () => {
        const response = await fetch('http://localhost:8080/api/reservations/save', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                hotelId: hotelId,
                roomId: id
            })
        })
        const responseData = await response.json()

        console.log(responseData);
        fetchHotels();
    }

    if (type === 1) {
        type = "Single Room";
    } else if (type === 2) {
        type = "Double Room";
    } else type = "Suite Room"

    return <li>
        <Card>
            <div className={classes.room}>
                <div className={classes.details}>
                    <p>Room Number: {roomNumber}</p>
                    <p>Camera Type: {type}</p>
                </div>
                <div className={classes.book}>
                    <p>Price: {price} RON</p>
                    <button onClick={reserveRoom}>Book room</button>
                </div>
            </div>


        </Card>
    </li>
}

export default Room;