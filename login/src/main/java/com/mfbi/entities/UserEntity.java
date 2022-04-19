package com.mfbi.entities;

import java.util.*;

//import com.mfbi.dto.RoleDTO;
//import com.mfbi.dto.UserDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@ToString
@Entity
public class UserEntity implements PanacheRepository {

    @Id
    @GeneratedValue
    private Long id;
    public String username;
    public String password;
    public String roles;
}
