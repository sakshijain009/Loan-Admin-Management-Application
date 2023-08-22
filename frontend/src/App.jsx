import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Register from './components/Register';
import Login from './components/Login';
import Dash from './components/Dash';
import AdminLogin from './components/AdminLogin';
import Dashboard from './components/Dashboard';
import ApplyLoans from './components/ApplyLoans';
import ViewLoanDetails from './components/ViewLoanDetails'
import ViewItems from './components/ViewItems';
import AdminDashboard from './components/AdminDashboard';
import AdminAddUser from './components/AdminAddUser';
import AdminAddItem from './components/AdminAddItem';
import AdminAddLoan from './components/AdminAddLoan';
import AdminViewUser from './components/AdminViewUser';
import AdminViewItem from './components/AdminViewItem';
import Logout from './components/logout';
import AdminEditUser from './components/AdminEditUser';
import AdminEditItem from './components/AdminEditItem';
import AdminViewLoan from './components/AdminViewLoan';
import AdminEditLoan from './components/AdminEditLoan'

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
          <Route path='/register' element={<Register user={user} loginUser={loginUser} bt={"login"}/> } />
          <Route path='/login' element={<Login  user={user} loginUser={loginUser} bt={"register"}/> } />
          <Route path='/loginadmin' element={<AdminLogin/>} />
          <Route path='/' element={<Dashboard/>} />
          <Route path='/home' element={<Dash user={user}/>} />
          <Route path='/applyloan' element={<ApplyLoans user={user} setUser={setUser} loginUser={loginUser}  bt={"logout"}/>} />
          <Route path='/viewloan' element={<ViewLoanDetails user={user} loginUser={loginUser} bt={"logout"}/>} />
          <Route path='/viewitems' element={<ViewItems user={user} loginUser={loginUser} bt={"logout"}/>} />
          <Route path='/adminhome' element={<AdminDashboard />} />
          <Route path='/adminadduser' element={<AdminAddUser />} />
          <Route path='/adminadditem' element={<AdminAddItem />} />
          <Route path='/adminaddloan' element={<AdminAddLoan />} />
          <Route path='/adminviewloan' element={<AdminViewLoan />} />
          {/* <Route path='/admineditloan/:id' element={<AdminEditLoan />} exact /> */}
          <Route path='/adminviewuser' element={<AdminViewUser />} />
          <Route path='/adminviewitem' element={<AdminViewItem />} />
          {/* <Route path='adminedituser/:id' element={<AdminEditUser />} exact />
          <Route path='adminedititem/:id' element={<AdminEditItem />} exact /> */}
          <Route path='/logout' element={<Logout />} />
        </Routes>
      </Router>
    </>
  )
}

export default App
