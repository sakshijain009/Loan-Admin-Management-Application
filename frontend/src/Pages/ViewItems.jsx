import Appbar from '../components/Appbar';
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

const ViewItems = ({user, bt}) => {
    const navigate = useNavigate();
    if(sessionStorage.getItem("username") === null) {
        navigate("/login");
    } 
    const [data, setData] = useState([])
  
    useEffect(() => {
        const data = async () => {
            user = sessionStorage.getItem('username');
            const res = await fetch('http://localhost:8080/viewItems', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "emp_id": user
            }})
            const dt = await res.json();
            console.log(dt);
            setData(dt);
        }
        data();
    }, [])
  
  
    return (
      <div>
        <Appbar hbtn={"/home"} bt={bt}/>
        <h3 className='text-center pt-5' >Viewing Issued Item Details</h3>
        <div className='mx-auto p-4'>
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">Issue ID</StyledTableCell>
            <StyledTableCell align="center">Description</StyledTableCell>
            <StyledTableCell align="center">Make</StyledTableCell>
            <StyledTableCell align="center">Category</StyledTableCell>
            <StyledTableCell align="center">Valuation</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {Array.from(data).map((row) => (
            <StyledTableRow>
              <StyledTableCell key={row.item_id} align="center">{row.issue_id}</StyledTableCell>
              <StyledTableCell key={row.item_id} align="center">{row.item_description}</StyledTableCell>
              <StyledTableCell key={row.item_id} align="center">{row.item_make}</StyledTableCell>
              <StyledTableCell key={row.item_id} align="center">{row.item_category}</StyledTableCell>
              <StyledTableCell key={row.item_id} align="center">{row.item_value}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
      </div>

    )
}

export default ViewItems