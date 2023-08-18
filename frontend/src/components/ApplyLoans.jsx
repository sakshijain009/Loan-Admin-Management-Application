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

function ApplyLoans({user, bt}) {

    const [category, setCategory] = useState("");
    const [itemMake, setItemMake] = useState("");
    const [item, setItem] = useState("");
    const [value, setValue] = useState(0);
    const [categories, setCategories] = useState([]);
    const [makeArr, setMakeArr] = useState([]);
    const [description, setDescription] = useState([]);
    const tempCategories = [];
    useEffect(() => {
        const data = async () => {
            const response = await fetch("http://localhost:8080/getAllCategory");
            const json = await response.json();
            const res = JSON.stringify(json);
            // sessionStorage.setItem("itemsDB", res);
            setCategories(json);
        };
        data();
    }, []);

    useEffect(() => {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/${category}/getAllMake`);
            const json = await response.json();
            const res = JSON.stringify(json);
            // sessionStorage.setItem("itemsDB", res);
            setMakeArr(json);
            setItemMake("")
            setItem("")
            setValue(0)
        };
        data();
    }, [category]);

    useEffect(() => {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/${category}/${itemMake}/getAllDescriptions`);
            const json = await response.json();
            const res = JSON.stringify(json);
            // sessionStorage.setItem("itemsDB", res);
            setDescription(json);
        };
        data();
    }, [itemMake]);

    useEffect(() => {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/${category}/${itemMake}/${item}/getItem`);
            const json = await response.json();
            const res = JSON.stringify(json.value);
            console.log(json);
            // sessionStorage.setItem("itemsDB", res);
            setValue(json.value);
        };
        data();
    }, [item]);

    function submitHandler() {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/api/users/applyLoan`, {
                method: 'POST',
                headers: {
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    "employee_id":user,
                    "item_description":item,
                    "item_make":itemMake,
                    "item_category":category,
                    "item_value":value
                })
            });
            const json = await response.json();
            alert(json.message);
            // sessionStorage.setItem("itemsDB", res);
            
        };
        data();
    }

    // useEffect((category) => {
    //     const data = async () => {
    //         const response = await fetch(`http://localhost:8080/getallItems/${category}`);
    //         const json = await response.json();
    //         sessionStorage.setItem("itemsDB", JSON.stringify(json));
    //     };
    //     data();
    // }, [category]);

    return (
        <>
            <Appbar hbtn={"1"} bt={bt}/>
            <div className="loan__container">
                <h3 className="text-center py-3 pt-5">Select Product and Apply for Loan</h3>
                <div className="loan-select">
                    <div className="loan-form">
                        <Fixed lab={"Employee ID"}
                            value={user}/>
                        <DropdownItem flag={0}
                            val={category}
                            setVal={setCategory}
                            lab={"Select Categories"}
                            arr={categories}/>
                        <DropdownItem flag={1}
                            val={itemMake}
                            setVal={setItemMake}
                            lab={"Select Item Make"}
                            arr={makeArr}/>
                        <DropdownItem flag={2}
                            val={item}
                            setVal={setItem}
                            lab={"Select Description"}
                            arr={description}/>
                        <Fixed lab={"Price"}
                            value={value}/>

                    </div>
                    <Button variant="contained" className='apply_loan'
                    onClick={submitHandler}
                        style={
                            {
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'center'
                            }
                    }><CreditScoreIcon/>Apply Loan</Button>
                </div>
            </div>
        </>
    )
}

function Fixed({lab, value}) {
    return (
        <FormControl sx={
                {
                    m: 1,
                    minWidth: 450
                }
            }
            disabled>
            <InputLabel id="demo-simple-select-disabled-label">
                {value}</InputLabel>
            <TextField labelId="demo-simple-select-disabled-label" id="demo-simple-select-disabled"
                value={value}
                label={lab}></TextField>
        </FormControl>
    )
}

function DropdownItem({
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
                            if (flag == 0) {
                                const data = async () => {
                                    const response = await fetch(`http://localhost:8080/${val}/getAllMake`, {
                                        method: 'GET',
                                        headers: {
                                            'Content-Type': 'application/json'
                                        }
                                    });
                                    const json = await response.json();
                                    sessionStorage.setItem("allMake", JSON.stringify(json));
                                    console.log("Yoyoyo")
                                    console.log(json);
                                };
                                data();
                            }
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

export default ApplyLoans
