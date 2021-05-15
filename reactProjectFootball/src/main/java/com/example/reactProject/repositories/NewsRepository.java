package com.example.reactProject.repositories;

import com.example.reactProject.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsNewsByName(String name);
    News findNewsByName(String name);
    void deleteNewsById(Long id);
    List<News> getNewsByDateEquals(Date date);
    List<News> findFirst12ByOrderByIdDesc();
}
