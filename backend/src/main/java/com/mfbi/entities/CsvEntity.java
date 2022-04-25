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
@Entity
@Table(name = "csv")
@TypeDef(name = "json", typeClass = JsonType.class)
public class CsvEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Map<String, String> properties = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public CsvEntity setUsername(String uuid) {
        this.username = uuid;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public CsvEntity setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public CsvEntity addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

}