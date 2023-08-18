import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { useNavigate } from 'react-router-dom';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';

export default function Appbar({bt}) {
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
          <HomeRoundedIcon onClick={() => (navigate('/home'))}/>
          </IconButton>
          {/* <HomeRoundedIcon onClick={() => (navigate('/home'))}/> */}
          <Typography variant="h6" component="div" sx={{ flexGrow: 1, paddingLeft:5 }}>
            Loan Management Application
          </Typography>
          <Button color="inherit" onClick={()=>(navigate(`/${bt}`))}>{bt}</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
