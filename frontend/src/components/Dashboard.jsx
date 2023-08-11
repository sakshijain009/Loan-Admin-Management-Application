import React from 'react'
import Appbar from './Appbar'
import { Navigate, useNavigate } from 'react-router-dom';
import './dashboard.css';

function Dashboard(){
    
    const navigate = useNavigate();

    function adminLogin() {
        // navigate('/login')
    }

    function userLogin() {
        navigate('/login');
    }

    function userRegister() {
        navigate('/register');
    }

    return (
        <>
            <Appbar/>
            <div className="container">
                <div className="card">
                    <span>Admin Login</span>
                    <button onClick={adminLogin}>Login</button>
                </div>
                <div className="card">
                <span>User Register/Login</span>
                    <div className="buttons">
                    <button onClick={userRegister}>Register</button>
                    <button onClick={userLogin}>Login</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Dashboard;
