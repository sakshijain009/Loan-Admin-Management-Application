import React from "react";
import Appbar from './Appbar';
import './Dash.css';
import { Link, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";

function AdminDashboard(){
    let goToUrl;
    const navigate = useNavigate();
    if(sessionStorage.getItem("admin") === null) {
        navigate("/loginadmin");
    } else {
        goToUrl = "/adminhome";
    }
    return(
        <>
            <Appbar bt={"Logout"} hbtn={"/adminhome"}/>
            <div className="user-dashboard__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>Admin Dash Board</h3>
                <div className="user-dashboard__buttons mt-5">
                    <Button variant="primary" className="mx-5 px-3 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/adminadduser">
                                Customer Data Management
                        </Link>
                    </Button>
                    <Button variant="warning" className="mx-5 px-5 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'black', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/adminaddloan">
                                Loan Card Management
                        </Link>
                    </Button>
                    <Button variant="primary" className="mx-5 px-5 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/adminadditem">
                                Items Master Data
                        </Link>
                    </Button>
                    {/* <Link to="/adminaddloan" className="user-dashboard__button">Loan Card Management</Link>
                    <Link to="/adminadditem" className="user-dashboard__button">Items Master Data</Link> */}
                </div>
            </div>
        </>
    )
}
export default AdminDashboard;