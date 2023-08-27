import React from "react";
import Appbar from "../components/Appbar";
import "./Dash.css";
import { Link, useNavigate } from "react-router-dom";
import { Button, Card } from "react-bootstrap";

function AdminDashboard() {
  let goToUrl;
  const navigate = useNavigate();
  if (sessionStorage.getItem("admin") === null) {
    navigate("/loginadmin");
  } else {
    goToUrl = "/adminhome";
  }
  return (
    <>
      <Appbar bt={"Logout"} hbtn={"/adminhome"} />
      <div className="user-dashboard__container text-center">
        {/* <h1>Loan Management Application</h1>
        <hr /> */}
        <h3>Admin Dash Board</h3>
        <div className="d-flex justify-content-around flex-column flex-md-row mt-5">
          <Card className="box__shadow mt-3 col-10 col-md-3 mx-auto">
            <Card.Img
              variant="top"
              src="https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"
            />
            <Card.Body
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Card.Title>Customer Data Management</Card.Title>
              <Button variant="light" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "black",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/adminadduser"
                >
                  Add User
                </Link>
              </Button>
              <Button variant="dark" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "white",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/adminviewuser"
                >
                  View User
                </Link>
              </Button>
            </Card.Body>
          </Card>

          <Card className="box__shadow mt-3 col-10 col-md-3 mx-auto">
            <Card.Img
              variant="top"
              src="https://images.unsplash.com/photo-1583916011819-e4b81836bb57?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjN8fGxvYW58ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"
            />
            <Card.Body
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Card.Title>Loan Card Management</Card.Title>
              <Button variant="light" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "black",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/adminaddloan"
                >
                  Add Loan
                </Link>
              </Button>
              <Button variant="dark" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "white",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/adminviewloan"
                >
                  View Loan
                </Link>
              </Button>
            </Card.Body>
          </Card>

          <Card className="box__shadow mt-3 col-10 col-md-3 mx-auto">
            <Card.Img
              variant="top"
              src="https://images.unsplash.com/photo-1562770584-eaf50b017307?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8aXRlbXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"
            />
            <Card.Body
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Card.Title>Items Master Data</Card.Title>
              <Button variant="light" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "black",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/adminadditem"
                >
                  Add Item
                </Link>
              </Button>
              <Button variant="dark" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "white",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/adminviewitem"
                >
                  View Items
                </Link>
              </Button>
            </Card.Body>
          </Card>

          {/* <Button variant="warning" className="mx-5 px-5 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'black', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/adminaddloan">
                                Loan Card Management
                        </Link>
                    </Button>
                    <Button variant="primary" className="mx-5 px-5 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/adminadditem">
                                Items Master Data
                        </Link>
                    </Button> */}
          {/* <Link to="/adminaddloan" className="user-dashboard__button">Loan Card Management</Link>
                    <Link to="/adminadditem" className="user-dashboard__button">Items Master Data</Link> */}
        </div>
      </div>
    </>
  );
}
export default AdminDashboard;
