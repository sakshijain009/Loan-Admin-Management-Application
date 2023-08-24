import React, { useState } from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import Appbar from './Appbar';
import { useNavigate } from 'react-router-dom';

import './Login.css';

const AdminLogin = () => {

    const [empid, setEmpid] = useState("");
    const [pwd, setPwd] = useState("");
    const [error, setError] = useState({
        "username": '',
        "password": ''
    });
    const navigate = useNavigate();
    if(sessionStorage.getItem("admin")) {
        navigate("/adminhome");
    }

    async function handleSubmit(e) {
        e.preventDefault()
        // console.log("Login Submit")
        setError({
            "username": empid ? "" : "Username is required",
            "password": pwd ? pwd.length === 8 ? "" : "Password should contain 8 digits" : "Password is required"
        })
        let resp = {empid, pwd};
        console.log(resp);
        const response = await fetch("http://localhost:8080/api/admin/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(
                {
                    "username": empid,
                    "password": pwd
                }
            )
        });

        const json = await response.json();
        if(response.status === 200){
            sessionStorage.clear();
            console.log(JSON.stringify(json))
            sessionStorage.setItem("admin", JSON.stringify({"username": empid, "password": pwd}));
            navigate('/adminhome');
        }

        console.log(json);
    }

    return (
    <>
        <Appbar hbtn={"/"}/>
            <div className='login'>
                <h2>Login Admin</h2>

                <TextField id="outlined-basic" label="Admin ID" variant="outlined" className='text_login'
                    onChange={
                        e => setEmpid(e.target.value)
                    }/>
                {error.username && <p style={{color:'red'}}>{error.username}</p>}
                <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    onChange={
                        e => setPwd(e.target.value)
                    }/>
                {error.password && <p style={{color:'red'}}>{error.password}</p>}
                 <Button variant="contained" className='login_button'
                    onClick={handleSubmit}>Login</Button>
            </div>

            
    </>
  )
}

export default AdminLogin;