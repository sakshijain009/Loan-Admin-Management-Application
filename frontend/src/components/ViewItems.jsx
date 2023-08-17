import React, { useEffect, useState } from 'react'

const ViewItems = ({user}) => {

    const [data, setData] = useState([])
  
    useEffect(() => {
        const data = async () => {
            user = sessionStorage.getItem('username');
            const res = await fetch('http://localhost:8080/viewItems', {
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
            <td>{user1.issue_id}</td>
            <td>{user1.item_description}</td>
            <td>{user1.item_make}</td>
            <td>{user1.item_category}</td>
            <td>{user1.item_value}</td>
          </tr>
        )
      })
    }
  
    return (
      <div>
        <h1 id="title">Items Purchased</h1>
        <table id="users">
          <thead>
            <tr>
              <th>Issue_id</th>
              <th>Item Description</th>
              <th>Item Make</th>
              <th>Item Category</th>
              <th>Item Valuation</th>
            </tr>
          </thead>
          <tbody>{renderTable()}</tbody>
        </table>
      </div>
    )
}

export default ViewItems