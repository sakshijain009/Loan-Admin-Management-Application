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

function AdminEditItem({id, show, handleClose, setEditDone}) {
    // const navigate = useNavigate();
    const [itemId, setItemId] = React.useState("");
    const [category, setCategory] = React.useState("");
    const [make, setMake] = React.useState("");
    const [description, setDescription] = React.useState("");
    const [value, setValue] = React.useState("");
    const [status, setStatus] = useState("");

    // const {id} = useParams();

    useEffect(() => {
        const data = async () => {
            console.log(id);
            const response = await fetch(`http://localhost:8080/api/admin/getItemById/${id}`);
            const res = await response.json();
            setItemId(res.item_id);
            setCategory(res.category);
            setMake(res.make);
            setDescription(res.description);
            setValue(res.value);
            setStatus(res.status);
        }
        data();
    }, [id])

    async function handleSubmit(e) {
        e.preventDefault();
        try {
        const response = await fetch("http://localhost:8080/api/admin/updateItem", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(
                {
                    "item_id": itemId,
                    "category": category,
                    "make": make,
                    "description": description,
                    "value": value,
                    "status": status
                }
            )
        });
        const json = await response.json();
        console.log(json);
        console.log(response.status);
        if(response.status === 200){
            // navigate('/adminviewitem');
            handleClose();
            setEditDone(prev => !prev);
            // alert(json.message);
            // window.location.reload();
        }
        else{
            alert(json.message);
        }
            
    } catch (error) {
        console.log(error)
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
                <h2>Edit Item</h2>
                <TextField className='text_register' disabled placeholder='Item ID' InputProps={{
        endAdornment: <InputAdornment position="end">{itemId}</InputAdornment>,
                    }} />
                <TextField id="outlined-basic" placeholder="Category" disabled className='text_register' value={category}
                    onChange={
                        e => setCategory(e.target.value)
                    }/>
                <TextField id="outlined-basic" placeholder="Item Make" disabled  className='text_register' value={make}
                    onChange={
                        e => setMake(e.target.value)
                    }/>
                    <TextField id="outlined-basic" placeholder="Description"  className='text_register' value={description}
                    onChange={
                        e => setDescription(e.target.value)
                    }/>
                    <TextField id="outlined-basic" placeholder="Value"  className='text_register' value={value}
                    onChange={
                        e => setValue(e.target.value)
                    }/>
                    <TextField id="outlined-basic" placeholder="Status" disabled className='text_register' value={status}
                    onChange={
                        e => setStatus(e.target.value)
                    }/>


                {/* <Button variant="contained" className='register_button'
                    onClick={handleSubmit}>Update Item</Button> */}
            </div>
            </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Update Item
          </Button>
        </Modal.Footer>
      </Modal>
        // </>
    )
}
export default AdminEditItem;