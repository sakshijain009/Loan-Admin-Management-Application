import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './Pages/Login';
import Register from './Pages/Register';
import AdminLogin from './Pages/AdminLogin';
import Dashboard from './Pages/Dashboard';
import Dash from './Pages/Dash';
import ApplyLoans from './Pages/ApplyLoans';
import ViewLoanDetails from './Pages/ViewLoanDetails';
import ViewItems from './Pages/ViewItems';
import AdminDashboard from './Pages/AdminDashboard';
import AdminAddUser from './Pages/AdminAdd/AdminAddUser';
import AdminAddLoan from './Pages/AdminAdd/AdminAddLoan';
import AdminAddItem from './Pages/AdminAdd/AdminAddItem';
import AdminViewUser from './Pages/AdminView/AdminViewUser';
import AdminViewLoan from './Pages/AdminView/AdminViewLoan';
import AdminViewItem from './Pages/AdminView/AdminViewItem';
import ErrorPage from './Pages/ErrorPage';
import Logout from './Components/Logout';

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
          <Route path='/login' element={<Login user={user} loginUser={loginUser} bt={"register"}/> } />
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
          <Route path="*" element={<ErrorPage />}/>
        </Routes>
      </Router>
    </>
  )
}

export default App
