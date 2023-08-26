import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./dashboard.css";

function Dashboard() {
  const navigate = useNavigate();
  if (sessionStorage.getItem("admin")) {
    navigate("/adminhome");
  } else if (sessionStorage.getItem("username")) {
    navigate("/home");
  }

  return (
    <div className="cover-bg d-flex text-center text-white bg-dark">
      <div className="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <header className="mb-auto">
          <div>
            <h3 className="float-md-start mb-0">LAMA</h3>
            <nav className="nav nav-masthead justify-content-center float-md-end">
              <Link to="/" className="nav-link active" aria-current="page">
                Home
              </Link>
              <Link to="/login" className="nav-link">
                Login
              </Link>
              <Link to="/register" className="nav-link">
                Register
              </Link>
            </nav>
          </div>
        </header>
        <main className="px-3">
          <h1>LAMA</h1>
          <p className="lead">
            Welcome to LAMA! <br /> Jump right in and apply for loans.
            <br />
            Choose among the various categories and find the perfect loan for
            you!
          </p>
          <Link
            to="/loginadmin"
            className="btn btn-lg mt-2 btn-secondary fw-bold border-white bg-white text-dark"
          >
            Welcome back Admin
          </Link>
        </main>

        <footer className="mt-auto text-white-50">
          <p>&copy; 2023 LAMA</p>
        </footer>
      </div>
    </div>
  );
}

export default Dashboard;
