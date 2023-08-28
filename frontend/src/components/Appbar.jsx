import React, { useEffect } from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import { useNavigate } from "react-router-dom";
import HomeRoundedIcon from "@mui/icons-material/HomeRounded";
import PersonIcon from "@mui/icons-material/Person";
import Popover from '@mui/material/Popover';

export default function Appbar({ hbtn, bt }) {
  const navigate = useNavigate();
  const [prof, setProf] = React.useState("");
  const [det, setDet] = React.useState([]);
  useEffect(() => {
    console.log(prof);
    if (sessionStorage.getItem("username") != null) {
      // setProf(sessionStorage.getItem("username"));
      const id = sessionStorage.getItem("username");
      const data = async () => {
        const response = await fetch(
          `http://localhost:8080/api/users/profile/${id}`
        );
        const res = await response.json();
        if (response.status === 200) {
          setProf(res.name);
          setDet(res);
        } else {
          setProf(id);
        }
      };
      data();
    }
    if (sessionStorage.getItem("admin") != null) {
      const p = JSON.parse(sessionStorage.getItem("admin")).username;
      setProf(p);
    }
  }, [prof]);

  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClickProf = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleCloseProf = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);
  const id = open ? 'simple-popover' : undefined;

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" style={{ backgroundColor: "#4477CE" }}>
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={() => navigate(hbtn)}
          >
            <HomeRoundedIcon />
          </IconButton>
          <Typography
            className="d-none d-sm-block"
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, paddingLeft: 1 }}
          >
            Loan Management
          </Typography>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              marginRight: 30,
            }}
          >
            {prof && (
              <Box
                component="span"
                sx={{ flexGrow: 1, p: 1, borderRadius: 8 }}
                className="bg-light text-dark"
               
              >
                <Typography variant="h9" className="font-weight-bold px-2"  onClick={handleClickProf}>
                  <PersonIcon />
                  Welcome {prof}
                </Typography>
                
                <Popover
              id={id}
              open={open}
              anchorEl={anchorEl}
              onClose={handleCloseProf}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              sx={{padding:'10px'}}
              >
              <Typography sx={{p:0.5}}><b>Designation</b> {det.designation}</Typography>
              <Typography sx={{p:0.5 }}><b>Department</b> {det.department}</Typography>
              <Typography sx={{ p: 0.5 }}><b>Gender</b> {det.gender==='M'?'Male':(det.gender==='F'?'Female':'Others')}</Typography>
              <Typography sx={{ p: 0.5 }}><b>Date of Birth</b> {det.dob}</Typography>
              <Typography sx={{ p: 0.5 }}><b>Date of Joining</b> {det.doj}</Typography>
            </Popover>
              </Box>
            )}
          </div>
          {bt && (
            <Button
              variant="outlined"
              color="inherit"
              onClick={() => navigate(`/${bt}`)}
            >
              {bt}
            </Button>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
}
