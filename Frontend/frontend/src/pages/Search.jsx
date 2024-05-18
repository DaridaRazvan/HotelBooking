import Card from "../components/ui/Card.jsx";
import classes from "./Search.module.css";

import {useEffect, useRef, useState} from "react";
import Hotels from "../components/Hotels.jsx";
import {Link} from "react-router-dom";

const Search = () => {
    const [latitude, setLatitude] = useState(0);
    const [longitude, setLongitude] = useState(0);
    const [hotels, setHotels] = useState([]);
    const distanceRef = useRef();

    useEffect(() => {
        navigator.geolocation.getCurrentPosition((position) => {
            setLatitude(position.coords.latitude)
            setLongitude(position.coords.longitude)
        })
    }, [])

    const getHotels = async (distanceInKm) => {
        const response = await fetch('http://localhost:8080/api/hotel/inRange', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                latitude: latitude,
                longitude: longitude,
                distanceInKm: distanceInKm
            })
        })
        const responseData = await response.json()

        const loadedHotels = []
        for (const key in responseData) {
            loadedHotels.push({
                id: responseData[key].id,
                name: responseData[key].name
            })
        }
        setHotels(loadedHotels)
    }

    const searchButtonClicked = async (event) => {
        event.preventDefault();
        console.log("button clicked")

        const distanceInKm = distanceRef.current.value;
        console.log(distanceInKm)

        getHotels(distanceInKm).catch((error) => {
            console.log(error)
        })
    }

    return <>

        <div className={classes.header}>
            <Link to="/reservations">See Reservations</Link>
        </div>
        <Card>
            <div className={classes.search}>
                <form className={classes.searchForm} onSubmit={searchButtonClicked}>
                    <input className={classes.input} type='number' min="1" id='distance' ref={distanceRef}/>
                    <button>Search Hotels</button>
                </form>
                <Hotels hotels={hotels}/>
            </div>
        </Card>
    </>
}

export default Search;