import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Register from './components/Register';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import AdminLogin from './components/AdminLogin';


function App() {

  return (
    <>
      <Router>
        <Routes>
        <Route path='/loginadmin' element={<AdminLogin/>} />
          <Route path='/register' element={<Register/> } />
          <Route path='/login' element={<Login/> } />
          <Route path='/' element={<Dashboard/>} />
        </Routes>
      </Router>
    </>
  )
}

export default App
