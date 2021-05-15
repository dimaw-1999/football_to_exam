package com.example.reactProject.repositories;

import com.example.reactProject.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    boolean existsClubsByName(String name);
    Club findClubById(Long id);
    Club findClubByName(String name);
}
