package org.sysu.bpmmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sysu.bpmmanagementservice.entity.RenConnectEntity;

import java.util.List;

public interface RenConnectEntityRepository extends JpaRepository<RenConnectEntity,String> {
    void deleteAllByUsername(String userName);

    List<RenConnectEntity> findAllByUsernameAndType(String username, int type);

    void deleteByUsernameAndBelongToOrganizabledIdAndType(String username, String belongToOrganizabledId, int type);

    List<RenConnectEntity> findAllByBelongToOrganizabledIdAndType(String belongToOrganizabledId, int type);

}
