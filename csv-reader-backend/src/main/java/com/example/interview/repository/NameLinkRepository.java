package com.example.interview.repository;

import com.example.interview.entity.LinkNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Repository
@Transactional
public interface NameLinkRepository extends JpaRepository<LinkNameEntity, Long> {

    List<LinkNameEntity> findByName(String name);

}
