import React, {useEffect, useState} from 'react';
import Appbar from '../../components/Appbar';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button'
import Select from '@mui/material/Select';
import {TextField} from '@mui/material';
import {Link, Navigate, useNavigate} from 'react-router-dom';
import CreditScoreIcon from '@mui/icons-material/CreditScore';
import '../ApplyLoans.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function AdminAddItem() {
    let goToUrl;
    const navigate = useNavigate();
    if(sessionStorage.getItem("admin") === null) {
        navigate("/loginadmin");
    } else {
        goToUrl = "/adminhome";
    }
    const [category, setCategory] = useState("");
    const [itemMake, setItemMake] = useState("");
    const [value, setValue] = useState(0);
    const [description, setDescription] = useState("");
    const [issue, setIssue] = useState("");
    const [types, setTypes] = useState([]);
    const [error, setError] = React.useState({
        "description":'',
        "make":'',
        "category":'',
        "value":'',
        "status" : ''
    });

    useEffect(() =>{
        const data = async () => {
            const response = await fetch("http://localhost:8080/getAllTypes");
            const json = await response.json();
            const res = JSON.stringify(json);
            if(response.status===404)
            {
                toast("No Item Categories Available");
            }
            else
            {
                setTypes(json);
            }
        };
        data();
    }, []);

    const submitHandler = async (e) => {
        e.preventDefault();
        setError({
            "description": description ? "" : "Description is required",
            "make": itemMake ? "" : "Item Make is required",
            "category": category ? "" : "Category is required",
            "value": value !== 0 ? "" : "Value is required",
            "status" : issue ? "" : "Issue Status is required"
        })
        try {
            // if(error.description || error.make || error.category || error.value || error.status) {
            //     throw new Error("Invalid Form");
            // }
            // const data = async () => {
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
            // };
            // data();
        } catch (err) {
            console.log(err);
        }
        
    }

    return (
        <>
            <Appbar bt={"Logout"} hbtn={goToUrl}/>
            <div className="loan__container">
                <h3 className="text-center py-3 pt-5">Add a New Item</h3>
                <div className="loan-select">
                    <div className="loan-form">
                        {/* <TextField label={"Item ID"}/> */}
                        {/* <TextField id="outlined-basic" label="Category" variant="outlined" className='text_register'
                    onChange={
                        e => setCategory(e.target.value)
                    }/> */}
                    <DropdownItems flag={0}
                        val={category}
                        setVal={setCategory}
                        lab={"Select Categories"}
                        arr={types}/>
                    {error.category && <p style={{color:'red'}}>{error.category}</p>}
                        <TextField id="outlined-basic" label="Item Make" variant="outlined" className='text_register'
                    onChange={
                        e => setItemMake(e.target.value)
                    }/>
                    {error.make && <p style={{color:'red'}}>{error.make}</p>}
                        <TextField id="outlined-basic" label="Item Description" variant="outlined" className='text_register'
                    onChange={
                        e => setDescription(e.target.value)
                    }/>
                    {error.description && <p style={{color:'red'}}>{error.description}</p>}
                        <TextField id="outlined-basic" label="Issue Status" variant="outlined" className='text_register'
                    onChange={
                        e => setIssue(e.target.value)
                    }/>
                    {error.status && <p style={{color:'red'}}>{error.status}</p>}
                        <TextField id="outlined-basic" label="Item Value" type='number' variant="outlined" className='text_register'
                    onChange={
                        e => setValue(parseInt(e.target.value))
                    }/>
                    {error.value && <p style={{color:'red'}}>{error.value}</p>}

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
            <ToastContainer/>
        </>
    )
}

function DropdownItems({
    flag,
    val,
    setVal,
    lab,
    arr
}) {

    return (
        <div>
            <FormControl autoWidth
                sx={
                    {
                        m: 1,
                        minWidth: 450
                    }
            }>
                <InputLabel id="demo-simple-select-autowidth-label">
                    {lab}</InputLabel>
                <Select labelId="demo-simple-select-autowidth-label" id="demo-simple-select-autowidth"
                    value={val}
                    onChange={
                        e => {setVal(e.target.value);}
                    }
                    autoWidth
                    label={lab}>
                    {
                    Array.from(arr).map((ele) => (
                        <MenuItem key={ele}
                            value={ele}>
                            {ele}</MenuItem>
                    ))
                } </Select>
            </FormControl>
        </div>
    )
}



export default AdminAddItem
