package com.example.reactProject.repositories;

import com.example.reactProject.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<Players, Long> {
    Players getPlayersById(Long id);
    boolean existsPlayersByName(String name);
    Players getPlayersByName(String name);
}
