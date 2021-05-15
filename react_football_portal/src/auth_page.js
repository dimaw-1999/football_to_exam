import React, { useEffect, useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
  import {useCookies} from 'react-cookie';
  
  
  
  
  
  function Login() {
      const [email,setEmail] = useState("");
      const [password,setPassword]=useState("");
      const [fullName,setFullName]=useState("");
      const [cookieJWT, setCookieJWT, removeCookieJWT] = useCookies(['jwt']);
  
      const chFull=event=> {
        setFullName(event.target.value);
  }
  const chEmail=event=> {
    setEmail(event.target.value);
  }
  const chPassword=event=> {
    setPassword(event.target.value);
  }
  const chPassword2=event=> {
    if(password!=event.target.value) {
      document.getElementById("45").style.display="block";
      document.getElementById("46").style.display="none";
    }
    else {
      document.getElementById("46").style.display="block";
      document.getElementById("45").style.display="none";
    }
  }
  
  async function add() {
    const data = {email,password};
    sendRegister(data);
  
  }
  async function sendRegister(data) {
    const response = await fetch("http://localhost:8000/auth",{
      method : "POST",
      mode:"cors",
      cache:"no-cache",
      credentials:"same-origin",
      headers:{
          "Content-Type" : "application/json"
      },
      redirect:"follow",
      referrerPolicy:"no-referrer",
      body:JSON.stringify(data)
    });
    
  
    if(response.status==200){
      let jwt = await response.json();
      setCookieJWT('jwt', jwt);
      document.location.href = "http://localhost:3000";
      
  }
  else {
alert("dimaw");
  }
   }
  
    
  
   
  
  
  
  
    return (
      <div className="container mt-5" style={{marginLeft:"580px"}}>
        <h3 >Sign in</h3>
        <div style={{marginLeft:"-70px"}}>
        <div className="row mt-5">
        <svg style={{color:"steelblue",position:"absolute",marginLeft:"-50px",width:"70px"}} xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-envelope-fill" viewBox="0 0 16 16">
    <path d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555zM0 4.697v7.104l5.803-3.558L0 4.697zM6.761 8.83l-6.57 4.027A2 2 0 0 0 2 14h12a2 2 0 0 0 1.808-1.144l-6.57-4.027L8 9.586l-1.239-.757zm3.436-.586L16 11.801V4.697l-5.803 3.546z"/>
  </svg>
        <input value={email} onChange={chEmail} type="text" placeholder="Email" style={{marginLeft:"30px",width:"400px",border:"solid white 3px"}}/>
        <hr style={{width:"400px",marginLeft:"50px",position:"absolute",marginTop:"40px"}}/>
        </div>
        <div className="row mt-5">
        <svg style={{color:"steelblue",position:"absolute",marginLeft:"-50px",width:"70px"}} xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-bag" viewBox="0 0 16 16">
    <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
  </svg>
        <input value={password} onChange={chPassword} type="password" placeholder="Password" style={{marginLeft:"30px",width:"400px",border:"solid white 3px"}}/>
        <hr style={{width:"400px",marginLeft:"50px",position:"absolute",marginTop:"40px"}}/>
        </div>
        <div class="mb-3 form-check mt-5">
      <input type="checkbox" class="form-check-input" id="exampleCheck1"/>
      <label class="form-check-label" for="exampleCheck1">I have read and accept the terms and conditions</label>
    </div>
    <button style={{marginLeft:"500px"}} onClick={add} className="btn btn-primary">Login<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cursor-fill" viewBox="0 0 16 16">
    <path d="M14.082 2.182a.5.5 0 0 1 .103.557L8.528 15.467a.5.5 0 0 1-.917-.007L5.57 10.694.803 8.652a.5.5 0 0 1-.006-.916l12.728-5.657a.5.5 0 0 1 .556.103z"/>
  </svg></button>
      </div></div>
    );
  }

  export default Login;