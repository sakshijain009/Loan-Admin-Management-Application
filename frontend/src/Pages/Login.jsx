import React, { useState, useEffect } from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import Appbar from '../components/Appbar';
import { useNavigate } from 'react-router-dom';
import {Button as Btn} from 'react-bootstrap';
import Modal from 'react-bootstrap/Modal';
import './Login.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Login = ({user, loginUser, bt}) => {

    const [empid, setEmpid] = useState("");
    const [pwd, setPwd] = useState("");

    const [show, setShow] = useState(false);

    const [error, setError] = useState({
        "username": '',
        "password": ''
    });

    const handleClose = () => setShow(false);
    const handleClose2 = async () => {
        console.log({empid, pwd})
        const response = await fetch("http://localhost:8080/api/users/changePassword", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "emp_id": empid,
                "pwd": pwd
            },
            body: JSON.stringify(
                { }
            )
        });
        const json = await response.json();
        console.log(json);
        if(json.status === 200){
            toast(json.message);
            setShow(false);
        }
        else{
            toast(json.message);
        }
        
    }
    const handleShow = () => setShow(true);

    const navigate = useNavigate();

    useEffect(() => {
        if(user!=null && user.length > 0)
        {
            navigate('/login');
        }
    },[user])

    async function handleSubmit(e) {
        e.preventDefault();
        let resp = {empid, pwd};
        console.log(resp);
        setError({
            "username": empid ? "" : "Employee ID is required",
            "password": pwd ? pwd.length === 8 ? "" : "Password should contain 8 digits" : "Password is required"
        })

        if(error.username || error.password) return;
        
        const response = await fetch("http://localhost:8080/api/users/checkLogin", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(
                {
                    "id": empid,
                    "password": pwd
                }
            )
        });

        const json = await response.json();
        console.log(json);
        if(json.message === "Login success"){
            sessionStorage.clear();
            loginUser(empid);
            navigate('/home');
        }
        else
        {
            if(empid)
            toast(json.message);
            setEmpid("");
            setPwd("");
        }
    }

    return (
    <>
        <Appbar bt={bt} hbtn={"/"}/>
            <div className='login'>
                <h2>Login User</h2>

                <TextField id="outlined-basic" label="Employee ID" variant="outlined" className='text_login'
                    value={empid}
                    onChange={
                        e => setEmpid(e.target.value)
                    }/>
                    {error.username && <p style={{color:'red'}}>{error.username}</p>}
                <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    value={pwd}
                    onChange={
                        e => setPwd(e.target.value)
                    }/>
                    {error.password && <p style={{color:'red'}}>{error.password}</p>}
                 <Button variant="contained" className='login_button'
                    onClick={handleSubmit}>Login</Button>

                <Btn variant="warning" className='login_button' style={{minWidth: 420}} onClick={handleShow}>
                        Change Password
                    </Btn>

                <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                <Modal.Title>Change your Password</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                <TextField id="outlined-basic" label="Employee ID" variant="outlined" className='text_login'
                    onChange={
                        e => setEmpid(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    onChange={
                        e => setPwd(e.target.value)
                    }/>
                </Modal.Body>
                <Modal.Footer>
                <Btn variant="danger" onClick={handleClose}>
                    Cancel
                </Btn>
                <Btn variant="success" onClick={handleClose2}>
                    Change My Password
                </Btn>
                </Modal.Footer>
            </Modal>
            </div>
            <ToastContainer />            
    </>
  )
}

export default Login;
