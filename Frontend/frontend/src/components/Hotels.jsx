import Hotel from "./Hotel.jsx";
import {useEffect, useState} from "react";
import classes from "./Hotels.module.css";

const Hotels = (props) => {
    const [hotels, setHotels] = useState([]);

    useEffect(() => {
        setHotels(props.hotels)
    }, [props.hotels])

    const hotelsList = hotels.map(hotel => <Hotel key={hotel.id} id={hotel.id} name={hotel.name}/>);

    return <ul className={classes.hotels}>
        {hotelsList}
    </ul>
}

export default Hotels;