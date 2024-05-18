import classes from "./ChangeRoom.module.css"

const ChangeRoom = ({reservationId, id, hotelId, roomNumber, type, price, fetchRes}) => {

    const changeRoom = async () => {
        const response = await fetch('http://localhost:8080/api/reservations/change', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                hotelId: hotelId,
                roomId: id,
                reservationId: reservationId
            })
        })
        const responseData = await response.json()
        console.log(responseData);
        console.log("change room");
        fetchRes(responseData.message);
    }

    return <li className={classes.changeRoom} onClick={changeRoom}>
        <p>Room Number: {roomNumber}</p>
        <p>Camera Type: {type}</p>
        <p>Price: {price} RON</p>
    </li>
}

export default ChangeRoom;