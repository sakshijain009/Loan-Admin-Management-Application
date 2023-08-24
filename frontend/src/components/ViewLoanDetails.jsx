import Appbar from './Appbar'
import React, {useState, useEffect} from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useNavigate } from 'react-router-dom';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },

  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

const ViewLoanDetails = ({user, bt}) => {

    const [data, setData] = useState([])
    const navigate = useNavigate();
    if(sessionStorage.getItem("username") === null) {
        navigate("/login");
    } 
    useEffect(() => {
        const data = async () => {
            user = sessionStorage.getItem('username');
            const res = await fetch('http://localhost:8080/getallLoans', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "emp_id": user
            }})
            const dt = await res.json();
            console.log(dt);
            
            console.log(user)
            setData(dt);
        }
        data();
      // setData(json)
    }, [])
  
    return (
      <div>
        <Appbar hbtn={"/home"} bt={bt}/>
        <h3 className='text-center pt-5' >Viewing your Loan details</h3>
        <div className='mx-auto p-4'>
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">Loan ID</StyledTableCell>
            <StyledTableCell align="center">Duration</StyledTableCell>
            <StyledTableCell align="center">Loan Type</StyledTableCell>
            <StyledTableCell align="center">Card ID</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {Array.from(data).map((row) => (
            <StyledTableRow>
              <StyledTableCell align="center">{row.loan_id}</StyledTableCell>
              <StyledTableCell align="center">{row.duration}</StyledTableCell>
              <StyledTableCell align="center">{row.type}</StyledTableCell>
              <StyledTableCell align="center">{row.card_id}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
      </div>
    )
}

export default ViewLoanDetails