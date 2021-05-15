import React,{useEffect,useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

function Transfer() {
    const[arr,setArr] = useState([]);
    let f=0;

    useEffect(() => {
        loadData();
      }, []);
      
      async function loadData() {
        const response = await fetch("http://localhost:8000/api/getTransfer");  
      let cards = await response.json();
      setArr(cards);
       }

    return (
      <div>
        <div className="container">
            <h3>Latest transfers news</h3><br></br>
            {arr?.map(card=> (
               <div className="row">
                 <img style={{width:"100px"}} className="col-3" src={card.img}/>
                 <b style={{color:"green"}} className="col-6">{card.name}</b>
                 <b className="col-3">{card.date}</b> 
                 <hr style={{color:"green",marginTop:"10px"}}></hr><br/>
               </div>
               ))}
      
        </div>
          </div>
    );
        

    
}



export default Transfer;