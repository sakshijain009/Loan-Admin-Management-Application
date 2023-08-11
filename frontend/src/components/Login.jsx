import React, { useState } from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import Appbar from './Appbar';

import './Login.css';

const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit(e) {
        e.preventDefault()
        console.log("Form Submitted");
        let resp = {username, password};
        console.log(resp);
        // alert('Form Submitted Successfully');
        // <Alert severity="success">This is a success alert — check it out!</Alert>
        // axios.post('http://localhost:8080/addUser', resp)
        // .then(response => console.log(response))
        // .catch(err => console.log(err));
    }

    return (
    <>
        <Appbar />
            <div className='login'>
                {/* <h2>Login User</h2> */}

                <TextField id="outlined-basic" label="Username" variant="outlined" className='text_login'
                    onChange={
                        e => setUsername(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    onChange={
                        e => setPassword(e.target.value)
                    }/>
                 <Button variant="contained" className='login_button'
                    onClick={handleSubmit}>Submit</Button>
            </div>

            
    </>
  )
}

export default Login;