import React from 'react';
import Appbar from './Appbar';

const ApplyLoans = ({name}) => {
  return (
    <>
            <Appbar />
            <div className="loan__container">
                <h1>Loan Management Application</h1>
                <hr />
                <h3>Select Product and Apply for Loan</h3>
                <div className="loan-select">
                    <label for="employee">Employee ID</label>
                    <input type="text" id="employee" placeholder="name" />

                    <label for="category">Item Category</label>
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


                    <p>Item Value    {item.value}</p>

                </div>
            </div>
    </>
  )
}

export default ApplyLoans