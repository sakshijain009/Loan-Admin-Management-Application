import React from 'react'
import Appbar from './Appbar'
import { useNavigate } from 'react-router-dom';
import './dashboard.css';

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
            <Appbar hbtn={"0"} bt={"Register"}/>
            <div className="dashboard-container">
                <div className="dashboard-card">
                    <span>Admin Login</span>
                    <button className='dashboard-button' onClick={adminLogin}>Login</button>
                </div>
                <div className="dashboard-card">
                <span>User Register/Login</span>
                    <div className="dashboard-buttons">
                    <button className='dashboard-button' onClick={userRegister}>Register</button>
                    <button className='dashboard-button' onClick={userLogin}>Login</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Dashboard;
