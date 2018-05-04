package com.sjtu.server.service.impl;

import com.sjtu.server.domain.Class;
import com.sjtu.server.repository.ClassRepository;
import com.sjtu.server.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Page<Class> listClass(Specification<Class> spec, Pageable pageable) {
        return classRepository.findAll(spec, pageable);
    }

    @Override
    public Class add(Class obj) {
        return classRepository.save(obj);
    }
}
