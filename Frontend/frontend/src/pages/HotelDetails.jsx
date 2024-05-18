import {useEffect, useState} from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import Rooms from "../components/Rooms.jsx";
import classes from "./HotelDetails.module.css";

const HotelDetails = () => {
    const [name, setName] = useState('');
    const [rooms, setRooms] = useState([]);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetchHotelDetails().catch((error) => {
            console.log(error);
        })
    }, [])

    useEffect(() => {
        fetchHotelAvailableRooms().catch((error) => {
            console.log(error);
        })
    }, [])

    const fetchHotelAvailableRooms = async () => {
        const response = await fetch(`http://localhost:8080/api/rooms/${id}`)

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

    const fetchHotelDetails = async () => {
        const response = await fetch(`http://localhost:8080/api/hotel/${id}`);

        if (!response.ok) {
            throw new Error('Something went wrong!');
        }

        const responseData = await response.json();
        console.log(responseData);

        setName(responseData.name);
    };

    return <>
        <div className={classes.header}>
            <Link to="/"> Search </Link>
            <Link to="/reservations">See Reservations</Link>
        </div>
        <h1> {name} </h1>
        <h3>
            Available Rooms:
        </h3>
        <Rooms rooms={rooms} hotelId={id} fetchHotelsAgain={fetchHotelAvailableRooms}/>


    </>
}

export default HotelDetails;