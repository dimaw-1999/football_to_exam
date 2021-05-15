package com.example.reactProject.parser;

import com.example.reactProject.Services.ServiceLiga;
import com.example.reactProject.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LigaParse {

    @Autowired
    private ServiceLiga serviceLiga;

////////////////////////////// Transfer news /////////////////////////
    @Scheduled(fixedDelay = 4300000)
    public void ParserTransfer() {
        String url = "https://www.readfootball.com/transfer.html";
        try {
           Document doc = (Document) Jsoup.connect(url)
                   .userAgent("Mozilla")
                   .timeout(10000)
                   .referrer("https://google.com")
                   .get();
           Elements elements = doc.select("div[class=arhive_artile_title]>a");
           Elements elements1 = doc.select("img[class=img_article]");
           int i=0;
           for(Element el:elements) {
               if(!serviceLiga.isExistTransfer(el.ownText())) {
                   Transfer transfer = new Transfer();
                   transfer.setName(el.ownText());
                   int r=0;
                   for(Element e:elements1) {
                       if(r==i) {
                           transfer.setImg(e.absUrl("src"));
                           break;
                       }
                       r++;
                   }
                   transfer.setDate(new Date(new java.util.Date().getTime()));
                   serviceLiga.addTransfer(transfer);
                   i++;
               }
           }

    } catch (IOException e) {
            e.printStackTrace();
        }}

///////////////////////////// Parser of results///////////////////////
//    @Scheduled(fixedDelay = 4300000)
//    public void ParserResult() {
//        String url = "https://football.kulichki.net/";
//       try {
//           Document doc = (Document) Jsoup.connect(url)
//                   .userAgent("Mozilla")
//                   .timeout(10000)
//                   .referrer("https://google.com")
//                   .get();
//           Elements elements = doc.select("ul>li>a");
//           for(Element l:elements) {
//               if(l.ownText().equals("Англия")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               Club club = serviceLiga.getClubByName(h1);
//                               result1.setImg1(club.getImg());
//                               result1.setClub2(h2);
//                               Club club1 = serviceLiga.getClubByName(h2);
//                               result1.setImg2(club1.getImg());
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                    result1.setDate(d.ownText());
//                                    break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("APL");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                          i++;
//                       }
//           } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//               if(l.ownText().equals("Германия")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               result1.setClub2(h2);
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                       result1.setDate(d.ownText());
//                                       break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("Bundesliga");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                           i++;
//                       }
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//               if(l.ownText().equals("Премьер-лига")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               result1.setClub2(h2);
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                       result1.setDate(d.ownText());
//                                       break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("Premier liga");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                           i++;
//                       }
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//               if(l.ownText().equals("Испания")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               result1.setClub2(h2);
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                       result1.setDate(d.ownText());
//                                       break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("La Liga");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                           i++;
//                       }
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//               if(l.ownText().equals("Италия")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               result1.setClub2(h2);
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                       result1.setDate(d.ownText());
//                                       break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("Seria A");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                           i++;
//                       }
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//               if(l.ownText().equals("Франция")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               result1.setClub2(h2);
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                       result1.setDate(d.ownText());
//                                       break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("Ligue 1");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                           i++;
//                       }
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//               if(l.ownText().equals("Казахстан")) {
//                   try {
//                       Document england = (Document) Jsoup.connect(l.absUrl("href"))
//                               .userAgent("Mozilla")
//                               .timeout(10000)
//                               .referrer("https://google.com")
//                               .get();
//                       Elements days = england.select("td[bgcolor=#F2F2F2][width=79]>p[align=left]>font");
//                       Elements clubs = england.select("td[bgcolor=#F2F2F2][align=left]>p>font[size=2][face=Arial]>a");
//                       Elements result = england.select("td[width=135]>p>font[size=2]>a>b");
//                       int i=0;
//                       int h=0;
//                       String h1="";
//                       String h2="";
//                       for(Element cl:clubs) {
//                           if(i==0) h1=cl.ownText();
//                           if(i==1) h2=cl.ownText();
//                           if(i==1 && !serviceLiga.isExistResult(h1,h2)) {
//                               Result result1 = new Result();
//                               result1.setClub1(h1);
//                               result1.setClub2(h2);
//                               int r=0;
//                               for(Element d:days) {
//                                   if(r==h) {
//                                       result1.setDate(d.ownText());
//                                       break;
//                                   }
//                                   r++;
//                               }
//                               int y=0;
//                               for(Element k:result) {
//                                   if(y==h) {
//                                       result1.setResult(k.ownText());
//                                       break;
//                                   }
//                                   y++;
//                               }
//                               Liga liga = serviceLiga.getLigaByName("KPL");
//                               result1.setLiga(liga);
//                               serviceLiga.addResult(result1);
//                           }
//                           if(i==1) {
//                               h++;
//                               i=-1;
//                           }
//                           i++;
//                       }
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//           }
//    } catch (IOException e) {
//           e.printStackTrace();
//       }
//       List<Result> results = serviceLiga.getAllResult();
//
//        for(Result k:results) {
//            if(k.getDate_now()==null) {
//
//                k.setDate_now(LocalDateTime.now ());
//                serviceLiga.addResult(k);
//            }
//        }
//
//
//        for(Result j:results) {
//            if(j.getImg1()==null || j.getImg2()==null) {
//                Club cl = serviceLiga.getClubByName(j.getClub1());
//                Club cl1 = serviceLiga.getClubByName(j.getClub2());
//                j.setImg1(cl.getImg());
//                j.setImg2(cl1.getImg());
//                serviceLiga.addResult(j);
//            }
//        }
//        for(Result k:results) {
//            if(k.getResult()==null) {
//                serviceLiga.RemoveResult(k.getId());
//            }
//        }
//
//    }



//----------------------------------------Add news about football--------------------------------------------------

//    @Scheduled(fixedDelay = 4320000)
//    public void ParserBarcaPlayers() {
//       String url = "https://football.kulichki.net/";
//       try {
//           Document doc = (Document) Jsoup.connect(url)
//                   .userAgent("Mozilla")
//                   .timeout(10000)
//                   .referrer("https://google.com")
//                   .get();
//           Elements names = doc.select("font[color=red]>b");
//           Elements urls = doc.select("img[src*=/photo/]");
//           Elements details = doc.select("center~br~br~a");
//           int i=0;
//           for(Element el:names) {
//               if(!serviceLiga.isExistNews(el.ownText())) {
//                   News news = new News();
//                   news.setName(el.ownText());
//
//                      int j=0;
//                       for(Element u:urls) {
//                           if(j==i) {
//                           news.setUrl(u.absUrl("src"));
//                           break;
//                           }
//                           j++;
//                       }
//                   int r=0;
//                   for(Element l:details) {
//                       boolean check= false;
//                       try {
//                           Document docs = (Document) Jsoup.connect(l.absUrl("href"))
//                                   .userAgent("Mozilla")
//                                   .timeout(5000)
//                                   .referrer("https://google.com")
//                                   .get();
//                           Elements element = docs.select("b");
//                           Elements elements = new Elements();
//                           for(Element kdd:element) {
//                               if(kdd.ownText().length()>200) {
//                                   elements.add(kdd);
//                               }
//                           }
//                           for (Element li : elements) {
//                                  if(r==i) {
//                                      news.setDescription(li.ownText());
//                                      check = true;
//                                  }
//
//                           }
//
//                       } catch (IOException e) {
//                           e.printStackTrace();
//                       }
//                       if(check==true) {
//                           break;
//                       }
//                       r++;
//                   }
//                   Date date = new Date();
//                   java.sql.Date date1 = new java.sql.Date(date.getTime());
//                   news.setDate(date1);
//                   news.setBig(false);
//                       serviceLiga.addNews(news);
//               }
//               i++;
//           }
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//       List<News> newses=serviceLiga.getAllNews();
//       int i=1;
//       for(News h:newses) {
//           if(i==1){
//           h.setBig(true);
//           serviceLiga.addNews(h);}
//           if(i==3) {
//
//               i=0;
//           }
//           i++;
//       }
//
//    }


//-----------------------------------------Add players of APL----------------------------------------------------------------

//   @Scheduled(fixedDelay = 432000000)
//   public void ParserBarcaPlayers() {
//       String url = "https://football.kulichki.net/england/";
//       try {
//           Document doc = (Document) Jsoup.connect(url)
//                   .userAgent("Mozilla")
//                   .timeout(5000)
//                   .referrer("https://google.com")
//                   .get();
//
//           Elements elements0 = doc.select("b>font>a[href*=/england/2021/teams/]");
//            long id1=1;
//           for(Element el:elements0) {
//               try {
//                   String ur = el.absUrl("href");
//                   Document doc1 = (Document) Jsoup.connect(ur)
//                           .userAgent("Mozilla")
//                           .timeout(5000)
//                           .referrer("https://google.com")
//                           .get();
//                   Elements elements = doc1.select("tr>td[bgcolor=#F2F2F2][width=5%][align=center]>font[size=2]");
//                    Elements elements1 = doc1.select("td[bgcolor=#F2F2F2][width=10%][align=center]>font[size=2]");
//                   Elements elements2 = doc1.select("td[bgcolor=#F2F2F2][width=20%]>font[size=2]>a[href*=/players/]");
//                   Elements elements3 = doc1.select("td[bgcolor=#F2F2F2][width=15%]>font[size=2]");
//
//                   for(Element el1:elements2) {
//                       String name = el1.ownText();
//                       if(!serviceLiga.isExistPlayers(name)) {
//                           Players players = new Players();
//                           players.setName(el1.ownText());
//                           Club club = serviceLiga.getClubByName(el.ownText());
//                           serviceLiga.addPlayers(players);
//                           if(club.getPlayers()==null) {
//                               ArrayList<Players> players1 = new ArrayList<>();
//                               players1.add(players);
//                               club.setPlayers(players1);
//                           }
//                           else {
//                               club.getPlayers().add(players);
//                           }
//                           serviceLiga.addClub(club);
//                       }
//                   }
//
//                   for(Element el2:elements2) {
//                        try {
//                            String ur1 = el2.absUrl("href");
//                            Document doc2 = (Document) Jsoup.connect(ur1)
//                                    .userAgent("Mozilla")
//                                    .timeout(5000)
//                                    .referrer("https://google.com")
//                                    .get();
//                            Elements elements4 = doc2.select("p[align=center]>img[src*=/playerpics/]");
//                            for(Element el1:elements4) {
//                                Players players = serviceLiga.getPlayersByName(el2.ownText());
//                                if(players.getImg()== null) {
//                                    players.setImg(el1.absUrl("src"));
//                                    serviceLiga.addPlayers(players);
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                   }
//
//                   for(Element el2:elements2) {
//                   for(Element el4:elements1) {
//                       Players players = serviceLiga.getPlayersByName(el2.ownText());
//                                if(players.getDate()==null){
//                                players.setDate(el4.ownText());
//                                serviceLiga.addPlayers(players);}
//                                elements1.remove(el4);
//
//                       break;
//                   }
//
//                   }
//
//
//
//                   for(Element el1:elements2) {
//                   for(Element el5:elements3) {
//                       Players players = serviceLiga.getPlayersByName(el1.ownText());
//                            if(players.getCitizen()==null) {
//                           players.setCitizen(el5.ownText());
//                           serviceLiga.addPlayers(players);}
//                           elements3.remove(el5);
//                       break;
//
//                   }
//
//                   }
//                   int j=0;
//                   for(Element el6:elements) {
//                       Players players = serviceLiga.getPlayersById(id1);
//                       if(players!=null) {
//                       if(j==0) {
//                           String t = el6.ownText();
//                           if(players.getNumber()!=null) {
//                           players.setNumber(t);
//                           serviceLiga.addPlayers(players);}
//
//                            }
//                       if(j==1) {
//                           String t = el6.ownText();
//                           if(players.getGames()!=el6.ownText()) {
//                           players.setGames(t);
//                           serviceLiga.addPlayers(players);}
//
//
//                       }
//                       if(j==2) {
//                           String t = el6.ownText();
//                           if(players.getGoals()!=el6.ownText()) {
//                           players.setGoals(t);
//                           serviceLiga.addPlayers(players);}
//
//                                                 }
//                       if(j==3) {
//                           String t = el6.ownText();
//                           if(players.getY_card()!=el6.ownText()) {
//                           players.setY_card(t);
//                           serviceLiga.addPlayers(players);}
//
//                                                 }
//                       if(j==4) {
//                           String t = el6.ownText();
//                           if(players.getR_card()!=el6.ownText()) {
//                           players.setR_card(t);
//                           serviceLiga.addPlayers(players);}
//
//                                                  }}
//                       j++;
//                       if(j>4) {
//                           j=0;
//                           id1++;
//                       }
//                   }
//
//
//               } catch (Exception e) {
//                   e.printStackTrace();
//               }
//           }
//
//       }
//
//
//
//       catch (Exception e) {
//           e.printStackTrace();
//       }
//
//
//   }



//   -------------------------------------Tables club's parsers----------------------------------------------------------------

//    @Scheduled(fixedDelay = 43200000)
//    public void ParserPremierLiga() {
//        String url = "https://football.kulichki.net/ruschamp/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/ruschamp/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            long id3=1;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("Premier liga");
//                    if(liga.getClubs()==null) {
//                    ArrayList<Club> clubs = new ArrayList<>();
//                    clubs.add(club);
//                    liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=1;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//                }
//            long id2=1;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=1;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//
//            }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Scheduled(fixedDelay = 43200000)
//    public void ParserLaLiga() {
//        String url = "https://football.kulichki.net/spain/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/spain/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("La Liga");
//                    if(liga.getClubs()==null) {
//                        ArrayList<Club> clubs = new ArrayList<>();
//                        clubs.add(club);
//                        liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=17;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//            }
//            long id2=17;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=17;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//            long id3=17;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//
//        }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Scheduled(fixedDelay = 43200000)
//    public void ParserBundes() {
//        String url = "https://football.kulichki.net/germany/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/germany/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("Bundesliga");
//                    if(liga.getClubs()==null) {
//                        ArrayList<Club> clubs = new ArrayList<>();
//                        clubs.add(club);
//                        liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=37;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//            }
//            long id2=37;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=37;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//            long id3=37;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//
//        }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Scheduled(fixedDelay = 43200000)
//    public void ParserKPL() {
//        String url = "https://football.kulichki.net/kz/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/kz/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("KPL");
//                    if(liga.getClubs()==null) {
//                        ArrayList<Club> clubs = new ArrayList<>();
//                        clubs.add(club);
//                        liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=74;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//            }
//            long id2=74;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=74;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//            long id3=74;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//
//        }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Scheduled(fixedDelay = 43200000)
//    public void ParserAPL() {
//        String url = "https://football.kulichki.net/england/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/england/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("APL");
//                    if(liga.getClubs()==null) {
//                        ArrayList<Club> clubs = new ArrayList<>();
//                        clubs.add(club);
//                        liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=55;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//            }
//            long id2=55;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=55;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//            long id3=55;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//
//        }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Scheduled(fixedDelay = 43200000)
//    public void ParserFrance() {
//        String url = "https://football.kulichki.net/france/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/france/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("Seria A");
//                    if(liga.getClubs()==null) {
//                        ArrayList<Club> clubs = new ArrayList<>();
//                        clubs.add(club);
//                        liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=88;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//            }
//            long id2=88;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=88;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//            long id3=88;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//
//        }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Scheduled(fixedDelay = 43200000)
//    public void ParserItaly() {
//        String url = "https://football.kulichki.net/italy/";
//        try {
//
//            Document doc = (Document) Jsoup.connect(url)
//                    .userAgent("Mozilla")
//                    .timeout(5000)
//                    .referrer("https://google.com")
//                    .get();
//            Elements elements = doc.select("b>font>a[href*=/italy/2021/teams/]");
//            Elements elements1 = doc.select("td[width=55][bgcolor=#cffccd]>font");
//            Elements elements2 = doc.select("td[width=80][bgcolor=#cffccd]>font");
//            Elements elements3 = doc.select("td[width=55][bgcolor=#cffccd]>font>b");
//
//
//            for(Element el:elements) {
//                if(!serviceLiga.isExist(el.ownText())) {
//                    Club club = new Club();
//                    club.setName(el.ownText());
//                    serviceLiga.addClub(club);
//                    Liga liga = serviceLiga.getLigaByName("Seria B");
//                    if(liga.getClubs()==null) {
//                        ArrayList<Club> clubs = new ArrayList<>();
//                        clubs.add(club);
//                        liga.setClubs(clubs);
//                    }
//                    else if(liga.getClubs()!=null) {
//                        liga.getClubs().add(club);
//                    }
//                    serviceLiga.addLiga(liga);
//                }
//            }
//
//            long id=108;
//            for(Element el:elements1) {
//                if(el.ownText().length()>0) {
//                    Club club = serviceLiga.getClubById(id);
//                    club.setGames(el.ownText());
//                    serviceLiga.addClub(club);
//                    id++;
//                }
//            }
//            long id2=108;
//            for(Element el:elements3) {
//                Club club = serviceLiga.getClubById(id2);
//                int score = Integer.parseInt(el.ownText());
//                club.setScore(score);
//                serviceLiga.addClub(club);
//                id2++;
//            }
//
//
//            long id1=108;
//            int i=0;
//            for(Element el:elements2) {
//                Club club = serviceLiga.getClubById(id1);
//                if(i==0) {club.setWins(el.ownText()); }
//                if(i==1) {club.setSame(el.ownText());}
//                if(i==2) {club.setLoses(el.ownText());}
//                if(i==3) {club.setDifference(el.ownText());}
//                if(i==3) {
//                    id1++;
//                    i=-1;
//                }
//                i++;
//                serviceLiga.addClub(club);
//
//            }
//
//            long id3=108;
//            for(Element el:elements) {
//                String ur = el.absUrl("href");
//                try {
//                    Document doc1 = (Document) Jsoup.connect(ur)
//                            .userAgent("Mozilla")
//                            .timeout(5000)
//                            .referrer("https://google.com")
//                            .get();
//                    Elements elements4 = doc1.select("p[align=center]>font[face=Arial]>img[border=0]");
//                    Elements elements5 = doc1.select("td>font[face=Arial][size=2]>b");
//                    for(Element e:elements4) {
//                        Club club = serviceLiga.getClubById(id3);
//                        if(club.getImg()==null){
//                            club.setImg(e.absUrl("src"));
//                            serviceLiga.addClub(club);}
//
//                    }
//                    for(Element e:elements5) {
//                        Club club = serviceLiga.getClubById(id3);
//
//                        if(club.getCoach()==null){
//                            club.setCoach(e.ownText());
//                            serviceLiga.addClub(club);}
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                id3++;
//            }
//
//        }
//
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
