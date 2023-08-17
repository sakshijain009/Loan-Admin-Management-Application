import React, { useEffect, useState } from 'react'

const ViewLoanDetails = ({user}) => {

    const [data, setData] = useState([])
  
    useEffect(() => {
        const data = async () => {
            user = sessionStorage.getItem('username');
            const res = await fetch('http://localhost:8080/getallLoans', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "emp_id": user
            }})
            const dt = await res.json();
            console.log(dt);
            
            console.log(user)
            setData(dt);
        }
        data();
      // setData(json)
    }, [])
  
    const renderTable = () => {
      return data.map(user1 => {
        return (
          <tr>
            <td>{user1.loan_id}</td>
            <td>{user1.duration}</td>
            <td>{user1.type}</td>
            <td>{user1.card_id}</td>
          </tr>
        )
      })
    }
  
    return (
      <div>
        <h1 id="title">Loans Applied</h1>
        <table id="users">
          <thead>
            <tr>
              <th>Loan_id</th>
              <th>Duration</th>
              <th>Loan type</th>
              <th>Card_id</th>
            </tr>
          </thead>
          <tbody>{renderTable()}</tbody>
        </table>
      </div>
    )
}

export default ViewLoanDetails