package org.sysu.bpmmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sysu.bpmmanagementservice.entity.RenGroupEntity;

public interface RenGroupEntityRepository extends JpaRepository<RenGroupEntity, String> {
    RenGroupEntity deleteByName(String name);

    RenGroupEntity findByName(String name);

    RenGroupEntity findById(String id);
}
