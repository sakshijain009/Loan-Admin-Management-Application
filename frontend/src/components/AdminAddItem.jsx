import React, {useEffect, useState} from 'react';
import Appbar from './Appbar';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button'
import Select from '@mui/material/Select';
import {TextField} from '@mui/material';
import {Link, Navigate, useNavigate} from 'react-router-dom';
import CreditScoreIcon from '@mui/icons-material/CreditScore';
import './ApplyLoans.css'

function AdminAddItem() {
    const navigate=useNavigate();
    const [category, setCategory] = useState("");
    const [itemMake, setItemMake] = useState("");
    const [value, setValue] = useState(0);
    const [description, setDescription] = useState([]);
    const [issue, setIssue] = useState("");


    function submitHandler() {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/api/admin/addItem`, {
                method: 'POST',
                headers: {
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    "description":description,
                    "make":itemMake,
                    "category":category,
                    "value":value,
                    "status" : issue
                })
            });
            // const json = await response.json();
            // alert(json.message);
            // sessionStorage.setItem("itemsDB", res);
            if(response.status===200)
            {
                navigate("/adminviewitem");
            }
        };
        data();
    }

    return (
        <>
            <Appbar/>
            <div className="loan__container">
                <h3 className="text-center py-3 pt-5">Add Item</h3>
                <div className="loan-select">
                    <div className="loan-form">
                        {/* <TextField label={"Item ID"}/> */}
                        <TextField id="outlined-basic" label="Category" variant="outlined" className='text_register'
                    onChange={
                        e => setCategory(e.target.value)
                    }/>
                        <TextField id="outlined-basic" label="Item Make" variant="outlined" className='text_register'
                    onChange={
                        e => setItemMake(e.target.value)
                    }/>
                        <TextField id="outlined-basic" label="Item Description" variant="outlined" className='text_register'
                    onChange={
                        e => setDescription(e.target.value)
                    }/>
                        <TextField id="outlined-basic" label="Issue Status" variant="outlined" className='text_register'
                    onChange={
                        e => setIssue(e.target.value)
                    }/>
                        <TextField id="outlined-basic" label="Item Value" variant="outlined" className='text_register'
                    onChange={
                        e => setValue(e.target.value)
                    }/>

                    </div>
                    <Button variant="contained" className='apply_loan'
                    onClick={submitHandler}
                        style={
                            {
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'center'
                            }
                    }><CreditScoreIcon/>Add Item</Button>
                </div>
                <div style={{display:'flex', justifyContent:'center'}} className="container m-auto">
                    <button className="btn btn-success" onClick={()=>(navigate('/adminviewitem'))}>View Items</button>
                </div>
            </div>
        </>
    )
}

export default AdminAddItem
