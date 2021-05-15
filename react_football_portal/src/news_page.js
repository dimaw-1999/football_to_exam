import React,{useEffect,useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import {useCookies} from 'react-cookie';

function News() {
    const[arr,setArr] = useState([]);
    const [cookieJWT, setCookieJWT, removeCookieJWT] = useCookies(['jwt']);
    const[name,setName] = useState("");
    const[description,setDescription]=useState("");
    const[url,setUrl]=useState("");

    async function loadData() {
      const response = await fetch("http://localhost:8000/api/getNews");  
    let cards = await response.json();
    setArr(cards);
     }
      

     const handleName=event=> {
        setName(event.target.value);
     }
     const handleDescription=event=> {
      setDescription(event.target.value);
    }
    const handleUrl=event=> {
      setUrl(event.target.value);
    }
        
        const delet=event=> {
          const inputData = event.target.id;
          del(inputData);
          window.location.href="http://localhost:3000/news"
        }

        const add=event=> {
          document.getElementById("555").style.display="none";
          document.getElementById("666").style.display="block";

        }
        
        
        async function del(data) {
          const bearer = "Bearer "+cookieJWT['jwt'].jwtToken;
      
          const response = await fetch("http://localhost:8000/api/delNews",{
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

        const handleSubmit = event => {
  
          const inputData = {name,description,url}
          alert(name+""+description+""+url)
          addNews(inputData);
          window.location.href="http://localhost:3000/news"
          
        }
      
        
  async function addNews(data) {
    const bearer = "Bearer "+cookieJWT['jwt'].jwtToken;
    const response = await fetch("http://localhost:8000/api/addNews",{
      method : "PUT",
      mode:"cors",
      cache:"no-cache",
      credentials:"same-origin",
      headers:{
          "Content-Type" : "application/json",
          "Authorization": bearer
      },
      redirect:"follow",
      referrerPolicy:"no-referrer",
      body:JSON.stringify(data)
    })
   
  }

      

      
       useEffect(() => {
        loadData();
      }, []);
      

    return (
      <div>
      <div id="555" style={{display:"block"}}>
        <div className="container">
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="https://liga-online.ru/system/domains/main_hero_images/000/000/001/desktop/1680_Slider_Soocer.png?1589266576" class="d-block w-100" style={{height:"400px"}} alt="..."/>
      <div class="carousel-caption d-none d-md-block">
      </div>
    </div>
    <div class="carousel-item">
      <img src="https://www.fairobserver.com/wp-content/uploads/2019/10/FT-slider.jpg" class="d-block w-100" style={{height:"400px"}} alt="..."/>
      <div class="carousel-caption d-none d-md-block">
      </div>
    </div>
    <div class="carousel-item">
      <img src="https://blog.rockthetraveller.com/wp-content/uploads/2017/11/Los-estadios-de-f%C3%BAtbol-m%C3%A1s-incre%C3%ADbles-del-planeta-slider.jpg" class="d-block w-100" style={{height:"400px"}} alt="..."/>
      <div class="carousel-caption d-none d-md-block">
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
        </div>
        <div className="container mt-3">
          <div className="row">
            <h3 className="col-10">Newses</h3>
            {cookieJWT['jwt']?            <a className="btn btn-success col-2" onClick={add} style={{width:"150px",marginLeft:"50px"}}>ADD NEWS</a>
:""}

            </div>
            <div  className="row">
            {arr?.map(card=> (
                <div className="col-3 mt-3">
        <div class="card" style={{width: "300px",height:"400px"}}>
        <img src={card.url} style={{width:"300px",height:"250px"}} class="card-img-top" alt="..."/>
        <div class="card-body">
          <h5 class="card-title">{card.name}</h5>
          <a href="#" style={{marginTop:"15px"}}  class="btn btn-dark">Подробнее</a>
          
          {cookieJWT['jwt']?<a id={card.id} onClick={delet} style={{marginTop:"15px",marginLeft:"5px"}} className="btn btn-danger">Удалить</a>:""}
        </div>
        
      </div></div> ))}
      </div>
        </div>
          </div>
          <div className="container" id="666" style={{display:"none"}}>
          <div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">Name</label>
  <input onChange={handleName} value={name} type="text" class="form-control" />
</div>
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label">Description</label>
  <textarea onChange={handleDescription} value={description} class="form-control" rows="3"></textarea>
  <label for="exampleFormControlInput1" class="form-label">Url</label>
  <input onChange={handleUrl} value={url} type="text" class="form-control" />
  <button style={{marginTop:"15px",marginLeft:"1240px"}} onClick={handleSubmit} className="btn btn-success">SAVE</button>
</div>
          </div>
          </div>
    );
        

    
}



export default News;