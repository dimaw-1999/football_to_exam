package com.example.reactProject.repositories;

import com.example.reactProject.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LigaRepository extends JpaRepository<Liga, Long> {
    Liga findLigaByName(String name);
    Liga findLigaByNameOrderByClubsScoreDesc(String name);
}
