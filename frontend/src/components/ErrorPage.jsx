import React from 'react'
import { Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'

const ErrorPage = () => {
  return (
    <div style={{display:'flex', justifyContent:'center'}}>
      <div style={{justifyContent:'center'}}>
        <h1 style={{fontSize: 300}}>404</h1>
        <h1 className="mt-3 mb-2">Oops, No page was found here</h1>
        <div style={{display:'flex', justifyContent:'center'}}> <h4>Go to Home</h4> </div>
        <div style={{display:'flex', justifyContent:'center'}}>
        <Button variant="primary" className="mx-5 px-5 py-2" >
            <Link to='/' style={{textDecoration:'none', color:'white'}}>Home</Link>
        </Button>
        </div>
      </div>
    </div>
  )
}

export default ErrorPage