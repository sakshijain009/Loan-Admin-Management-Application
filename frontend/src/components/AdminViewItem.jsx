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

const AdminViewItem = () => {

    const [data, setData] = useState([])
  
    useEffect(() => {
        const data = async () => {
            const res = await fetch('http://localhost:8080/getAllItems', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
            }})
            const dt = await res.json();
            console.log(dt);
            setData(dt);
        }
        data();
      // setData(json)
    }, [])

    const [deleteRow, setDeleteRow] = useState("");

    const deleteHandler = () => {
        console.log(deleteRow);
        const data = async () => {
            const res = await fetch(`http://localhost:8080/api/admin/removeItem/${deleteRow}`, {
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
        console.log("Item id", deleteRow);
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
            <StyledTableCell align="center">Item Id</StyledTableCell>
            <StyledTableCell align="center">Item Category</StyledTableCell>
            <StyledTableCell align="center">Item Description</StyledTableCell>
            <StyledTableCell align="center">Item Make</StyledTableCell>
            <StyledTableCell align="center">Item Status</StyledTableCell>
            <StyledTableCell align="center">Item Value</StyledTableCell>
            <StyledTableCell align="center">Edit</StyledTableCell>
            <StyledTableCell align="center">Delete</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((row) => (
            <StyledTableRow key={row.item_id}>
              <StyledTableCell align="center">{row.item_id}</StyledTableCell>
              <StyledTableCell align="center">{row.category || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.description || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.make || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.status || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.value || "-"}</StyledTableCell>
              <StyledTableCell align="center"><Link to={`/adminedituser/${row.item_id}`}>Edit</Link></StyledTableCell>
              <StyledTableCell align="center"><button onClick={() => setDeleteRow(row.item_id)}>Delete</button></StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
      </div>

    )
}

export default AdminViewItem