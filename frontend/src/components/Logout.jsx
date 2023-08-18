import React, {useEffect} from "react";
import { useNavigate } from "react-router-dom";

function Logout(){
    const navigate = useNavigate();
    useEffect(() => {
        sessionStorage.removeItem("username");
        navigate('/');
      },[])
       
}

export default Logout;