package com.sjtu.server.controller;

import com.sjtu.server.domain.User;
import com.sjtu.server.service.impl.UserServiceImpl;
import com.sjtu.server.vo.JSONPage;
import com.sjtu.server.vo.JSONResult;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class studentController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/student")
    public String GetStudent(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Predicate type = criteriaBuilder.equal(root.get("type").as(Integer.class), 0);
                predicates.add(type);
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };

        Pageable pageable = new PageRequest(page-1, size);
        Page<User> users = userService.listUser(specification,pageable);
        return JSONResult.fillResponseString(0,"",  new JSONObject(){{
            put("total", users.getTotalElements());
            put("data", users.getContent());
        }});
    }
}
