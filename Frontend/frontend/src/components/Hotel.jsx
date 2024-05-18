import {useNavigate} from "react-router-dom";
import classes from "./Hotel.module.css";

const Hotel = ({id, name}) => {
    const navigate = useNavigate();

    return <li className={classes.hotel}>
        <p className={classes.name}>{name}</p>
        <button onClick={() => navigate(`/hotel/${id}`)}> Make Reservation</button>
    </li>
}

export default Hotel;