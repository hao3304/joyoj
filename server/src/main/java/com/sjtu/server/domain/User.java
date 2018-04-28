package com.sjtu.server.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {

    public enum Type {Student, Teacher }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty(message = "账号不能为空")
    @Size(min = 3,max = 20)
    @Column(nullable = false,length = 20,unique = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Type type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isRoot() {
        return "root".equals(this.username);
    }

}
