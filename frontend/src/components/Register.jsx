import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
import axios from 'axios'
import Navbar from './Navbar/Navbar';
import './Register.css'

function AddUser() {
    const [empid, setEmpid] = React.useState("");
    const [name, setName] = React.useState("");
    const [dept, setDept] = React.useState("");
    const [des, setDes] = React.useState("");
    const [gender, setGender] = React.useState("");
    const [dob, setDob] = React.useState("");
    const [doj, setDoj] = React.useState("");

    async function handleSubmit(e) {
        e.preventDefault()
        console.log("Form Submitted");
        axios.post('http://localhost:8080/addUser', {
            empid,
            name,
            dept,
            des,
            gender,
            dob,
            doj
        }).then(response => console.log(response)).catch(err => console.log(err));
        // fetch('http://localhost:8080/addUser', {
        //     method: 'POST',
        //     body: JSON.stringify(body)
        // })
        // .then((response) => {
        //     console.log(response);
        //     return response.json();
        // })
    }
    return (
        <>
            <Navbar />
            <div className='register'>
                <h2>Register User</h2>
                <TextField id="outlined-basic" label="Employee ID" variant="outlined" className='text_register'
                    onChange={
                        e => setEmpid(e.target.value)
                    }/><br/>
                <TextField id="outlined-basic" label="Employee Name" variant="outlined" className='text_register'
                    onChange={
                        e => setName(e.target.value)
                    }/><br/>
                <TextField id="outlined-basic" label="Employee Department" variant="outlined" className='text_register'
                    onChange={
                        e => setDept(e.target.value)
                    }/><br/>
                <TextField id="outlined-basic" label="Employee Designation" variant="outlined" className='text_register'
                    onChange={
                        e => setDes(e.target.value)
                    }/><br/>
                <TextField id="outlined-basic" label="Gender" variant="outlined" className='text_register'
                    onChange={
                        e => setGender(e.target.value)
                    }/><br/>
                <TextField id="outlined-basic" label="Date of Birth (DDMMYYYY)" variant="outlined" className='text_register'
                    onChange={
                        e => setDob(e.target.value)
                    }/><br/>
                <TextField id="outlined-basic" label="Date of Joining (DDMMYYYY)" variant="outlined" className='text_register'
                    onChange={
                        e => setDoj(e.target.value)
                    }/><br/>
                <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Submit</Button>
            </div>
        </>
    )
}
export default AddUser;
