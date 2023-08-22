import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
// import axios from 'axios'
// import Navbar from './Navbar/Navbar';
import Appbar from './Appbar';
import './Register.css'

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

// import dayjs from 'dayjs';
// import {DemoContainer} from '@mui/x-date-pickers/internals/demo';
// import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
// import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
// import {DatePicker} from '@mui/x-date-pickers/DatePicker';
// import Alert from '@mui/material/Alert';
import { useState, useEffect } from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import { InputAdornment } from '@mui/material';

function AdminEditUser() {
    const navigate = useNavigate();
    const [empid, setEmpid] = React.useState("");
    const [name, setName] = React.useState("");
    const [dept, setDept] = React.useState("");
    const [des, setDes] = React.useState("");
    const [gender, setGender] = React.useState("");
    const [pwd, setPwd] = useState("");
    // const [dob, setDob] = React.useState(dayjs('2023-01-01'));
    // const [doj, setDoj] = React.useState(dayjs('2023-01-01'));

    const [dobSend, setDobSend] = React.useState("2023-01-01");
    const [dojSend, setDojSend] = React.useState("2023-01-01");

    const {id} = useParams();

    useEffect(() => {
        const data = async () => {
            // console.log(id);
            const response = await fetch(`http://localhost:8080/api/users/profile/${id}`);
            const res = await response.json();
            setEmpid(res.id);
            setName(res.name);
            setDept(res.department);
            setDes(res.designation);
            setPwd(res.password);
            setGender(res.gender);
            setDobSend(res.dob);
            setDojSend(res.doj);
        }
        data();
    }, [id])

    function dateIsValid(date) {
        return date instanceof Date && !isNaN(date);
      }

    async function handleSubmit(e) {
        e.preventDefault();
        try {
        
        if(!dateIsValid(new Date(dobSend))) {
            throw new Error("Enter correct DOB in the format YYYY-MM-DD");
        }
        if(!dateIsValid(new Date(dojSend))) {
            throw new Error("Enter correct DOJ in the format YYYY-MM-DD");
        }
        // console.log("Registration successful");
        const response = await fetch("http://localhost:8080/api/admin/updateUser", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(
                {
                    "id": empid,
                    "name": name,
                    "department": dept,
                    "designation": des,
                    "gender": gender,
                    "dob": dobSend,
                    "doj": dojSend,
                    "password": pwd
                }
            )
        });
        const json = await response.json();
        console.log(json);
        console.log(response.status);
        if(response.status === 200){
            navigate('/adminviewuser');
        }
        else{
            alert("Please fill the details correctly!");
        }
            
    } catch (error) {
        console.log(error)
    }
    }

    return (
        <>
            <Appbar/>
            <div className='register'>
                <h2>Edit User</h2>
                <TextField className='text_register' disabled placeholder='Employee ID' InputProps={{
        endAdornment: <InputAdornment position="end">{empid}</InputAdornment>,
                    }} />
                <TextField id="outlined-basic" placeholder='Employee Name' className='text_register' value={name}
                    onChange={
                        e => setName(e.target.value)
                    }/>
                <TextField id="outlined-basic" placeholder="Employee Department" className='text_register' value={dept}
                    onChange={
                        e => setDept(e.target.value)
                    }/>
                <TextField id="outlined-basic" placeholder="Employee Designation"  className='text_register' value={des}
                    onChange={
                        e => setDes(e.target.value)
                    }/> {/* <TextField id="outlined-basic" label="Gender" variant="outlined" className='text_register'
                    onChange={
                        e => setGender(e.target.value)
                    }/> */}
                <Box sx={
                    {
                        minWidth: 120,
                        paddingTop: 1
                    }
                }>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Gender</InputLabel>
                        <Select labelId="demo-simple-select-label" id="demo-simple-select"
                            value={gender}
                            label="Gender"
                            onChange={
                                e => setGender(e.target.value)
                        }>
                            <MenuItem value={'M'}>Male</MenuItem>
                            <MenuItem value={'F'}>Female</MenuItem>
                            <MenuItem value={'O'}>Other</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
                {/* <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    onChange={
                        e => setPwd(e.target.value)
                    }/>  */}

                <TextField id="outlined-basic" placeholder="Date of Birth" className='text_register' value={dobSend}
                    onChange={
                        e => setDobSend(e.target.value)
                    }/>

                <TextField id="outlined-basic" placeholder="Date of Joining"  className='text_register' value={dojSend}
                    onChange={
                        e => setDojSend(e.target.value)
                    }/>
                {/* <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={
                        ['DatePicker']
                    }>
                        <DatePicker label="Date of Birth" className='text_register'
                            value={dob}
                            format="DD/MM/YYYY"
                            onChange={dateBirthHandler}/>
                    </DemoContainer>
                </LocalizationProvider>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={
                        ['DatePicker']
                    }>
                        <DatePicker label="Date of Joining" className='text_register'
                            value={doj}
                            format="DD/MM/YYYY"
                            onChange={dateJoinHandler}/>
                    </DemoContainer>
                </LocalizationProvider> */}

                <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Update User</Button>
            </div>
        </>
    )
}
export default AdminEditUser;
