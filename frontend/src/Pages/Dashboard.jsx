import React from 'react'
import Appbar from '../components/Appbar';
import { useNavigate } from 'react-router-dom';
import './dashboard.css';
import { Button, Card } from 'react-bootstrap';

function Dashboard(){
    
    const navigate = useNavigate();
    if(sessionStorage.getItem("admin")) {
        navigate("/adminhome");
    } else if(sessionStorage.getItem("username")) {
        navigate("/home");
    }

    function adminLogin() {
        navigate('/loginadmin');
    }

    function userLogin() {
        navigate('/login');
    }

    function userRegister() {
        navigate('/register');
    }

    return (
        <>
            <Appbar hbtn={"/"}/>
            
            <section className={"summary"}>
            <h2>LAMA</h2>
            <p>
                A Loan Management Application
            </p>
        </section>
            <div className="dashboard-container">

            

            <Card style={{ width: '18rem' }} className='mx-4'>
      <Card.Body>
        <Card.Title>Admin</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">Login as Admin</Card.Subtitle>
        <Button variant='dark' onClick={adminLogin} className='m-2'>Login</Button>
      </Card.Body>
    </Card>

    <Card style={{ width: '18rem' }} className='mx-4'>
      <Card.Body>
        <Card.Title>Employee</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">Login/Register as Employee</Card.Subtitle>
        <Button variant='light' onClick={userRegister} className='m-2'>Register</Button>
        <Button variant='dark' onClick={userLogin} className='m-2'>Login</Button>
      </Card.Body>
    </Card>
            </div>
        </>
    )
}

export default Dashboard;
