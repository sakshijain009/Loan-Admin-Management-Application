import Appbar from "../components/Appbar";
import "./Dash.css";
import { Link } from "react-router-dom";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Card } from "react-bootstrap";

function Dash({ user }) {
  const navigate = useNavigate();
  if (sessionStorage.getItem("username") === null) {
    navigate("/login");
  }
  return (
    <>
      <Appbar hbtn={"/home"} bt={"Logout"} />
      <div className="user-dashboard__container text-center">
        {/* <h1>Loan Management Application</h1>
        <hr /> */}
        <h3>User Dash Board</h3>
        <div className="d-flex justify-content-around flex-column flex-md-row mt-5">
          <Card className="box__shadow mt-3 col-10 col-md-3 mx-auto">
            <Card.Img
              variant="top"
              src="https://images.unsplash.com/photo-1516321318423-f06f85e504b3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"
            />
            <Card.Body
              className="d-flex text-center justify-content-center align-items-center"
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Card.Title>Customer Data Management</Card.Title>
              <Button variant="dark" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "white",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/viewloan"
                >
                  View Loans
                </Link>
              </Button>
            </Card.Body>
          </Card>

          <Card className="box__shadow mt-3 col-10 col-md-3 mx-auto">
            <Card.Img
              variant="top"
              src="https://images.unsplash.com/photo-1450101499163-c8848c66ca85?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"
            />
            <Card.Body
              className="d-flex text-center justify-content-center align-items-center"
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Card.Title>Loan Card Management</Card.Title>
              <Button variant="dark" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "white",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/applyloan"
                >
                  Apply for Loan
                </Link>
              </Button>
            </Card.Body>
          </Card>

          <Card className="box__shadow mt-3 col-10 col-md-3 mx-auto">
            <Card.Img
              variant="top"
              src="https://images.unsplash.com/photo-1623265300797-4a3541e29a60?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fGxpc3R8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"
            />
            <Card.Body
              className="d-flex text-center justify-content-center align-items-center"
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
              }}
            >
              <Card.Title>Items Master Data</Card.Title>
              <Button variant="dark" className="m-2">
                <Link
                  style={{
                    textDecoration: "none",
                    color: "white",
                    fontWeight: "bold",
                    fontSize: "1.2rem",
                  }}
                  to="/viewitems"
                >
                  View Items Purchased
                </Link>
              </Button>
            </Card.Body>
          </Card>

          {/* <Button variant="primary" className="mx-5 px-5 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/viewloan">
                                View Loans
                        </Link>
                    </Button>
                    <Button variant="warning" className="mx-5 px-4 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'black', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/applyloan">
                                Apply for Loans
                        </Link>
                    </Button>
                    <Button variant="primary" className="mx-5 px-3 py-2">
                        <Link 
                            style={{textDecoration:'none', color:'white', fontWeight:'bold', fontSize:'1.2rem'}} 
                            to="/viewitems">
                                View Items Purchased
                        </Link>
                    </Button> */}
          {/* <Link to="/adminaddloan" className="user-dashboard__button">Loan Card Management</Link>
                    <Link to="/adminadditem" className="user-dashboard__button">Items Master Data</Link> */}
        </div>
      </div>
    </>
  );
}
export default Dash;
