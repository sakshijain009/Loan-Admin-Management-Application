import React from "react";
import Appbar from './Appbar';
import './Dash.css';
import { Link } from "react-router-dom";

function AdminDashboard(){
    return(
        <>
            <Appbar bt={"Logout"}/>
            <div className="user-dashboard__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>Admin Dash Board</h3>
                <div className="user-dashboard__buttons">
                    <Link to="/adminadduser" className="user-dashboard__button">Customer Data Management</Link>
                    <Link to="/adminaddloan" className="user-dashboard__button">Loan Card Management</Link>
                    <Link to="/adminadditem" className="user-dashboard__button">Items Master Data</Link>
                </div>
            </div>
        </>
    )
}
export default AdminDashboard;