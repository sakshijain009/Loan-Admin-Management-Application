import React, { useEffect, useState } from 'react'

const ViewItems = () => {
    const url = 'http://localhost:8080/viewItems';

    const [data, setData] = useState([])
  
    useEffect(() => {
      fetch(
        url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
        }})
      .then(json => setData(json.data))
    }, [])
  
    const renderTable = () => {
      return data.map(user => {
        return (
          <tr>
            <td>{user[0]}</td>
            <td>{user[1]}</td>
            <td>{user[2]}</td>
            <td>{user[3]}</td>
            <td>{user[4]}</td>
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