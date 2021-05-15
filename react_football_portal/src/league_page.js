import React,{useEffect,useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

function League() {

    const[name,setName] = useState("");
    const [leagues,setLeagues] = useState([]);
    
    useEffect(() => {
        loadData();
      }, []);
  

    
      const take = event => {
        document.getElementById("888").style.display="none";
        document.getElementById("777").replaceWith(document.getElementById(event.target.id+"444"));
        document.getElementById(event.target.id+"444").style.display="block";
        }

        const play = event => {
          let club_name='';
          let card_name='';
          for(let char of event.target.id) {
            if(char=="4") {
              club_name=card_name;
              card_name='';
              continue;
            }
            card_name+=char;
          }
          document.getElementById(card_name+"444").style.display="none";
          document.getElementById("444").replaceWith(document.getElementById(club_name+"555"));
          document.getElementById(club_name+"555").style.display="block";
          }
  
    async function loadData() {
        const response = await fetch("http://localhost:8000/api/getLeagueName");  
      let cards = await response.json();
      setLeagues(cards);
       }


    return (
      <div>
        <div className="container" style={{display:"block"}} id="888">
        <div  className="row" style={{marginLeft:"100px"}} >
        {leagues?.map(card=> (
          <div className="col-4 mt-3" style={{border:"solid silver 1px",maxWidth:"300px",marginLeft:"50px",height:"350px"}}>
           <div className="row">
           <div>
             <a type="button" style={{fontSize:"30px",color:"black"}} id={card.name} onClick={take}>{card.name}</a>
             <div className="container" id={card.name+"444"} style={{display:"none"}}>
             <table class="table">
                 <thead>
                   <tr>
                     <th scope="col">Club</th>
                     <th  scope="col"></th>
                     <th scope="col"></th>
                     <th scope="col">games</th>
                     <th scope="col">wins</th>
                     <th scope="col">draws</th>
                     <th scope="col">losses</th>
                     <th scope="col">difference</th>
                     <th scope="col">score</th>
                   </tr>
                 </thead>
                 <tbody>
               {card.clubs?.map(club=>(
                 (club.number==1 || club.number==2 || club.number==3 || club.number==4) && (card.name=="La Liga" || card.name=="Bundesliga" ||card.name=="APL" ||card.name=="Seria A" || card.name=="Ligue 1") ?
                  <tr style={{borderLeft:"solid #4285F4 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a type="button" style={{color:"black",marginLeft:"-50px"}} id={club.name+"4"+card.name} onClick={play}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                  <h1 style={{color:"red",marginLeft:"500px"}}>{club.name}</h1>
                  <div className="row" style={{marginLeft:"200px"}}>
                    <div>
                    <img src={club.img} style={{width:"200px"}} /></div>
                    <div style={{marginTop:"-120px",marginLeft:"300px"}}><h4>{club.coach}</h4></div>
                  </div>
                  <table style={{marginTop:"50px"}} class="table table-dark table-striped">
                      <thead>
                   <tr>
                     <th scope="col">№</th>
                     <th scope="col">name</th>
                     <th scope="col">born</th>
                     <th scope="col">citizenship</th>
                     <th scope="col">games</th>
                     <th scope="col">goals</th>
                     <th scope="col">yellow cards</th>
                     <th scope="col">red cards</th>
                     <th scope="col"></th>
                   </tr>
                 </thead>
                 <tbody>
                    {club.players?.map(player=>(
                  player.number==null?"":    
                  <tr>
                    <td>{player.number}</td>
                    <td>{player.name}</td>
                    <td>{player.date}</td>
                    <td>{player.citizen}</td>
                    <td>{player.games}</td>
                    <td>{player.goals}</td>
                    <td>{player.y_card}</td>
                    <td>{player.r_card}</td>
                    <td><img style={{width:"100px"}}  src={player.img}/></td>
                  </tr>
                   ))}  </tbody>
                    </table>
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td>
                  </tr>:
                  (club.number==1) && (card.name=="Premier liga") ?
                  <tr style={{borderLeft:"solid #4285F4 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a style={{color:"black",marginLeft:"-50px"}}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                    {club.players?.map(player=>(
                      <div>{player.name}</div>
                    ))}
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td>
                  </tr>
                  :
                  (club.number==5) && (card.name=="La Liga" || card.name=="Bundesliga" ||card.name=="APL" ||card.name=="Seria A" || card.name=="Ligue 1") ?
                  <tr style={{borderLeft:"solid #FF4500 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a type="button" style={{color:"black",marginLeft:"-50px"}} id={club.name+"4"+card.name} onClick={play}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                  <h1 style={{color:"red",marginLeft:"500px"}}>{club.name}</h1>
                  <div className="row" style={{marginLeft:"200px"}}>
                    <div>
                    <img src={club.img} style={{width:"200px"}} /></div>
                    <div style={{marginTop:"-120px",marginLeft:"300px"}}><h4>{club.coach}</h4></div>
                  </div>
                  <table style={{marginTop:"50px"}} class="table table-dark table-striped">
                      <thead>
                   <tr>
                     <th scope="col">№</th>
                     <th scope="col">name</th>
                     <th scope="col">born</th>
                     <th scope="col">citizenship</th>
                     <th scope="col">games</th>
                     <th scope="col">goals</th>
                     <th scope="col">yellow cards</th>
                     <th scope="col">red cards</th>
                     <th scope="col"></th>
                   </tr>
                 </thead>
                 <tbody>
                    {club.players?.map(player=>(
                  player.number==null?"":    
                  <tr>
                    <td>{player.number}</td>
                    <td>{player.name}</td>
                    <td>{player.date}</td>
                    <td>{player.citizen}</td>
                    <td>{player.games}</td>
                    <td>{player.goals}</td>
                    <td>{player.y_card}</td>
                    <td>{player.r_card}</td>
                    <td><img style={{width:"100px"}}  src={player.img}/></td>
                  </tr>
                   ))}  </tbody>
                    </table>
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  (club.number==6) && (card.name=="La Liga" || card.name=="Bundesliga" ||card.name=="APL" ||card.name=="Seria A" || card.name=="Ligue 1") ?
                  <tr style={{borderLeft:"solid #FFD700 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a type="button" style={{color:"black",marginLeft:"-50px"}} id={club.name+"4"+card.name} onClick={play}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                  <h1 style={{color:"red",marginLeft:"500px"}}>{club.name}</h1>
                  <div className="row" style={{marginLeft:"200px"}}>
                    <div>
                    <img src={club.img} style={{width:"200px"}} /></div>
                    <div style={{marginTop:"-120px",marginLeft:"300px"}}><h4>{club.coach}</h4></div>
                  </div>
                  <table style={{marginTop:"50px"}} class="table table-dark table-striped">
                      <thead>
                   <tr>
                     <th scope="col">№</th>
                     <th scope="col">name</th>
                     <th scope="col">born</th>
                     <th scope="col">citizenship</th>
                     <th scope="col">games</th>
                     <th scope="col">goals</th>
                     <th scope="col">yellow cards</th>
                     <th scope="col">red cards</th>
                     <th scope="col"></th>
                   </tr>
                 </thead>
                 <tbody>
                    {club.players?.map(player=>(
                  player.number==null?"":    
                  <tr>
                    <td>{player.number}</td>
                    <td>{player.name}</td>
                    <td>{player.date}</td>
                    <td>{player.citizen}</td>
                    <td>{player.games}</td>
                    <td>{player.goals}</td>
                    <td>{player.y_card}</td>
                    <td>{player.r_card}</td>
                    <td><img style={{width:"100px"}}  src={player.img}/></td>
                  </tr>
                   ))}  </tbody>
                    </table>
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  (club.number==2) && (card.name=="Premier liga") ?
                  <tr style={{borderLeft:"solid #00BFFF 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a style={{color:"black",marginLeft:"-50px"}}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                    {club.players?.map(player=>(
                      <div>{player.name}</div>
                    ))}
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  (club.number==3 || club.number==4) && (card.name=="Premier liga") ?
                  <tr style={{borderLeft:"solid #FF4500 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a style={{color:"black",marginLeft:"-50px"}}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                    {club.players?.map(player=>(
                      <div>{player.name}</div>
                    ))}
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  (club.number==1) && (card.name=="KPL") ?
                  <tr style={{borderLeft:"solid #00BFFF 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a style={{color:"black",marginLeft:"-50px"}}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                    {club.players?.map(player=>(
                      <div>{player.name}</div>
                    ))}
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  (club.number==2 || club.number==3 || club.number==4) && (card.name=="KPL") ?
                  <tr style={{borderLeft:"solid #FFD700 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a style={{color:"black",marginLeft:"-50px"}}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                    {club.players?.map(player=>(
                      <div>{player.name}</div>
                    ))}
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  club.zona==true ?
                  <tr style={{borderLeft:"solid red 2px"}}>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a type="button" style={{color:"black",marginLeft:"-50px"}} id={club.name+"4"+card.name} onClick={play}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                  <h1 style={{color:"red",marginLeft:"500px"}}>{club.name}</h1>
                  <div className="row" style={{marginLeft:"200px"}}>
                    <div>
                    <img src={club.img} style={{width:"200px"}} /></div>
                    <div style={{marginTop:"-120px",marginLeft:"300px"}}><h4>{club.coach}</h4></div>
                  </div>
                  <table style={{marginTop:"50px"}} class="table table-dark table-striped">
                      <thead>
                   <tr>
                     <th scope="col">№</th>
                     <th scope="col">name</th>
                     <th scope="col">born</th>
                     <th scope="col">citizenship</th>
                     <th scope="col">games</th>
                     <th scope="col">goals</th>
                     <th scope="col">yellow cards</th>
                     <th scope="col">red cards</th>
                     <th scope="col"></th>
                   </tr>
                 </thead>
                 <tbody>
                    {club.players?.map(player=>(
                  player.number==null?"":    
                  <tr>
                    <td>{player.number}</td>
                    <td>{player.name}</td>
                    <td>{player.date}</td>
                    <td>{player.citizen}</td>
                    <td>{player.games}</td>
                    <td>{player.goals}</td>
                    <td>{player.y_card}</td>
                    <td>{player.r_card}</td>
                    <td><img style={{width:"100px"}}  src={player.img}/></td>
                  </tr>
                   ))}  </tbody>
                    </table>
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td></tr>
                  :
                  <tr>
                  <td>{club.number}</td>
                  <td><img style={{width:"30px",height:"30px",marginLeft:"-60px"}} src={club.img}/></td>
                  <td><a type="button" style={{color:"black",marginLeft:"-50px"}} id={club.name+"4"+card.name} onClick={play}>{club.name}</a></td>
                  <div className="container"  id={club.name+"555"} style={{display:"none"}}>
                  <h1 style={{color:"red",marginLeft:"500px"}}>{club.name}</h1>
                  <div className="row" style={{marginLeft:"200px"}}>
                    <div>
                    <img src={club.img} style={{width:"200px"}} /></div>
                    <div style={{marginTop:"-120px",marginLeft:"300px"}}><h4>{club.coach}</h4></div>
                  </div>
                  <table style={{marginTop:"50px"}} class="table table-dark table-striped">
                      <thead>
                   <tr>
                     <th scope="col">№</th>
                     <th scope="col">name</th>
                     <th scope="col">born</th>
                     <th scope="col">citizenship</th>
                     <th scope="col">games</th>
                     <th scope="col">goals</th>
                     <th scope="col">yellow cards</th>
                     <th scope="col">red cards</th>
                     <th scope="col"></th>
                   </tr>
                 </thead>
                 <tbody>
                    {club.players?.map(player=>(
                  player.number==null?"":    
                  <tr>
                    <td>{player.number}</td>
                    <td>{player.name}</td>
                    <td>{player.date}</td>
                    <td>{player.citizen}</td>
                    <td>{player.games}</td>
                    <td>{player.goals}</td>
                    <td>{player.y_card}</td>
                    <td>{player.r_card}</td>
                    <td><img style={{width:"100px"}}  src={player.img}/></td>
                  </tr>
                   ))}  </tbody>
                    </table>
                  </div>
                  <td>{club.games}</td>
                  <td>{club.wins}</td>
                  <td>{club.same}</td>
                  <td>{club.loses}</td>
                  <td>{club.difference}</td>
                  <td>{club.score}</td>
                  </tr>
               ))}</tbody>
               </table>
               <div ><div style={{background:"#4285F4",width:"10px",height:"10px"}}></div><p style={{position:"absolute",marginTop:"-18px",marginLeft:"20px"}}>Проход дальше-Лига чемпионов(Групповой этап)</p></div>
               <div style={{marginTop:"10px"}}><div style={{background:"#00BFFF",width:"10px",height:"10px"}}></div><p style={{position:"absolute",marginTop:"-18px",marginLeft:"20px"}}>Проход дальше -Лига чемпионов(Квалификация)</p></div>
               <div style={{marginTop:"10px"}}><div style={{background:"#FF4500",width:"10px",height:"10px"}}></div><p style={{position:"absolute",marginTop:"-18px",marginLeft:"20px"}}>Проход дальше-Лига европы(Групповой этап)</p></div>
               <div style={{marginTop:"10px"}}><div style={{background:"#FFD700",width:"10px",height:"10px"}}></div><p style={{position:"absolute",marginTop:"-18px",marginLeft:"20px"}}>Проход дальше-Лига европы(Квалификация)</p></div>
               <div style={{marginTop:"10px"}}><div style={{background:"red",width:"10px",height:"10px"}}></div><p style={{position:"absolute",marginTop:"-18px",marginLeft:"20px"}}>Зона вылета(Понижение)</p></div> 
             </div>
             <h4 style={{color:"silver"}}>{card.country}</h4>
           </div>
           <img src={card.img} style={{height:"200px"}}/>
           </div>
         </div>
         

       ))}</div></div>
      
    <div id="777" style={{display:"none"}}>

    </div>
    <div id="444" style={{display:"none"}}>

    </div>
</div>
    );
        

    
}


export default League;

