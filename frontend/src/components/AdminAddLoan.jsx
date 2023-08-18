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

function AdminAddLoan() {

    const [loanId, setLoanId] = useState("");
    const [loanType, setLoanType] = useState([]);
    const [duration, setDuration] = useState("");
    const [category, setCategory] = useState("");

    function submitHandler() {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/api/users/applyLoan`, {
                method: 'POST',
                headers: {
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    "loan_id":loanId,
                    "loan_type":category,
                    "duration":duration
                })
            });
            const json = await response.json();
            alert(json.message);
            // sessionStorage.setItem("itemsDB", res);
            
        };
        data();
    }

    useEffect(() => {
        const data = async () => {
            const response = await fetch(`http://localhost:8080/getallItems`);
            const json = await response.json();
            setLoanType(json);
        };
        data();
    }, []);

    return (
        <>
            <Appbar/>
            <div className="loan__container">
                <h3 className="text-center py-3 pt-5">Loan Cards Master Data Details</h3>
                <div className="loan-select">
                    <div className="loan-form">
                        <TextField label={"Loan ID"}
                        variant="outlined"
                            value={loanId}
                            onChange={e => setLoanId(e)}/>
                        <DropdownItem
                            val={category}
                            setVal={setCategory}
                            label={"Select Categories"}
                            arr={loanType}/>
                        <TextField label={"Duration"}
                        variant="outlined"
                            value={duration}
                            onChange={e => setDuration(e)}/>

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
        </>
    )
}

export default AdminAddLoan
