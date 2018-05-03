package com.sjtu.server.repository;

import com.sjtu.server.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

}
