package com.gemini.admin.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {
    private String name;
    public Author(String name) {
        this.name = name;
    }
}