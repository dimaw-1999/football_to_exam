package com.example.reactProject.repositories;

import com.example.reactProject.entity.News;
import com.example.reactProject.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ResultRepository extends JpaRepository<Result,Long> {
    boolean existsResultByClub1AndClub2(String club1,String club2);
    void deleteResultById(Long id);
    Result getResultById(Long id);
    List<Result> findFirst57ByOrderByIdDesc();

}
