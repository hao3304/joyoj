package com.sjtu.server.service;

import com.sjtu.server.domain.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ClassService {
    Page<Class> listClass(Specification<Class> spec, Pageable pageable);
    Class add(Class obj);
}
