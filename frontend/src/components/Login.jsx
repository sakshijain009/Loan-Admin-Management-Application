import React, { useState } from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import Appbar from './Appbar';

import './Login.css';

const Login = () => {

    const [empid, setEmpid] = useState("");
    const [pwd, setPwd] = useState("");

    async function handleSubmit(e) {
        e.preventDefault()
        console.log("Login Submit")
        let resp = {empid, pwd};
        console.log(resp);
        const response = await fetch("http://localhost:8080/login", {
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

        const json = await response;

        console.log(json.body);
    }

    return (
    <>
        <Appbar />
            <div className='login'>
                {/* <h2>Login User</h2> */}

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