<<<<<<< HEAD
import React from 'react';
=======
import React, {useEffect} from 'react';
>>>>>>> 517b90df9b90e5ab36615cd1b53dd3f19300745b
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import { useNavigate } from 'react-router-dom';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import { Link } from 'react-router-dom';
import { palette } from '@mui/system';

export default function Appbar({hbtn, bt}) {
  const navigate = useNavigate();
  const [prof,setProf] = React.useState("");
  useEffect( ()=> {
    console.log(prof);
    if(sessionStorage.getItem("username") != null) {
      setProf(sessionStorage.getItem("username"));
    } 
    if(sessionStorage.getItem("admin") != null) {
      const p = JSON.parse(sessionStorage.getItem("admin")).username;
      setProf(p);
    } 
  }, [prof])
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            // onClick={() => {
            //   if({hbtn} == "0"){
            //     console.log("Redirecting to /");
            //     return(navigate('/'));
            //   }
            //   else if({hbtn} == "1"){
            //     console.log("Redirecting to /home");
            //     return(navigate('/home'));
            //   }
            // }}
            
            onClick={() => (navigate(hbtn))}
          >
          <HomeRoundedIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1, paddingLeft:1 }}>
            Loan Management 
          </Typography>
          <div style={{display:'flex', justifyContent:'center', marginRight:30}}>
          <Box component="span" sx={{flexGrow:1, p:1, borderRadius:8}} className="bg-light text-primary">
            {prof && <Typography variant="h9" className="font-weight-bold">Welcome {prof}</Typography> }
          </Box>
          </div>
          <Button variant="outlined" color="inherit" onClick={()=>(navigate(`/${bt}`))}>{bt}</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
