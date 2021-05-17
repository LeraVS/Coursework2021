package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectTypeRepository extends JpaRepository<ObjectType, Long> {

}
