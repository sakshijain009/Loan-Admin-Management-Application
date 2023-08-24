import Appbar from './Appbar';
import './Dash.css';
import { Link } from "react-router-dom";
import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';

function Dash({user}){
    const navigate = useNavigate();
    if(sessionStorage.getItem("username") === null) {
        navigate("/login");
    } 
    return(
        <>
            <Appbar hbtn={"/home"} bt={"Logout"}/>
            <div className="user-dashboard__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>User Dash Board</h3>
                <div className="user-dashboard__buttons">
                    <Link to="/viewloan" className="user-dashboard__button">View Loans</Link>
                    <Link to="/applyloan" className="user-dashboard__button">Apply for Loan</Link>
                    <Link  to="/viewItems" className="user-dashboard__button">View Items Purchased</Link>
                </div>
            </div>
        </>
    )
}
export default Dash;