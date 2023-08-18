import Appbar from '../Appbar';
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

const AdminViewUser = () => {

    const [data, setData] = useState([])
  
    useEffect(() => {
        const data = async () => {
            const res = await fetch('http://localhost:8080/admin/getAllUser', {
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
    
    return (
      <div>
        <Appbar/>
        <h3 className='text-center pt-5' >Customer Master Data Details</h3>
        <div className='mx-auto p-4'>
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">Employee Id</StyledTableCell>
            <StyledTableCell align="center">Employee name</StyledTableCell>
            <StyledTableCell align="center">Designation</StyledTableCell>
            <StyledTableCell align="center">Department</StyledTableCell>
            <StyledTableCell align="center">Gender</StyledTableCell>
            <StyledTableCell align="center">Date Of Birth</StyledTableCell>
            <StyledTableCell align="center">Date Of Joining</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((row) => (
            <StyledTableRow key={row.id}>
              <StyledTableCell align="center">{row.id}</StyledTableCell>
              <StyledTableCell align="center">{row.name}</StyledTableCell>
              <StyledTableCell align="center">{row.designation}</StyledTableCell>
              <StyledTableCell align="center">{row.department}</StyledTableCell>
              <StyledTableCell align="center">{row.gender}</StyledTableCell>
              <StyledTableCell align="center">{row.dob}</StyledTableCell>
              <StyledTableCell align="center">{row.doj}</StyledTableCell>
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