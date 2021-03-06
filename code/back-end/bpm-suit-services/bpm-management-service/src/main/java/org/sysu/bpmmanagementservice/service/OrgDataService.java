package org.sysu.bpmmanagementservice.service;

import java.util.HashMap;

/**
 * 组织架构管理服务
 */
public interface OrgDataService {
    /** 人类 */
    HashMap<String, Object> addHuman(String username, String firstName, String lastName, String email, String password, String role);

    HashMap<String, Object> removeHuman(String username);

    HashMap<String, Object> updateHuman(String username, HashMap<String, String> pairs);

    HashMap<String, Object> retrieveHumanByUsername(String username);

    HashMap<String, Object> retrieveAllHuman();

    /** group */
    HashMap<String, Object> addGroup(String name, String description, String note, String belongToId, int groupType);

    HashMap<String, Object> removeGroupById(String id);

    HashMap<String, Object> updateGroup(String id, HashMap<String, String> pairs);

    HashMap<String, Object> retrieveGroupById(String id);

    HashMap<String, Object> retrieveAllGroup();

    /** position */
    HashMap<String, Object> addPosition(String name, String description, String note, String belongToId, String reportToId);

    HashMap<String, Object> removePositionById(String id);

    HashMap<String, Object> updatePosition(String id, HashMap<String, String> pairs);

    HashMap<String, Object> retrievePositionById(String id);

    HashMap<String, Object> retrieveAllPosition();

    /** capability */
    HashMap<String, Object> addCapability(String name, String description, String note);

    HashMap<String, Object> removeCapabilityById(String id);

    HashMap<String, Object> updateCapability(String id, HashMap<String, String> pairs);

    HashMap<String, Object> retrieveCapabilityById(String id);

    HashMap<String, Object> retrieveAllCapability();

    /** connection */
    //移除用户所有的连接
    HashMap<String, Object> removeHumanConnection(String username);

    /** 查找特定用户有哪些职位 */
    HashMap<String, Object> retrieveHumanInWhatPosition(String username);

    /** 查找特定职位有哪些人员 */
    HashMap<String, Object> retrieveAllHumanInPosition(String positionId);

    /** 查询特定用户有哪些能力*/
    HashMap<String, Object> retrieveHumanWithWhatCapability(String username);

    /** 查询特定能力有哪些人员 */
    HashMap<String, Object> retrieveAllHumanWithCapability(String capabilityId);

    HashMap<String, Object> addHumanPosition(String username, String positionId);

    HashMap<String, Object> removeHumanPosition(String username, String positionId);

    HashMap<String, Object> addHumanCapability(String username, String capabilityId);

    HashMap<String, Object> removeHumanCapability(String username, String capabilityId);

    HashMap<String, Object> retrieveAllConnection();
}
