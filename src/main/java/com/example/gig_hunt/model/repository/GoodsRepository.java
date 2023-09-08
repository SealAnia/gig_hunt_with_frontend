package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @Query(value = "SELECT * FROM goods g WHERE g.master_id = :master_id", nativeQuery = true)
    List<Goods> findItemsFromMaster(@Param("master_id") Long userId);

}
