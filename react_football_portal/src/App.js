import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import League from './league_page';
import News from './news_page';
import Result from './result_page';
import Transfer from './transfer_page';
import Login from './auth_page';
import {useCookies} from 'react-cookie';




function App() {
  const [cookieJWT, setCookieJWT, removeCookieJWT] = useCookies(['jwt']);

  async function logout() {
    const response = await fetch("http://localhost:8000/logout",{
      method : "GET",
      mode:"cors",
      cache:"no-cache",
      credentials:"same-origin",
      headers:{
          "Content-Type" : "application/json"
      },
      redirect:"follow",
      referrerPolicy:"no-referrer",
    });
    window.location.href="http://localhost:3000/news"
  }
  return (
    <div className="App">
      <Router>
    <div className= "text-white p-2 " style={{background:"black"}}>
      <div className="container p-2">
        <div className="row">
      <Link to="/"><h2 style={{fontWeight:"bold",float:"left",marginTop:"2px",color:"white"}}>FOOTBALL.PORTAL</h2></Link>
      {!cookieJWT['jwt']?<a href="/auth"><h5 style={{float:"right",marginTop:"-43px",color:"silver"}}><svg style={{marginRight:"5px"}} xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-bounding-box" viewBox="0 0 16 16">
  <path d="M1.5 1a.5.5 0 0 0-.5.5v3a.5.5 0 0 1-1 0v-3A1.5 1.5 0 0 1 1.5 0h3a.5.5 0 0 1 0 1h-3zM11 .5a.5.5 0 0 1 .5-.5h3A1.5 1.5 0 0 1 16 1.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 1-.5-.5zM.5 11a.5.5 0 0 1 .5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 1 0 1h-3A1.5 1.5 0 0 1 0 14.5v-3a.5.5 0 0 1 .5-.5zm15 0a.5.5 0 0 1 .5.5v3a1.5 1.5 0 0 1-1.5 1.5h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 1 .5-.5z"/>
  <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm8-9a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
</svg>SIGN IN</h5></a>:""}
{cookieJWT['jwt']?            
<a  type="button" onClick={logout}><h5 style={{float:"right",marginTop:"-43px",color:"silver"}}><svg style={{marginRight:"5px"}} xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-bounding-box" viewBox="0 0 16 16">
  <path d="M1.5 1a.5.5 0 0 0-.5.5v3a.5.5 0 0 1-1 0v-3A1.5 1.5 0 0 1 1.5 0h3a.5.5 0 0 1 0 1h-3zM11 .5a.5.5 0 0 1 .5-.5h3A1.5 1.5 0 0 1 16 1.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 1-.5-.5zM.5 11a.5.5 0 0 1 .5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 1 0 1h-3A1.5 1.5 0 0 1 0 14.5v-3a.5.5 0 0 1 .5-.5zm15 0a.5.5 0 0 1 .5.5v3a1.5 1.5 0 0 1-1.5 1.5h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 1 .5-.5z"/>
  <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm8-9a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
</svg>LOGOUT</h5></a>:""}
      </div>
      </div>
      </div>
      <div className="container" style={{height:"100px"}}>
      <ul style={{marginTop:"-300px"}}>
  <li style={{marginLeft:"80px",marginRight:"80px"}}><a href="/news">NEWS</a></li>
  <li style={{marginLeft:"80px",marginRight:"80px"}}><a href="/result">RESULTS</a></li>
  <li style={{marginLeft:"80px",marginRight:"80px"}}><a href="/leagues">LEAGUES</a></li>
  <li style={{marginLeft:"80px",marginRight:"80px"}}><a href="/transfer">TRANSFERS</a></li>
  <li style={{marginLeft:"80px",marginRight:"80px"}}><a href="#">CONTACT</a></li>
</ul></div>
<div>
<hr style={{color:"DodgerBlue"}}/>
</div>

      <Switch>
        <Route path="/leagues">
        <League/>
        </Route>
        <Route path="/news">
        <News/>
         </Route>
         <Route path="/result">
        <Result/>
         </Route>
         <Route path="/transfer">
        <Transfer/>
         </Route>
         <Route path="/auth">
        <Login/>
        </Route>
        <Route path="/">
        <News/>
        </Route>
        </Switch>
      </Router>
      <div style={{background:"black",position:"absolute",marginTop:"800px",width:"100%",height:"100px"}}>
          <h4 style={{color:"white",marginTop:"40px",marginLeft:"550px"}}>Copyright(c) IITU:iitu@mail.kz Almaty 2021</h4>
      </div>
    </div>
  );
}

export default App;
