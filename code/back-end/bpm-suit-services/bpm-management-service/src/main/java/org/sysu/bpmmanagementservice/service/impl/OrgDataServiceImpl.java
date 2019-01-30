package org.sysu.bpmmanagementservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sysu.bpmmanagementservice.constant.GlobalContext;
import org.sysu.bpmmanagementservice.constant.ResponseConstantManager;
import org.sysu.bpmmanagementservice.dao.*;
import org.sysu.bpmmanagementservice.entity.*;
import org.sysu.bpmmanagementservice.service.OrgDataService;
import org.sysu.bpmmanagementservice.vo.RenConnectionVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrgDataServiceImpl implements OrgDataService {
    @Autowired
    ActIdUserEntityDao actIdUserEntityDao;

    @Autowired
    RenCapabilityEntityDao renCapabilityEntityDao;

    @Autowired
    RenPositionEntityDao renPositionEntityDao;

    @Autowired
    RenGroupEntityDao renGroupEntityDao;

    @Autowired
    RenConnectEntityDao renConnectEntityDao;

    @Override
    public HashMap<String, Object> addHuman(String firstName, String lastName, String email, String password) {
        HashMap<String, Object> result = new HashMap<>();
        ActIdUserEntity actIdUserEntity = new ActIdUserEntity();
        actIdUserEntity.setEmail(email);
        actIdUserEntity.setFirstName(firstName);
        actIdUserEntity.setLastName(lastName);
        actIdUserEntity.setPassword(password);
        actIdUserEntity =  actIdUserEntityDao.saveOrUpdate(actIdUserEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", actIdUserEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> removeHuman(String username) {
        HashMap<String, Object> result = new HashMap<>();
        ActIdUserEntity actIdUserEntity =  actIdUserEntityDao.deleteByUsername(username);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    /** 只有密码可以修改 */
    @Override
    public HashMap<String, Object> updateHuman(String username, HashMap<String, String> pairs) {
        HashMap<String, Object> result = new HashMap<>();
        ActIdUserEntity actIdUserEntity = actIdUserEntityDao.findByUsername(username);
        if(pairs.get("password") != null) {
            actIdUserEntity.setPassword(pairs.get("password"));
            actIdUserEntityDao.saveOrUpdate(actIdUserEntity);
        }
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveHumanByUsername(String username) {
        HashMap<String, Object> result = new HashMap<>();
        ActIdUserEntity  actIdUserEntity = actIdUserEntityDao.findByUsername(username);
        result.put("data", actIdUserEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    @Override
    public HashMap<String, Object> findAllHuman() {
        HashMap<String, Object> result = new HashMap<>();
        List<ActIdUserEntity> humans = actIdUserEntityDao.findAll();
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", humans);
        return result;
    }

    @Override
    public HashMap<String, Object> addGroup(String name, String description, String note, String belongToId, int groupType) {
        HashMap<String, Object> result = new HashMap<>();
        RenGroupEntity renGroupEntity = new RenGroupEntity();
        renGroupEntity.setName(name);
        renGroupEntity.setBelongToId(belongToId);
        renGroupEntity.setDescription(description);
        renGroupEntity.setGroupType(groupType);
        renGroupEntity.setNote(note);
        renGroupEntity =  renGroupEntityDao.saveOrUpdate(renGroupEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renGroupEntity);
        return result;

    }

    @Override
    public HashMap<String, Object> removeGroupByName(String name) {
        HashMap<String, Object> result = new HashMap<>();
        RenGroupEntity renGroupEntity = renGroupEntityDao.deleteByName(name);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renGroupEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> updateGroup(String name, HashMap<String, String> pairs) {
        HashMap<String, Object> result = new HashMap<>();
        RenGroupEntity renGroupEntity = renGroupEntityDao.findByName(name);
        if(pairs.get("description") != null) {
            renGroupEntity.setDescription(pairs.get("description"));
        }
        if(pairs.get("note") != null) {
            renGroupEntity.setNote(pairs.get("note"));
        }
        if(pairs.get("belongToId") != null) {
            renGroupEntity.setBelongToId(pairs.get("belongToId"));
        }
        if(pairs.get("groupType") != null) {
            renGroupEntity.setGroupType(Integer.valueOf(pairs.get("groupType")));
        }
        renGroupEntity =  renGroupEntityDao.saveOrUpdate(renGroupEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renGroupEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveGroup(String id) {
        HashMap<String, Object> result = new HashMap<>();
        RenGroupEntity renGroupEntity = renGroupEntityDao.findById(id);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renGroupEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveAllGroup() {
        HashMap<String, Object> result = new HashMap<>();
        List<RenGroupEntity> groups = renGroupEntityDao.findAll();
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", groups);
        return result;
    }

    @Override
    public HashMap<String, Object> addPosition(String name, String description, String note, String belongToId, String reportToId) {
        HashMap<String, Object> result = new HashMap<>();
        RenPositionEntity renPositionEntity = new RenPositionEntity();
        renPositionEntity.setName(name);
        renPositionEntity.setBelognToId(belongToId);
        renPositionEntity.setDescription(description);
        renPositionEntity.setNote(note);
        renPositionEntity.setReportedId(reportToId);
        renPositionEntity = renPositionEntityDao.saveOrUpdate(renPositionEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renPositionEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> removePositionByName(String name) {
        HashMap<String, Object> result = new HashMap<>();
        RenPositionEntity renPositionEntity = renPositionEntityDao.deleteByName(name);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renPositionEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> updatePosition(String name, HashMap<String, String> pairs) {
        HashMap<String, Object> result = new HashMap<>();
        RenPositionEntity renPositionEntity = renPositionEntityDao.findByName(name);
        if(pairs.get("description") != null) {
            renPositionEntity.setDescription(pairs.get("description"));
        }
        if(pairs.get("belongToId") != null) {
            renPositionEntity.setBelognToId(pairs.get("belongToId"));
        }
        if(pairs.get("reportToId") != null) {
            renPositionEntity.setReportedId(pairs.get("reportToId"));
        }
        if(pairs.get("note") != null) {
            renPositionEntity.setNote(pairs.get("note"));
        }
        renPositionEntity = renPositionEntityDao.saveOrUpdate(renPositionEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renPositionEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrievePositionById(String id) {
        HashMap<String, Object> result = new HashMap<>();
        RenPositionEntity renPositionEntity = renPositionEntityDao.findById(id);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renPositionEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveAllPosition() {
        HashMap<String, Object> result = new HashMap<>();
        List<RenPositionEntity> positionEntities = renPositionEntityDao.findAll();
        result.put("data", positionEntities);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    @Override
    public HashMap<String, Object> addCapability(String name, String description, String note) {
        HashMap<String, Object> result = new HashMap<>();
        RenCapabilityEntity renCapabilityEntity = new RenCapabilityEntity();
        renCapabilityEntity.setDescription(description);
        renCapabilityEntity.setName(name);
        renCapabilityEntity.setNote(note);
        renCapabilityEntity =  renCapabilityEntityDao.saveOrUpdate(renCapabilityEntity);
        result.put("data", renCapabilityEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    @Override
    public HashMap<String, Object> removeCapabilityByName(String name) {
        HashMap<String, Object> result = new HashMap<>();
        RenCapabilityEntity renCapabilityEntity = renCapabilityEntityDao.deleteByName(name);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renCapabilityEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> updateCapability(String name, HashMap<String, String> pairs) {
        HashMap<String, Object> result = new HashMap<>();
        RenCapabilityEntity renCapabilityEntity = renCapabilityEntityDao.findByName(name);
        if(pairs.get("note") != null) {
            renCapabilityEntity.setNote(pairs.get("note"));
        }
        if(pairs.get("description") != null) {
            renCapabilityEntity.setDescription(pairs.get("description"));
        }
        renCapabilityEntity = renCapabilityEntityDao.saveOrUpdate(renCapabilityEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renCapabilityEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveCapabilityById(String id) {
        HashMap<String, Object> result = new HashMap<>();
        RenCapabilityEntity renCapabilityEntity = renCapabilityEntityDao.findById(id);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renCapabilityEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveAllCapability() {
        HashMap<String, Object> result = new HashMap<>();
        List<RenPositionEntity> positionEntities = renPositionEntityDao.findAll();
        result.put("data", positionEntities);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    @Override
    public HashMap<String, Object> removeHumanConnection(String username) {
        HashMap<String, Object> result = new HashMap<>();
        List<RenConnectEntity> renConnectEntities = renConnectEntityDao.deleteAllByUsername(username);
        //删除所有的mapping里面的后续操作，也就是对应的actiivti的用户在group里面的状态

        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renConnectEntities);
        return result;

    }


    @Override
    public HashMap<String, Object> retrieveHumanInWhatPosition(String username) {
        HashMap<String, Object> result = new HashMap<>();
        List<RenConnectEntity> renConnectEntities = renConnectEntityDao.findAllByUsernameAndType(username, GlobalContext.RENCONNECT_TYPE_POSITION);
        List<RenGroupEntity> groupEntities = new ArrayList<>();
        for(RenConnectEntity renConnectEntity : renConnectEntities) {
            groupEntities.add(renGroupEntityDao.findById(renConnectEntity.getBelongToOrganizabledId()));
        }
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", groupEntities);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveHumanWithWhatCapability(String username) {
        HashMap<String, Object> result = new HashMap<>();
        List<RenConnectEntity> renConnectEntities = renConnectEntityDao.findAllByUsernameAndType(username, GlobalContext.RENCONNECT_TYPE_CAPABILITY);
        List<RenCapabilityEntity> renCapabilityEntities = new ArrayList<>();
        for(RenConnectEntity renConnectEntity : renConnectEntities) {
            renCapabilityEntities.add(renCapabilityEntityDao.findById(renConnectEntity.getBelongToOrganizabledId()));
        }
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renCapabilityEntities);
        return result;
    }

    @Override
    public HashMap<String, Object> addHumanPosition(String username, String positionId) {
        HashMap<String, Object> result = new HashMap<>();
        RenConnectEntity renConnectEntity = new RenConnectEntity();
        renConnectEntity.setBelongToOrganizabledId(positionId);
        renConnectEntity.setType(GlobalContext.RENCONNECT_TYPE_POSITION);
        renConnectEntity.setUsername(username);
        renConnectEntity =  renConnectEntityDao.saveOrUpdate(renConnectEntity);
        //进行业务关系底层映射实现（绑定activiti用户与组）

        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renConnectEntity);
        return result;

    }

    @Override
    public HashMap<String, Object> removeHumanPosition(String username, String positionId) {
        HashMap<String, Object> result = new HashMap<>();
        RenConnectEntity renConnectEntity = renConnectEntityDao.deleteByUsernameAndBelongToOrganizabledIdAndType(username, positionId, GlobalContext.RENCONNECT_TYPE_POSITION);
        //处理底层映射

        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renConnectEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> addHumanCapability(String username, String capabilityId) {
        HashMap<String, Object> result = new HashMap<>();
        RenConnectEntity renConnectEntity = new RenConnectEntity();
        renConnectEntity.setBelongToOrganizabledId(capabilityId);
        renConnectEntity.setType(GlobalContext.RENCONNECT_TYPE_CAPABILITY);
        renConnectEntity.setUsername(username);
        renConnectEntity =  renConnectEntityDao.saveOrUpdate(renConnectEntity);
        //进行业务关系底层映射实现（绑定activiti用户与组）

        result.put("data", renConnectEntity);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }

    @Override
    public HashMap<String, Object> removeHumanCapability(String username, String capabilityId) {
        HashMap<String, Object> result = new HashMap<>();
        RenConnectEntity renConnectEntity = renConnectEntityDao.deleteByUsernameAndBelongToOrganizabledIdAndType(username, capabilityId, GlobalContext.RENCONNECT_TYPE_CAPABILITY);
        //处理底层映射

        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", renConnectEntity);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveAllConnection() {
        HashMap<String, Object> result = new HashMap<>();
        List<RenConnectEntity> renConnectEntities = renConnectEntityDao.findAll();
        HashMap<String, RenConnectionVo> allConnectVo = new HashMap<>();
        String username = "";
        for(RenConnectEntity renConnectEntity : renConnectEntities) {
            username = renConnectEntity.getUsername();
            if(allConnectVo.get(username) == null) {
                allConnectVo.put(username, new RenConnectionVo());
            }
            //position
            if(renConnectEntity.getType() == GlobalContext.RENCONNECT_TYPE_POSITION) {
                RenPositionEntity renPositionEntity = renPositionEntityDao.findById(renConnectEntity.getBelongToOrganizabledId());
                allConnectVo.get(username).getRenPositionEntities().add(renPositionEntity);
            } else if(renConnectEntity.getType() == GlobalContext.RENCONNECT_TYPE_CAPABILITY) {
                RenCapabilityEntity renCapabilityEntity = renCapabilityEntityDao.findById(renConnectEntity.getBelongToOrganizabledId());
                allConnectVo.get(username).getRenCapabilityEntities().add(renCapabilityEntity);
            }
        }
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", allConnectVo);
        return result;
    }

    /** 只返回username就可以了*/
    @Override
    public HashMap<String, Object> retrieveAllHumanInPosition(String positionId) {
        HashMap<String, Object> result = new HashMap<>();
        List<RenConnectEntity> renConnectEntities = renConnectEntityDao.findAllByBelongToOrganizabledIdAndType(positionId, GlobalContext.RENCONNECT_TYPE_POSITION);
        List<String> usernames = new ArrayList<>();
        for(RenConnectEntity renConnectEntity : renConnectEntities) {
            usernames.add(renConnectEntity.getUsername());
        }
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("data", usernames);
        return result;
    }

    @Override
    public HashMap<String, Object> retrieveAllHumanWithCapability(String capabilityId) {
        HashMap<String, Object> result = new HashMap<>();
        List<RenConnectEntity> renConnectEntities = renConnectEntityDao.findAllByBelongToOrganizabledIdAndType(capabilityId, GlobalContext.RENCONNECT_TYPE_POSITION);
        List<String> usernames = new ArrayList<>();
        for(RenConnectEntity renConnectEntity : renConnectEntities) {
            usernames.add(renConnectEntity.getUsername());
        }
        result.put("data", usernames);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        return result;
    }
}