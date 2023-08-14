import React from "react";
import Appbar from './Appbar';
import './Dash.css';
import { Link } from "react-router-dom";

function Dash(){
    return(
        <>
            <Appbar bt={"Logout"}/>
            <div className="user-dashboard__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>User Dash Board</h3>
                <div className="user-dashboard__buttons">
                    <Link className="user-dashboard__button">View Loans</Link>
                    <Link className="user-dashboard__button">Apply for Loan</Link>
                    <Link className="user-dashboard__button">View Items Purchased</Link>
                </div>
            </div>
        </>
    )
}
export default Dash;