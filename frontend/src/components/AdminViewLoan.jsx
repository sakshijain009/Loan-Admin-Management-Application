import Appbar from './Appbar';
import React, {useState, useEffect} from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Link } from 'react-router-dom';

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

const AdminViewUser = () => {

    const [data, setData] = useState([])
  
    useEffect(() => {
        const data1 = async () => {
            const res = await fetch('http://localhost:8080/api/admin/getAllLoan', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
            }})
            const dt = await res.json();
            console.log(dt);
            setData(dt);
            console.log(typeof(data))
        }
        data1();
      // setData(json)
    }, [])

    const [deleteRow, setDeleteRow] = useState("");

    const deleteHandler = () => {
        console.log(deleteRow);
        const data = async () => {
            const res = await fetch(`http://localhost:8080/api/admin/removeLoan/${deleteRow}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
            }})
            const dt = await res.json();
            console.log(dt.message);
            // setData(dt);
            window.location.reload();
        }
        data();
    }    

    useEffect(() => {
        console.log("Emp id", deleteRow);
        deleteHandler()
    }, [deleteRow]);
    
    return (
      <div>
        <Appbar/>
        <h3 className='text-center pt-5' >Customer Master Data Details</h3>
        <div className='mx-auto p-4'>
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">Loan Id</StyledTableCell>
            <StyledTableCell align="center">Loan Type</StyledTableCell>
            <StyledTableCell align="center">Duration</StyledTableCell>
            <StyledTableCell align="center">Edit</StyledTableCell>
            <StyledTableCell align="center">Delete</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {Array.from(data).map((row) => (
            <StyledTableRow key={row.id}>
              <StyledTableCell align="center">{row.id}</StyledTableCell>
              <StyledTableCell align="center">{row.type || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.duration || "-"}</StyledTableCell>
              <StyledTableCell align="center"><Link to={`/admineditloan/${row.id}`}>Edit</Link></StyledTableCell>
              <StyledTableCell align="center"><button className='btn btn-danger' onClick={() => setDeleteRow(row.id)}>Delete</button></StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
      </div>

    )
}

export default AdminViewUser