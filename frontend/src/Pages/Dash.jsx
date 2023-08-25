import Appbar from '../components/Appbar';
import './Dash.css';
import { Link } from "react-router-dom";
import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import { Button, Card } from 'react-bootstrap';

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
                <div className="user-dashboard__buttons mt-5">

                    
                <Card style={{ width: '20rem' }} className='mx-3'>
      <Card.Body style={{display:'flex', flexDirection:'column', justifyContent:'center'}}>
        <Card.Title>Customer Data Management</Card.Title>
        <Button variant='dark' className='m-2'>
        <Link style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} to="/viewloan">
            View Loans
        </Link>
        </Button>
      </Card.Body>
    </Card>

    <Card style={{ width: '20rem' }} className='mx-3'>
      <Card.Body style={{display:'flex', flexDirection:'column', justifyContent:'center'}}>
        <Card.Title>Loan Card Management</Card.Title>
        <Button variant='dark' className='m-2'>
        <Link style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} to="/applyloan">
            Apply for Loan
        </Link>
        </Button>
      </Card.Body>
    </Card>

    <Card style={{ width: '20rem' }} className='mx-3'>
      <Card.Body style={{display:'flex', flexDirection:'column', justifyContent:'center'}}>
        <Card.Title>Items Master Data</Card.Title>
        <Button variant='dark' className='m-2'>
        <Link style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} to="/viewitems">
            View Items Purchased
        </Link>
        </Button>
      </Card.Body>
    </Card>

                    {/* <Button variant="primary" className="mx-5 px-5 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/viewloan">
                                View Loans
                        </Link>
                    </Button>
                    <Button variant="warning" className="mx-5 px-4 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'black', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/applyloan">
                                Apply for Loans
                        </Link>
                    </Button>
                    <Button variant="primary" className="mx-5 px-3 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/viewitems">
                                View Items Purchased
                        </Link>
                    </Button> */}
                    {/* <Link to="/adminaddloan" className="user-dashboard__button">Loan Card Management</Link>
                    <Link to="/adminadditem" className="user-dashboard__button">Items Master Data</Link> */}
                </div>
            </div>
        </>
    )
}
export default Dash;