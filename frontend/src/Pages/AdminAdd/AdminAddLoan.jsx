import React, { useEffect, useState } from "react";
import Appbar from "../../components/Appbar";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Button from "@mui/material/Button";
import Select from "@mui/material/Select";
import { TextField } from "@mui/material";
import CreditScoreIcon from "@mui/icons-material/CreditScore";
import "../ApplyLoans.css";
import DropdownItem from "react-bootstrap/esm/DropdownItem";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function AdminAddLoan() {
  let goToUrl;
  const navigate = useNavigate();
  if (sessionStorage.getItem("admin") === null) {
    navigate("/loginadmin");
  } else {
    goToUrl = "/adminhome";
  }
  const [loanType, setLoanType] = useState([
    "FURNITURE",
    "MEDICAL",
    "VEHICLE",
    "HOME_REMODELLING",
    "CAR_FINANCE",
    "HOME_EQUITY",
  ]);
  const [duration, setDuration] = useState(0);
  const [category, setCategory] = useState("");
  const [error, setError] = React.useState({
    type: "",
    duration: "",
  });

  const submitHandler = async (e) => {
    e.preventDefault();
    setError({
      type: category ? "" : "Please select a category",
      duration: duration > 0 ? "" : "Please enter a valid duration",
    });
    // try {
    // console.log(typeof(duration))
    if (duration <= 0) {
      // throw new Error("Please enter a valid duration");
      toast("Please enter a valid duration");
      return;
      // console.log("Invalid data");
    }
    const data = async () => {
      const response = await fetch(`http://localhost:8080/api/admin/addLoan`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          type: category,
          duration: duration,
        }),
      });
      const json = await response.json();
      setCategory("");
      setDuration(0);
      if (response.status === 200) {
        navigate("/adminviewloan");
      } else {
        toast(json.message);
      }
      console.log(json);
    };
    data();
    // } catch (err) {
    //     toast(err);
    // }
  };

  return (
    <>
      <Appbar bt={"Logout"} hbtn={goToUrl} />
      <div className="loan__container text-center">
        <h2 className="text-center mt-3">Add Loan Master Data</h2>
        <div className="loan-select">
          <div
            className="loan-form mx-auto"
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
            }}
          >
            <DropdownItems
              val={category}
              setVal={setCategory}
              label={"Select Categories"}
              arr={loanType}
            />
            {error.type && <p style={{ color: "red" }}>{error.type}</p>}
            <TextField
              sx={{ m: 1, minWidth: 350 }}
              label={"Duration"}
              type="number"
              // min={0}
              variant="outlined"
              value={duration}
              onChange={(e) => setDuration(parseInt(e.target.value || "0"))}
            />
            {error.duration && <p style={{ color: "red" }}>{error.duration}</p>}
          </div>
          <Button
            variant="contained"
            className="apply_loan"
            onClick={submitHandler}
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
            }}
          >
            <CreditScoreIcon />
            Add Loan
          </Button>
        </div>
      </div>
      <div
        style={{ display: "flex", justifyContent: "center" }}
        className="container m-auto"
      >
        <button
          className="btn btn-success"
          onClick={() => navigate("/adminviewloan")}
        >
          View Loans
        </button>
      </div>
      <ToastContainer />
    </>
  );
}

function DropdownItems({ flag, val, setVal, lab, arr }) {
  return (
    <div>
      <FormControl
        autoWidth
        sx={{
          m: 1,
          minWidth: 350,
        }}
      >
        <InputLabel id="demo-simple-select-autowidth-label">{lab}</InputLabel>
        <Select
          labelId="demo-simple-select-autowidth-label"
          id="demo-simple-select-autowidth"
          value={val}
          onChange={(e) => {
            setVal(e.target.value);
            // if (flag == 0) {
            //     const data = async () => {
            //         const response = await fetch(`http://localhost:8080/${val}/getAllMake`, {
            //             method: 'GET',
            //             headers: {
            //                 'Content-Type': 'application/json'
            //             }
            //         });
            //         const json = await response.json();
            //         sessionStorage.setItem("allMake", JSON.stringify(json));
            //         console.log("Yoyoyo")
            //         console.log(json);
            //     };
            //     data();
            // }
          }}
          autoWidth
          label={lab}
        >
          {arr.map((ele) => (
            <MenuItem key={ele} value={ele}>
              {ele}
            </MenuItem>
          ))}{" "}
        </Select>
      </FormControl>
    </div>
  );
}

export default AdminAddLoan;
