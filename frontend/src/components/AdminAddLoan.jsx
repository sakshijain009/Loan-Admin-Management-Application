import React, {useEffect, useState} from 'react';
import Appbar from './Appbar';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button'
import Select from '@mui/material/Select';
import {TextField} from '@mui/material';
import CreditScoreIcon from '@mui/icons-material/CreditScore';
import './ApplyLoans.css'
import DropdownItem from 'react-bootstrap/esm/DropdownItem';
import {useNavigate} from 'react-router-dom'

function AdminAddLoan() {

    const navigate = useNavigate();
    const [loanType, setLoanType] = useState(["FURNITURE", "MEDICAL", "VEHICLE", "HOME_REMODELLING", "CAR_FINANCE", "HOME_EQUITY" ]);
    const [duration, setDuration] = useState("");
    const [category, setCategory] = useState("");

    function submitHandler() {
        console.log(category);
        const data = async () => {
            const response = await fetch(`http://localhost:8080/api/admin/addLoan`, {
                method: 'POST',
                headers: {
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    "type":category,
                    "duration":duration
                })
            });
            const json = await response.json();
            console.log(json.message);
            alert(json.message);
            setCategory("");
            setDuration("");
            // sessionStorage.setItem("itemsDB", res);
            
        };
        data();
    }

    // useEffect(() => {
    //     const data = async () => {
    //         const response = await fetch(`http://localhost:8080/getallItems`);
    //         const json = await response.json();
    //         setLoanType(json);
    //     };
    //     data();
    // }, []);

    return (
        <>
            <Appbar/>
            <div className="loan__container">
                <h3 className="text-center py-3 pt-5">Add Loan Master Data</h3>
                <div className="loan-select">
                    <div className="loan-form" 
                    style=
                        {{
                            display: 'flex',
                            flexDirection: 'column',
                            justifyContent: 'center',
                        }}
                        >

                        <DropdownItems
                            val={category}
                            setVal={setCategory}
                            label={"Select Categories"}
                            arr={loanType}/>
                        <TextField sx={{m: 1,minWidth: 450}} 
                            label={"Duration"}
                            type="number"
                            variant="outlined"
                            value={duration}
                            onChange={e => setDuration(e.target.value)}/>
                        

                    </div>
                    <Button variant="contained" className='apply_loan'
                    onClick={submitHandler}
                        style={
                            {
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'center'
                            }
                    }><CreditScoreIcon/>Add Loan</Button>
                    
                </div>
            </div>
            <div style={{display:'flex', justifyContent:'center'}} className="container m-auto">
            <button className="btn btn-success" onClick={()=>(navigate('/adminviewloan'))}>View Loans</button>
            </div>
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
                        e => {
                            setVal(e.target.value);
                            // if (flag == 0) {
                            //     const data = async () => {
                            //         const response = await fetch(`http://localhost:8080/${val}/getAllMake`, {
                            //             method: 'GET',
                            //             headers: {
                            //                 'Content-Type': 'application/json'
                            //             }
                            //         });
                            //         const json = await response.json();
                            //         sessionStorage.setItem("allMake", JSON.stringify(json));
                            //         console.log("Yoyoyo")
                            //         console.log(json);
                            //     };
                            //     data();
                            // }
                        }
                    }
                    autoWidth
                    label={lab}>
                    {
                    arr.map((ele) => (
                        <MenuItem key={ele}
                            value={ele}>
                            {ele}</MenuItem>
                    ))
                } </Select>
            </FormControl>
        </div>
    )
}


export default AdminAddLoan
