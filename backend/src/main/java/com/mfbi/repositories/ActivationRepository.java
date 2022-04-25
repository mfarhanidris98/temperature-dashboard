package com.mfbi.repositories;

import com.mfbi.dto.ActivationDTO;
import com.mfbi.entities.ActivationEntity;
import com.mfbi.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class ActivationRepository implements PanacheRepository<ActivationEntity> {

    public ActivationDTO findByUsername(String username){
        ActivationEntity activationEntity = find("username", username).firstResult();

        return new ActivationDTO(activationEntity.username,
                activationEntity.key);
    }

    public boolean verifyKey(ActivationDTO activationDTO){
        ActivationDTO act = findByUsername(activationDTO.getUsername());

        System.out.println("Username :" + act.getUsername());
        System.out.println("Key :" + act.getAuthKey());
        return act.getAuthKey().equals(activationDTO.getAuthKey());
    }

    public void deleteByUsername(String username){
        ActivationEntity activationEntity = find(
                "username", username).firstResult();
        this.delete(activationEntity);
    }

}
