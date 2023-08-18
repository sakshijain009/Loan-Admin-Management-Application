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

const ViewItems = ({user}) => {

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
            
            console.log(user)
            setData(dt);
        }
        data();
      // setData(json)
    }, [])
  
    // const renderTable = () => {
    //   return data.map(user1 => {
    //     return (
    //       <tr>
    //         <td>{user1.issue_id}</td>
    //         <td>{user1.item_description}</td>
    //         <td>{user1.item_make}</td>
    //         <td>{user1.item_category}</td>
    //         <td>{user1.item_value}</td>
    //       </tr>
    //     )
    //   })
    // }
  
    return (
      <div>
        <Appbar/>
        <h3 className='text-center pt-5' >Viewing your Issued item details</h3>
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
          {data.map((row) => (
            <StyledTableRow>
              <StyledTableCell align="center">{row.issue_id}</StyledTableCell>
              <StyledTableCell align="center">{row.description}</StyledTableCell>
              <StyledTableCell align="center">{row.make}</StyledTableCell>
              <StyledTableCell align="center">{row.category}</StyledTableCell>
              <StyledTableCell align="center">{row.valuation}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </div>
      </div>
        /* <h1 id="title">Items Purchased</h1>
        <table id="users">
          <thead>
            <tr>
              <th>Issue_id</th>
              <th>Item Description</th>
              <th>Item Make</th>
              <th>Item Category</th>
              <th>Item Valuation</th>
            </tr>
          </thead>
          <tbody>{renderTable()}</tbody>
        </table> */

    )
}

export default ViewItems