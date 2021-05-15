package com.example.reactProject.repositories;

import com.example.reactProject.entity.Result;
import com.example.reactProject.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransferRepository extends JpaRepository<Transfer,Long> {
    List<Transfer> findFirst15ByOrderByIdDesc();
    boolean existsTransferByName(String name);

}
