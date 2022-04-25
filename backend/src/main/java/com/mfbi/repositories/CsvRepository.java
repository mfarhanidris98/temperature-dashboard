package com.mfbi.repositories;

import com.mfbi.entities.CsvEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CsvRepository implements PanacheRepository<CsvEntity> {

    @Transactional
    public boolean save(){
    this.persist(
        new CsvEntity()
            .addProperty("title", "High-Performance Java Persistence")
    );
        return true;
    }
}
