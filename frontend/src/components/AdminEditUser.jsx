import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
import '../Pages/Register.css'

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
// import {Link, useNavigate, useParams} from 'react-router-dom';
import { InputAdornment } from '@mui/material';
import { Modal } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function AdminEditUser({id, show, handleClose, setEditDone}) {
    // const navigate = useNavigate();
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

    const [error, setError] = React.useState({
        "name": '',
        "department": '',
        "designation": '',
        "gender": '',
        "dob": '',
        "doj": ''
    });

    useEffect(() => {
        const data = async () => {
            console.log(id);
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

        setError({
            "name": name? '': 'Please enter Employee Name',
            "department": dept? '': 'Please enter Employee Department',
            "designation": des? '': 'Please enter Employee Designation',
            "gender": gender? '': 'Please select Employee Gender',
            "dob": dobSend? '': 'Please select Employee Date of Birth',
            "doj": dojSend? '': 'Please select Employee Date of Joining'
        });
        try {
            

        if(!dateIsValid(new Date(dobSend))) {
            throw new Error("Enter correct DOB in the format YYYY-MM-DD");
        }
        if(!dateIsValid(new Date(dojSend))) {
            throw new Error("Enter correct DOJ in the format YYYY-MM-DD");
        }
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
        console.log(response.status);
        if(response.status === 200){
            handleClose();
            setEditDone(prev => !prev);
        }
        else{
            toast(json.message);
        }

    } catch (error) {
        console.log(error)
    }
    }

    return (
        // <>
        <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit User</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <div className='modal_register'>
                {/* <h2>Edit User</h2> */}
                <TextField className='text_register' disabled placeholder='Employee ID' InputProps={{
        endAdornment: <InputAdornment position="end">{empid}</InputAdornment>,
                    }} />
                <TextField id="outlined-basic" label='Employee Name' variant='outlined' className='text_register' value={name}
                    onChange={
                        e => setName(e.target.value)
                    }/>
                    {error.name && <p style={{color:'red'}}>{error.name}</p>}
                <TextField id="outlined-basic" label="Employee Department" variant='outlined' className='text_register' value={dept}
                    onChange={
                        e => setDept(e.target.value)
                    }/>
                    {error.department && <p style={{color:'red'}}>{error.department}</p>}
                <TextField id="outlined-basic" label="Employee Designation" variant='outlined'  className='text_register' value={des}
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

                <TextField id="outlined-basic" label="Date of Birth" variant='outlined' className='text_register' value={dobSend}
                    onChange={
                        e => setDobSend(e.target.value)
                    }/>
                    {error.dob && <p style={{color:'red'}}>{error.dob}</p>}

                <TextField id="outlined-basic" label="Date of Joining" variant='outlined'  className='text_register' value={dojSend}
                    onChange={
                        e => setDojSend(e.target.value)
                    }/>
                    {error.doj && <p style={{color:'red'}}>{error.doj}</p>}
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

                {/* <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Update User</Button> */}
            </div>
            <ToastContainer />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Update User
          </Button>
        </Modal.Footer>
      </Modal>
        // </>
    )
}
export default AdminEditUser;
