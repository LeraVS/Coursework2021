package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.TypeOfBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfBusinessRepository extends JpaRepository<TypeOfBusiness, Long> {

}
