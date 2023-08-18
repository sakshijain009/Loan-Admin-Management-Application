
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
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

const ViewLoanDetails = ({user}) => {

    const [data, setData] = useState([])
  
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
  
    const renderTable = () => {
      return data.map(user1 => {
        return (
          <div style={{display:'flex', justifyContent:'center'}}>
          <table>
          <tr>
            <td>{user1.loan_id}</td>
            <td>{user1.duration}</td>
            <td>{user1.type}</td>
            <td>{user1.card_id}</td>
          </tr>
          </table>
          </div>
        )
      })
    }
  
    return (
      <div>
        <Appbar/>
        {/* <h2 id="title">Loans Applied</h2>
        <table id="users">
          <thead>
            <tr>
              <th>Loan_id</th>
              <th>Duration</th>
              <th>Loan type</th>
              <th>Card_id</th>
            </tr>
          </thead>
          <tbody>{renderTable()}</tbody>
        </table> */}
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Loan ID</StyledTableCell>
            <StyledTableCell align="right">Duration</StyledTableCell>
            <StyledTableCell align="right">Loan Type</StyledTableCell>
            <StyledTableCell align="right">Card ID</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((row) => (
            <StyledTableRow>
              <StyledTableCell align="right">{row.loan_id}</StyledTableCell>
              <StyledTableCell align="right">{row.duration}</StyledTableCell>
              <StyledTableCell align="right">{row.type}</StyledTableCell>
              <StyledTableCell align="right">{row.card_id}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>

      </div>
    )
}

export default ViewLoanDetails