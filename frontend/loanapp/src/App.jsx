import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import AddUser from './components/addUser';


function App() {

  return (
    <>
      <Router>
        <Routes>
          <Route path='/addUser' element={<AddUser/> } />
        </Routes>
      </Router>
    </>
  )
}

export default App
