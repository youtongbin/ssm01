package com.neuedu.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String tele;

    public User() {
    }

    public User(String username, String password, String tele) {
        this.username = username;
        this.password = password;
        this.tele = tele;
    }

    public User(Integer id, String username, String password, String tele) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tele = tele;
    }
}
