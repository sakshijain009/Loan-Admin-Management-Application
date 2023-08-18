import Appbar from './Appbar';
import './Dash.css';
import { Link } from "react-router-dom";
import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';

function Dash({user}){
    const navigate = useNavigate();
    useEffect(() => {
        if(user === "" && user.length > 0)
        {
            navigate('/login');
        }
    },[user])
    return(
        <>
            <Appbar bt={"Logout"}/>
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