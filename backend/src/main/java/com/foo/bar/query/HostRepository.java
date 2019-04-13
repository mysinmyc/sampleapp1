package com.foo.bar.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foo.bar.entities.Host;

@Repository
public interface HostRepository extends JpaRepository<Host,Long>{

	
    @Query("select c from Host c where c.name like %?1% or c.address like %?1% ")
    List<Host> genericSearch(String criteria);
}
