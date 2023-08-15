import React, { useEffect, useState } from 'react';
import Appbar from './Appbar';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { TextField } from '@mui/material';

function ApplyLoans({user}){

    const [category, setCategory] = useState("");
    const [itemMake, setItemMake] = useState("");
    const [item, setItem] = useState("");
    const [value, setValue] = useState(0);
    const [categories, setCategories] = useState(["Furniture", "Home", "Kitchen", "Electronics"]);
    const [makeArr, setMakeArr] = useState(["Wood", "Glass", "Steel"]);
    const [description, setDescription] = useState(["Chair", "Table", "TV"]);

    useEffect(() => {
        const data = async () => {
            const response = await fetch("http://localhost:8080/getallItems");
            const json = await response.json();
            sessionStorage.setItem("itemsDB", JSON.stringify(json));
        };
        data();
    }, []);

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
            <Appbar />
            <div className="loan__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>Select Product and Apply for Loan</h3> <br/><br/>
                <div className="loan-select" style={{display:'flex', justifyContent:'center'}}>
                    <div>
                    <Fixed lab={"Employee ID"} value={user} />
                    <DropdownItem val={category} setVal={setCategory} lab={"Select Categories"} arr={categories} />
                    <DropdownItem val={itemMake} setVal={setItemMake} lab={"Select Item Make"} arr={makeArr} />
                    <DropdownItem val={item} setVal={setItem} lab={"Select Item"} arr={description}/>

                    <Fixed lab={"Price"} value={value} />
                    </div>
                </div>
            </div>
    </>
  )
}

function Fixed({lab, value}){
    return (
        <FormControl sx={{ m: 1, minWidth: 450 }} disabled>
            <InputLabel id="demo-simple-select-disabled-label">{value}</InputLabel>
            <TextField
                labelId="demo-simple-select-disabled-label"
                id="demo-simple-select-disabled"
                value={value}
                label={lab}
            ></TextField>
        </FormControl>
    )
}

function DropdownItem({val, setVal, lab, arr}){
    return (
    <div>
        <FormControl autoWidth sx={{ m: 1, minWidth: 450}}>
            <InputLabel id="demo-simple-select-autowidth-label">{lab}</InputLabel>
                <Select
                    labelId="demo-simple-select-autowidth-label"
                    id="demo-simple-select-autowidth"
                    value={val}
                    onChange={e => setVal(e.target.value)}
                    autoWidth
                    label={lab}
                >
                {arr.map((ele) => (
                    <MenuItem value={ele}>{ele}</MenuItem>
                ))}
                </Select>
        </FormControl>
    </div>
)}

export default ApplyLoans