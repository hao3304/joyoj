package com.sjtu.server.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "账号不能为空")
    @Size(min = 3,max = 20)
    @Column(nullable = false,length = 20,unique = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 20)
    private String password;

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotNull(message =  "性别不能为空")
    private Integer sex;

    @NotNull(message =  "类型不能为空，0学生1老师")
    private Integer type;

    @CreatedDate
    private Long createTime;

    public boolean isAdmin() {
        return "admin".equals(this.username);
    }

}
