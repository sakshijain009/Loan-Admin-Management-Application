import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
import axios from 'axios'
import Navbar from './Navbar/Navbar';
import Appbar from './Appbar';
import './Register.css'

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import dayjs from 'dayjs';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import Alert from '@mui/material/Alert';

function AddUser() {
    const [empid, setEmpid] = React.useState("");
    const [name, setName] = React.useState("");
    const [dept, setDept] = React.useState("");
    const [des, setDes] = React.useState("");
    const [gender, setGender] = React.useState("");
    const [dob, setDob] = React.useState(dayjs('2023-01-01'));
    const [doj, setDoj] = React.useState(dayjs('2023-01-01'));
    const [pwd, setPwd] = React.useState("");

    async function handleSubmit(e) {
        e.preventDefault()
        console.log("Form Submitted");
        let resp = {empid,
            name,
            dept,
            des,
            gender,
            dob,
            doj,
            pwd
        };
        console.log(resp);
        alert('Form Submitted Successfully');
        <Alert severity="success">This is a success alert — check it out!</Alert>
        axios.post('http://localhost:8080/addUser', resp)
        .then(response => console.log(response))
        .catch(err => console.log(err));
    }
    return (
        <>
            <Appbar />
            <div className='register'>
                {/* <h2>Register User</h2> */}
                <TextField id="outlined-basic" label="Employee ID" variant="outlined" className='text_register'
                    onChange={
                        e => setEmpid(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Employee Name" variant="outlined" className='text_register'
                    onChange={
                        e => setName(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Employee Department" variant="outlined" className='text_register'
                    onChange={
                        e => setDept(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Employee Designation" variant="outlined" className='text_register'
                    onChange={
                        e => setDes(e.target.value)
                    }/>
                {/* <TextField id="outlined-basic" label="Gender" variant="outlined" className='text_register'
                    onChange={
                        e => setGender(e.target.value)
                    }/> */}
                <Box sx={{ minWidth: 120, paddingTop:1}}>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Gender</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value={gender}
                                label="Gender"
                                onChange={e => setGender(e.target.value)}
                            >
                            <MenuItem value={'M'}>Male</MenuItem>
                            <MenuItem value={'F'}>Female</MenuItem>
                            <MenuItem value={'O'}>Other</MenuItem>
                            </Select>
                    </FormControl>
                </Box>
                <TextField id="outlined-basic" label="Password" variant="outlined" className='text_register' type='password'
                    onChange={
                        e => setPwd(e.target.value)
                    }/>
                {/* <TextField id="outlined-basic" label="Date of Birth (DDMMYYYY)" variant="outlined" className='text_register'
                    onChange={
                        e => setDob(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Date of Joining (DDMMYYYY)" variant="outlined" className='text_register'
                    onChange={
                        e => setDoj(e.target.value)
                    }/> */}
                <LocalizationProvider dateAdapter={AdapterDayjs} >
                    <DemoContainer components={['DatePicker']}>
                        <DatePicker label="Date of Birth" className='text_register' value={dob} format="DD/MM/YYYY" onChange={
                        e => setDob(e)
                    }/>
                    </DemoContainer>
                </LocalizationProvider>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={['DatePicker']}>
                        <DatePicker label="Date of Joining" className='text_register' value={doj} format="DD/MM/YYYY" onChange={
                        e => setDoj(e)
                    }/>
                    </DemoContainer>
                </LocalizationProvider>

                <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Submit</Button>
            </div>
        </>
    )
}
export default AddUser;
