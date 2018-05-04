package com.sjtu.server.repository;

import com.sjtu.server.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassRepository extends JpaRepository<Class, Long>,JpaSpecificationExecutor<Class> {

}
