import React from 'react'
import { Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'

const ErrorPage = () => {
  return (
    <div>
        <h1 className="mt-5">Sorry, Wrong URL.</h1>
        <h2>Go Home</h2>
        <Button variant="primary" className="mx-5 px-5 py-2">
            <Link to='/' style={{textDecoration:'none', color:'white'}}>Home</Link>
        </Button>
    </div>
  )
}

export default ErrorPage