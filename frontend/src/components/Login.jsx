import React, { useState, useEffect } from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import Appbar from './Appbar';
import { useNavigate } from 'react-router-dom';

import './Login.css';

const Login = ({user, loginUser}) => {

    const [empid, setEmpid] = useState("");
    const [pwd, setPwd] = useState("");


    const navigate = useNavigate();

    useEffect(() => {
        if(user!=null && user.length > 0)
        {
            navigate('/login');
        }
    },[user])

    async function handleSubmit(e) {
        e.preventDefault()
        console.log("Login Submit")
        loginUser(empid);
        let resp = {empid, pwd};
        console.log(resp);
        const response = await fetch("http://localhost:8080/checkLogin", {
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
    }

    return (
    <>
        <Appbar bt={"Register"}/>
            <div className='login'>
                <h2>Login User</h2>

                <TextField id="outlined-basic" label="Employee ID" variant="outlined" className='text_login'
                    onChange={
                        e => setEmpid(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    onChange={
                        e => setPwd(e.target.value)
                    }/>
                 <Button variant="contained" className='login_button'
                    onClick={handleSubmit}>Login</Button>
            </div>

            
    </>
  )
}

export default Login;
