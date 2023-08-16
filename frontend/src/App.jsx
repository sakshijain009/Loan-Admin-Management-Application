import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Register from './components/Register';
import Login from './components/Login';
import Dash from './components/Dash';
import AdminLogin from './components/AdminLogin';
import Dashboard from './components/Dashboard';
import ApplyLoans from './components/ApplyLoans';
import ViewLoanDetails from './components/ViewLoanDetails'

function App() {
  
  const [user,setUser] = useState("");

  useEffect(() => {
    const username = sessionStorage.getItem("username");
    setUser(username)
  },[])

  const loginUser = (username) =>{
    sessionStorage.setItem("username",username);
    setUser(username)
  }

  const logoutUser = () =>{
    sessionStorage.removeItem("username");
    setUser("")
  }

  return (
    <>
      <Router>
        <Routes>
        <Route path='/register' element={<Register user={user} loginUser={loginUser}/> } />
          <Route path='/login' element={<Login  user={user} loginUser={loginUser} /> } />
          <Route path='/loginadmin' element={<AdminLogin/>} />
          <Route path='/' element={<Dashboard/>} />
          <Route path='/home' element={<Dash user={user} loginUser={loginUser}/>} />
          <Route path='/applyloan' element={<ApplyLoans user={user} loginUser={loginUser}/>} />
          <Route path='/viewloan' element={<ViewLoanDetails user={user} loginUser={loginUser}/>} />
        </Routes>
      </Router>
    </>
  )
}

export default App
