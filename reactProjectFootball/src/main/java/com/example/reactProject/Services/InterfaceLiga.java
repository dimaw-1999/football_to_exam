package com.example.reactProject.Services;

import com.example.reactProject.entity.*;

import java.sql.Date;
import java.util.List;

public interface InterfaceLiga {
    void addLiga(Liga liga);
    void addClub(Club club);
    boolean isExist(String name);
    Liga getLigaByName(String name);
    Club getClubById(Long id);
    Players getPlayersById(Long id);
    boolean isExistPlayers(String name);
    void addPlayers(Players players);
    Club getClubByName(String name);
    Players getPlayersByName(String name);
    List<Liga> getAllLiga();
    Liga getLigaByScore(String name);
    List<Club> getAllClub();
    boolean isExistNews(String name);
    void addNews(News news);
    News getNewsByName(String name);
    List<News> getAllNews();
    void removeNews(Long id);
    boolean isExistResult(String name1,String name2);
    void addResult(Result result);
    List<Result> getAllResult();
    void RemoveResult(Long id);
    Result getResultById(Long id);
    List<News> getNews(Date date);
    List<News> getNewsFirst();
    List<Result> getResultFirst();
    void addTransfer(Transfer transfer);
    List<Transfer> getAllTransfers();
    boolean isExistTransfer(String name);
    List<Transfer> getTransferFirst();



}
