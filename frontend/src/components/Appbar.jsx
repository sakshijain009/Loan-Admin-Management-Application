import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import { useNavigate } from 'react-router-dom';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import { Link } from 'react-router-dom';

export default function Appbar({hbtn, bt}) {
  const navigate = useNavigate();
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
          >
          <HomeRoundedIcon onClick={() => {
            if({hbtn} == "0"){
              <Link to='/'></Link>
            }
            else if({hbtn} == "1"){
              <Link to='/home'></Link>
            }
          }}/>
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1, paddingLeft:1 }}>
            Loan Management 
          </Typography>
          <Button color="inherit" onClick={()=>(navigate(`/${bt}`))}>{bt}</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
