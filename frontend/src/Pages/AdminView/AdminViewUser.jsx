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
import AdminEditUser from '../../components/AdminEditUser';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

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
            const res = await fetch('http://localhost:8080/api/admin/getAllUser', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
            }})
            const dt = await res.json();
            if(res.status===404)
            {
              toast("No Users Available");
            }
            else
            {
              setData(dt);
            }
        }
        data();
      // setData(json)
    }, [editDone, deleteDone])



    const deleteHandler = () => {
        // console.log(deleteRow);
        const data = async () => {
            const res = await fetch(`http://localhost:8080/api/admin/removeEmployee/${deleteRow}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
            }})
            const dt = await res.json();
            console.log(dt.message);
            // setData(dt);
            setDeleteDone(prev => !prev);
            // window.location.reload();
        }
        data();
    }    

    useEffect(() => {
        // console.log("Emp id", deleteRow);
        deleteHandler()
    }, [deleteRow]);
    
    return (
      <div>
        <Appbar bt={"Logout"} hbtn={goToUrl}/>
        <h3 className='text-center pt-5' >Customer Master Data Details</h3>
        <div className='mx-auto p-4'>
        {data.length != 0 && <TableContainer component={Paper}>
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
            <StyledTableCell align="center">Edit</StyledTableCell>
            <StyledTableCell align="center">Delete</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {Array.from(data).map((row) => (
            <StyledTableRow key={row.id}>
              <StyledTableCell align="center">{row.id}</StyledTableCell>
              <StyledTableCell align="center">{row.name || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.designation || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.department || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.gender || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.dob || "-"}</StyledTableCell>
              <StyledTableCell align="center">{row.doj || "-"}</StyledTableCell>
              <StyledTableCell align="center">
                <Button variant='warning' onClick={() => {handleShow(), setEditRow(row.id)}}>Edit</Button>
              </StyledTableCell>
              <StyledTableCell align="center">
                <Button variant='danger' onClick={() => setDeleteRow(row.id)}>Delete</Button>
              </StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
}
    </div>


    {editRow && <AdminEditUser show={show} handleClose={handleClose} id={editRow} setEditDone={setEditDone} />}
    {/* <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit User</Modal.Title>
        </Modal.Header>
        <Modal.Body>

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal> */}


      <ToastContainer/>
    
  </div>

    )
}

export default AdminViewUser