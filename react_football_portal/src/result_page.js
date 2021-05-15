import React,{useEffect,useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import {useCookies} from 'react-cookie';

function Result() {
  const [cookieJWT, setCookieJWT, removeCookieJWT] = useCookies(['jwt']);

    const[arr,setArr] = useState([]);
    const[club1,setClub1] = useState("");
    const[club2,setClub2] = useState("");
    const[result,setResult] = useState("");
    const[date,setDate] = useState("");
    let f=0;

    useEffect(() => {
        loadData();
      }, []);
      
      async function loadData() {
        const response = await fetch("http://localhost:8000/api/getResult");  
      let cards = await response.json();
      setArr(cards);
       }

       
       const delet=event=> {
        const inputData = event.target.id;
        del(inputData);
        window.location.href="http://localhost:3000/result"
      }
      async function del(data) {
        const bearer = "Bearer "+cookieJWT['jwt'].jwtToken;
    
        const response = await fetch("http://localhost:8000/api/delResult",{
          method : "DELETE",
          mode:"cors",
          cache:"no-cache",
          credentials:"same-origin",
          headers:{
              "Content-Type" : "application/json",
              "Authorization" :bearer
          },
          redirect:"follow",
          referrerPolicy:"no-referrer",
          body:JSON.stringify(data)
        });
        
      
      }

      

    return (
      <div>
        <div className="container">
            <h3>Results</h3>
            <div className="row">
            {arr?.map(card=> (
              
               <div  className="col-6" style={{border:"solid silver 1px",display:"block"}}>
                   <div className="row">
                   <div className="col-2" style={{marginTop:"10px"}}>
                       <img src={card.img1} style={{width:"30px"}}/><br/>
                       <img src={card.img2} style={{width:"30px",marginTop:"50px"}}/>
                   </div>
                   <div className="col-5" style={{marginTop:"15px"}}>
                        <b>{card.club1}</b><br/><br/><br/><br/>
                        <b style={{marginTop:"20px"}}>{card.club2}</b>     
                   </div>
                   <div className="col-2">
                       <br/><br/>
                        <b>{card.result}</b>
                   </div>
                   <div className="col-3">
                   <br/><br/>
                        <p style={{color:"silver"}}>{card.date}</p>
                        {cookieJWT['jwt']?<svg xmlns="http://www.w3.org/2000/svg" id={card.id} type="button" onClick={delet} style={{color:"red",marginTop:"-165px",marginLeft:"120px"}} width="25" height="25" fill="currentColor" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
</svg>
:""}

                   </div>
                   
               </div>
               
               </div>
               ))}
      </div>
        </div>
          </div>
    );
        

    
}



export default Result;