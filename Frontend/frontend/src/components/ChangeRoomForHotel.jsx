import {useEffect, useState} from "react";
import ChangeRoom from "./ChangeRoom.jsx";
import classes from "./ChangeRoomForHotel.module.css"

const ChangeRoomForHotel = ({reservationId, roomId, hotelId, fetchRes}) => {

    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        fetchHotelAvailableRooms().catch((error) => {
            console.log(error);
        })
    }, [])

    const fetchHotelAvailableRooms = async () => {
        const response = await fetch(`http://localhost:8080/api/rooms/${hotelId}`)

        if (!response.ok) {
            throw new Error('Something went wrong!');
        }

        const responseData = await response.json();
        console.log(responseData);

        const loadedRooms = []
        for (const key in responseData) {
            loadedRooms.push({
                id: responseData[key].id,
                roomNumber: responseData[key].roomNumber,
                type: responseData[key].type,
                price: responseData[key].price
            })
        }

        setRooms(loadedRooms);
    }

    const roomsList = rooms.map(room => <ChangeRoom key={room.id} reservationId={reservationId} id={room.id}
                                                    hotelId={hotelId} roomNumber={room.roomNumber} type={room.type}
                                                    price={room.price} fetchRes={fetchRes}/>);


    return <ul className={classes.changeRooms}>
        {roomsList}
    </ul>
}

export default ChangeRoomForHotel;