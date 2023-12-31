import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
import Appbar from '../../components/Appbar';
import '../Register.css'

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import dayjs from 'dayjs';
import {DemoContainer} from '@mui/x-date-pickers/internals/demo';
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {DatePicker} from '@mui/x-date-pickers/DatePicker';
// import Alert from '@mui/material/Alert';
// import { useEffect } from 'react';
import {Link, useNavigate} from 'react-router-dom';

function AdminAddUser() {
    let goToUrl;
    const navigate = useNavigate();
    if(sessionStorage.getItem("admin") === null) {
        navigate("/loginadmin");
    } else {
        goToUrl = "/adminhome";
    }
    const [empid, setEmpid] = React.useState("");
    const [name, setName] = React.useState("");
    const [dept, setDept] = React.useState("");
    const [des, setDes] = React.useState("");
    const [gender, setGender] = React.useState("");
    const [dob, setDob] = React.useState();
    const [doj, setDoj] = React.useState();

    const [dobSend, setDobSend] = React.useState("");
    const [dojSend, setDojSend] = React.useState("");
    const [error, setError] = React.useState({
        "id": '',
        "name": '',
        "department": '',
        "designation": '',
        "gender": '',
        "dob": '',
        "doj": ''
    });

    // const [pwd, setPwd] = React.useState("");

    const dateFormat = e => {
        const year = e['$y'];
        const month = e['$M']+1;
        const day = e['$D'];
        let date_in_format = year.toString() + "-";
        date_in_format += (month < 10)?'0':'';
        date_in_format += month.toString() + "-";
        date_in_format += (day < 10)?'0':'';
        date_in_format += day.toString();
        return date_in_format;
    }
    const dateJoinHandler = e => {
        const date_in_format = dateFormat(e);
        console.log(date_in_format);
        setDoj(e);
        setDojSend(date_in_format);
    }

    const dateBirthHandler = e => {
        const date_in_format = dateFormat(e);
        console.log(date_in_format);
        setDob(e);
        setDobSend(date_in_format);
    }

    async function handleSubmit(e) {
        e.preventDefault()
        // console.log("Registration successful");
        setError({
            "id": empid? '': 'Please enter Employee ID',
            "name": name? '': 'Please enter Employee Name',
            "department": dept? '': 'Please enter Employee Department',
            "designation": des? '': 'Please enter Employee Designation',
            "gender": gender? '': 'Please select Employee Gender',
            "dob": dob? '': 'Please select Employee Date of Birth',
            "doj": doj? '': 'Please select Employee Date of Joining'
        })
        const response = await fetch("http://localhost:8080/api/admin/addUser", {
            method: "POST",
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
                    "password": "12345678"
                }
            )
        });
        const json = await response.json();
        console.log(json);
        console.log(response.status);
        if(response.status === 200){
            // loginUser(empid);
            navigate('/adminviewuser');
        }
        else{
            // alert("Please fill the details correctly!");
        }
    }

    return (
        <>
            <Appbar bt={"Logout"} hbtn={goToUrl} />
            <div className='register'>
                <h2>Register a New User</h2>
                <TextField id="outlined-basic" label="Employee ID" variant="outlined" className='text_register'
                    onChange={
                        e => setEmpid(e.target.value)
                    }/>
                {error.id && <p style={{color:'red'}}>{error.id}</p>}
                <TextField id="outlined-basic" label="Employee Name" variant="outlined" className='text_register'
                    onChange={
                        e => setName(e.target.value)
                    }/>
                {error.name && <p style={{color:'red'}}>{error.name}</p>}
                <TextField id="outlined-basic" label="Employee Department" variant="outlined" className='text_register'
                    onChange={
                        e => setDept(e.target.value)
                    }/>
                {error.department && <p style={{color:'red'}}>{error.department}</p>}
                <TextField id="outlined-basic" label="Employee Designation" variant="outlined" className='text_register'
                    onChange={
                        e => setDes(e.target.value)
                    }/> 
                {error.designation && <p style={{color:'red'}}>{error.designation}</p>}
                    {/* <TextField id="outlined-basic" label="Gender" variant="outlined" className='text_register'
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
                {error.gender && <p style={{color:'red'}}>{error.gender}</p>}
                {/* <TextField id="outlined-basic" label="Password" type="password" variant="outlined" className='text_login'
                    onChange={
                        e => setPwd(e.target.value)
                    }/>  */}
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={
                        ['DatePicker']
                    }>
                        <DatePicker label="Date of Birth" className='text_register'
                            value={dob}
                            format="DD/MM/YYYY"
                            onChange={dateBirthHandler}/>
                    </DemoContainer>
                </LocalizationProvider>
                {error.dob && <p style={{color:'red'}}>{error.dob}</p>}
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={
                        ['DatePicker']
                    }>
                        <DatePicker label="Date of Joining" className='text_register'
                            value={doj}
                            format="DD/MM/YYYY"
                            onChange={dateJoinHandler}/>
                    </DemoContainer>
                </LocalizationProvider>
                {error.doj && <p style={{color:'red'}}>{error.doj}</p>}
                <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Add User</Button>
                <Button variant='contained' className='register_button'><Link style={{textDecoration:'none', color:'white'}} to="/adminviewuser">View All Users</Link></Button>
            </div>
        </>
    )
}
export default AdminAddUser;
