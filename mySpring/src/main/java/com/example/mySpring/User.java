package com.example.mySpring;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    public User(){

    }

    public User(String name, String password, String email){
    	this.name = name;
    	this.password = password;
    	this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
    	return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
    }
}
