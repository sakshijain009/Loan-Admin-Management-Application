import Appbar from '../../components/Appbar';
import React, {useState, useEffect} from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {Button} from 'react-bootstrap';
import AdminEditItem from '../../components/AdminEditItem';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
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

  let goToUrl;
  const navigate = useNavigate();
  if(sessionStorage.getItem("admin") === null) {
      navigate("/loginadmin");
  } else {
      goToUrl = "/adminhome";
  }

    const [data, setData] = useState([]);

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [deleteRow, setDeleteRow] = useState("");
    const [editRow, setEditRow] = useState("");
    const [deleteDone, setDeleteDone] = useState(false);
    const [editDone, setEditDone] = useState(false);
  
    useEffect(() => {
        const data = async () => {
            const res = await fetch('http://localhost:8080/getAllItems')
            const dt = await res.json();
            if(res.status===200)
            {
              setData(dt);
            }
            else
            {
              setData([]);
              toast("No Items Available");
            }
        }
        data();
      // setData(json)
    }, [editDone, deleteDone])

    const deleteHandler = () => {
        console.log(deleteRow);
        const data = async () => {
            const res = await fetch(`http://localhost:8080/api/admin/removeItem/${deleteRow}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
            }})
            const dt = await res.json();
            if(res.status===200) {
              console.log(dt.message);
              setDeleteDone(prev => !prev);
            } else {
              toast(dt.message);
            }
        }
        data();
    }    

    useEffect(() => {
        console.log("Item id", deleteRow);
        deleteHandler()
    }, [deleteRow]);
    
    return (
      <div>
        <Appbar bt={"Logout"} hbtn={goToUrl}/>
        <h3 className='text-center pt-5' >Items Master Data Details</h3>
        <div style={{display:'flex', justifyContent:'center'}}>
          <Button variant="primary" className="m-2">
            <Link
              style={{
                textDecoration: "none",
                color: "white",
                fontWeight: "bold",
                fontSize: "1.2rem",
              }}
              to="/adminadditem"
            >
              Add more Items
            </Link>
          </Button>
        </div>
        <div className='mx-auto p-4'>
        {data.length != 0 && <TableContainer component={Paper}>
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
          {Array.from(data).map((row) => (
            <StyledTableRow key={row.item_id}>
              <StyledTableCell align="center">{row.item_id}</StyledTableCell>
              <StyledTableCell align="center">{row.category || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.description || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.make || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.status || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.value || "-"}</StyledTableCell>
              <StyledTableCell align="center">
                <Button variant='warning' onClick={() => {handleShow(), setEditRow(row.item_id)}}>Edit</Button>
              </StyledTableCell>
              <StyledTableCell align="center">
                <Button variant='danger' onClick={() => setDeleteRow(row.item_id)}>Delete</Button>
              </StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>}
    </div>
    {editRow && <AdminEditItem show={show} handleClose={handleClose} id={editRow} setEditDone={setEditDone} />}
    <ToastContainer />
      </div>

    )
}

export default AdminViewItem