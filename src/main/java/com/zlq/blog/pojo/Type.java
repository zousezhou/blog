package com.zlq.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by lanqZhou on 2020.10.20
 */

@Entity
@Table(name = "t_type", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany()
    private List<Blog> blog = new ArrayList<>();

    public Type(Long id, String name, List<Blog> blog) {
        this.id = id;
        this.name = name;
        this.blog = blog;
    }

    public Type() {

    }


    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", blog=" + blog +
                '}';
    }

    public List<Blog> getBlog() {
        return blog;
    }

    public void setBlog(List<Blog> blog) {
        this.blog = blog;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
