package com.mfbi.repositories;

import com.mfbi.entities.BookEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class BookRepository implements PanacheRepository<BookEntity> {

    @Transactional
    public boolean save(){
    this.persist(
        new BookEntity()
            .setIsbn("111")
            .addProperty("title", "High-Performance Java Persistence")
    );
        return true;
    }
}
