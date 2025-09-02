package com.zlq.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By lanqZhou on 2020.10.20
 */

@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    @ManyToMany(mappedBy = "tagList")
    private List<Blog> blogList = new ArrayList<>();

    public Tag(Long id, Long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public Tag() {

    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
