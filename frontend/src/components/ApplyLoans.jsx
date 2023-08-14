import React, { useEffect, useState } from 'react';
import Appbar from './Appbar';
import {useState} from 'react';

const ApplyLoans = ({user}) => {

    const [category, setCategory] = useState("");
    const [itemMake, setItemMake] = useState("");
    const [item, setItem] = useState("");

    useEffect(() => {
        const data = async () => {
            const response = await fetch("http://localhost:8080/getallItems");
            const json = await response.json();
            sessionStorage.setItem("itemsDB", JSON.stringify(json));
        };
        data();
    }, []);


  return (
    <>
            <Appbar />
            <div className="loan__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>Select Product and Apply for Loan</h3>
                <div className="loan-select">
                    <label for="employee">Employee ID</label>
                    <p id="employee">{user}</p>

                    {/* <label for="category">Item Category</label>
                    <select id="category" onChange={e => setCategory(e)}>
                        {categories.map((category) => (
                            <option value={category}>{category}</option>
                        ))}
                    </select>


                    <label for="item-make">Item Make</label>
                    <select id="item-make" onChange={e => setItemMake(e)}>
                        {Itemmakes.map((item) => (
                            <option value={item}>{item}</option>
                        ))}
                    </select>


                    <label for="item">Item Description</label>
                    <select id="item" onChange={e => setItem(e)}>
                        {Items.map((item) => (
                            <option value={item}>{item}</option>
                        ))}
                    </select>


                    <p>Item Value    {item.value}</p> */}

                </div>
            </div>
    </>
  )
}

export default ApplyLoans