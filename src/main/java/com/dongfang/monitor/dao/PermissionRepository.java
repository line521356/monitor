package com.dongfang.monitor.dao;

import com.dongfang.monitor.enums.ResourceTypeEnum;
import com.dongfang.monitor.model.Permission;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long>,JpaSpecificationExecutor<Permission> {

    List<Permission> findByResourceTypeInAndAvailable(List <ResourceTypeEnum> resourceTypeEnumList, Boolean available);

    Integer countByAvailableAndName(Boolean available,String name);

    Integer countByAvailableAndPermission(Boolean available,String name);

    List<Permission>findByAvailable(Boolean available);

    List <Permission> findByNameLikeAndAvailable(String name,Boolean available);
}
