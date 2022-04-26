package com.mfbi.repositories;

import com.mfbi.dto.ActivationDTO;
import com.mfbi.entities.ActivationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class ActivationRepository implements PanacheRepository<ActivationEntity> {

    public ActivationDTO findByUsername(String username){
        ActivationEntity activationEntity = find("username", username).firstResult();
        return new ActivationDTO(activationEntity.username,
                activationEntity.key);
    }

    public boolean verifyKey(ActivationDTO activationDTO){
        ActivationDTO act = findByUsername(activationDTO.getUsername());
        return act.getAuthKey().equals(activationDTO.getAuthKey());
    }

    public void deleteByUsername(String username){
        ActivationEntity activationEntity = find("username", username).firstResult();
        this.delete(activationEntity);
    }

}
