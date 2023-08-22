import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
import Appbar from './Appbar';
import './Register.css'

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import { useState, useEffect } from 'react';
// import {Link, useNavigate, useParams} from 'react-router-dom';
import { InputAdornment } from '@mui/material';
import { Modal } from 'react-bootstrap';

function AdminEditUser({id, show, handleClose, setEditDone}) {
    // const navigate = useNavigate();
    const [loan_id, setLoan_id] = React.useState("");
    const [type, setType] = React.useState("");
    const [duration, setDuration] = React.useState("");
 
    // const {id} = useParams();

    useEffect(() => {
        const data = async () => {
            // console.log(id);
            const response = await fetch(`http://localhost:8080/api/admin/getLoan/${id}`);
            const res = await response.json();
            setLoan_id(res.loan_id);
            setType(res.type);
            setDuration(res.duration);
        }
        data();
    }, [loan_id])


    async function handleSubmit(e) {
        e.preventDefault();
        try {
        

        // console.log("Registration successful");
        const response = await fetch("http://localhost:8080/api/admin/updateLoan", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(
                {
                    "loan_id": loan_id,
                    "type": type,
                    "duration": duration,
                }
            )
        });
        const json = await response.json();
        console.log(json);
        console.log(response.status);
        if(response.status === 200){
            // navigate('/adminviewuser');
            handleClose();
            setEditDone(prev => !prev);
            // alert(json.message);
            // window.location.reload();
        }
        else{
            alert(json.message);
        }
            
    } catch (error) {
        alert(json.message);
    }
    }

    return (
        // <>
        //     <Appbar/>
        <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit User</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <div className='modal_register'>
                {/* <h2>Edit Loan</h2> */}
                <TextField className='text_register' disabled placeholder='Loan ID' InputProps={{
        endAdornment: <InputAdornment position="end">{loan_id}</InputAdornment>,
                    }} />
                <TextField id="outlined-basic" disabled placeholder='Loan Type' className='text_register' value={type}
                    onChange={
                        e => setType(e.target.value)
                    }/>
                <TextField id="outlined-basic" placeholder="Duration" className='text_register' value={duration}
                    onChange={
                        e => setDuration(e.target.value)
                    }/>
                <Box sx={
                    {
                        minWidth: 120,
                        paddingTop: 1
                    }
                }>
                    
                </Box>

      

                {/* <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Update Loan</Button> */}
            </div>
            </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Update Loan
          </Button>
        </Modal.Footer>
      </Modal>
        // </>
    )
}
export default AdminEditUser;
