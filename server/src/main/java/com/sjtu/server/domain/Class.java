package com.sjtu.server.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "班级名称不能为空")
    private String name;

    private String cover;

    @CreatedDate
    private Long createTime;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> students;
}
