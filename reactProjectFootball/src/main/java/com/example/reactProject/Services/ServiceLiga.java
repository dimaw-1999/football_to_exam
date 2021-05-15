package com.example.reactProject.Services;

import com.example.reactProject.entity.*;
import com.example.reactProject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ServiceLiga implements InterfaceLiga  {

    @Autowired
    private LigaRepository ligaRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TransferRepository transferRepository;


    @Override
    public News getNewsByName(String name) {
        return newsRepository.findNewsByName(name);
    }

    @Override
    public boolean isExistNews(String name) {
        return newsRepository.existsNewsByName(name);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getNewsFirst() {
        return newsRepository.findFirst12ByOrderByIdDesc();
    }

    @Override
    public List<News> getNews(Date date) {
        return newsRepository.getNewsByDateEquals(date);
    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.getResultById(id);
    }

    @Override
    public void addTransfer(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public List<Transfer> getTransferFirst() {
        return transferRepository.findFirst15ByOrderByIdDesc();
    }

    @Override
    public boolean isExistTransfer(String name) {
        return transferRepository.existsTransferByName(name);
    }

    @Override
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public List<Result> getResultFirst() {
        return resultRepository.findFirst57ByOrderByIdDesc();
    }

    @Override
    public void RemoveResult(Long id) {
        resultRepository.deleteResultById(id);
    }

    @Override
    public List<Result> getAllResult() {
        return resultRepository.findAll();
    }

    @Override
    public void addResult(Result result) {
        resultRepository.save(result);
    }

    @Override
    public boolean isExistResult(String name1, String name2) {
        return resultRepository.existsResultByClub1AndClub2(name1,name2);
    }

    @Override
    public void removeNews(Long id) {
        newsRepository.deleteNewsById(id);
    }

    @Override
    public void addNews(News news) {
        newsRepository.save(news);
    }

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Club getClubById(Long id) {
        return clubRepository.findClubById(id);
    }

    @Override
    public List<Club> getAllClub() {
        return clubRepository.findAll();
    }

    @Override
    public Liga getLigaByScore(String name) {
        return ligaRepository.findLigaByNameOrderByClubsScoreDesc(name);
    }

    @Override
    public List<Liga> getAllLiga() {
        return ligaRepository.findAll();
    }

    @Override
    public Players getPlayersByName(String name) {
        return playersRepository.getPlayersByName(name);
    }

    @Override
    public Club getClubByName(String name) {
        return clubRepository.findClubByName(name);
    }

    @Override
    public void addPlayers(Players players) {
        playersRepository.save(players);
    }

    @Override
    public boolean isExistPlayers(String name) {
        return playersRepository.existsPlayersByName(name);
    }

    @Override
    public Players getPlayersById(Long id) {
        return playersRepository.getPlayersById(id);
    }

    @Override
    public Liga getLigaByName(String name) {
        return ligaRepository.findLigaByName(name);
    }

    @Override
    public boolean isExist(String name) {
        return clubRepository.existsClubsByName(name);
    }

    @Override
    public void addClub(Club club) {
        clubRepository.save(club);
    }

    @Override
    public void addLiga(Liga liga) {
        ligaRepository.save(liga);
    }
}
