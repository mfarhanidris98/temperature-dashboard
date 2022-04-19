package com.mfbi.entities;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
@Entity(name = "Book")
@Table(name = "book")
@TypeDef(name = "json", typeClass = JsonType.class)
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;


    @NaturalId
    @Column(length = 15)
    private String isbn;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Map<String, String> properties = new HashMap<>();

    public String getIsbn() {
        return isbn;
    }

    public BookEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public BookEntity setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public BookEntity addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

}