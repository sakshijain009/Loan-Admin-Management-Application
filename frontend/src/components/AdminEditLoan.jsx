import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button'
import React from 'react';
import '../Pages/Register.css'

import Box from '@mui/material/Box';

import { useState, useEffect } from 'react';
import { InputAdornment } from '@mui/material';
import { Modal, Toast } from 'react-bootstrap';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function AdminEditUser({id, show, handleClose, setEditDone}) {
    // const navigate = useNavigate();
    const [loan_id, setLoan_id] = React.useState("");
    const [type, setType] = React.useState("");
    const [duration, setDuration] = React.useState(0);

    const [error, setError] = React.useState({
        "duration": ''
    });
 
    // const {id} = useParams();

    useEffect(() => {
        const data = async () => {
            // console.log(id);
            const response = await fetch(`http://localhost:8080/api/admin/getLoanById/${id}`);
            const res = await response.json();
            setLoan_id(res.loan_id);
            setType(res.type);
            setDuration(res.duration);
        }
        data();
    }, [loan_id])


    async function handleSubmit(e) {
        e.preventDefault();

        setError({
            "duration": duration > 0 ? "" : "Please enter a valid duration"
        })
        try {
    
            if(duration <= 0) {
                throw new Error("Please enter a valid duration");
            }
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
            toast(json.message);
            // alert(json.message);
            // window.location.reload();
        }
        else{
            toast(json.message);
        }
            
    } catch (error) {
        toast(json.message);
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
                <TextField id="outlined-basic" disabled label='Loan Type' variant='outlined' className='text_register' value={type}
                    onChange={
                        e => setType(e.target.value)
                    }/>
                <TextField id="outlined-basic" label="Duration" variant='outlined' className='text_register' value={duration}
                    type='number'
                    onChange={
                        e => setDuration(e.target.value || "0")
                    }/>
                    {error.duration && <p style={{color:'red'}}>{error.duration}</p>}
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
            <ToastContainer />
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
