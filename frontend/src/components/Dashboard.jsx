import React from 'react'
import Appbar from './Appbar'
import { useNavigate } from 'react-router-dom';
import './dashboard.css';
import { Card } from 'react-bootstrap';

function Dashboard(){
    
    const navigate = useNavigate();

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
            <Appbar hbtn={"/"} bt={"Register"}/>
            <div className="dashboard-container">


            <Card style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>Admin</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">Login as Admin</Card.Subtitle>
        <Card.Link onClick={adminLogin}>Login</Card.Link>
      </Card.Body>
    </Card>

    <Card style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>Employee</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">Login/Register as Employee</Card.Subtitle>
        <Card.Link onClick={userRegister}>Register</Card.Link>
        <Card.Link onClick={userLogin}>Login</Card.Link>
      </Card.Body>
    </Card>


                {/* <div className="dashboard-card">
                    <span>Admin Login</span>
                    <button className='dashboard-button' onClick={adminLogin}>Login</button>
                </div>
                <div className="dashboard-card">
                <span>User Register/Login</span>
                    <div className="dashboard-buttons">
                    <button className='dashboard-button' onClick={userRegister}>Register</button>
                    <button className='dashboard-button' onClick={userLogin}>Login</button>
                    </div>
                </div> */}
            </div>
        </>
    )
}

export default Dashboard;
