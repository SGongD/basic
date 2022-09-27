package com.basicit.service.auth.impl;

import com.basicit.framework.exception.BusinessException;
import com.basicit.framework.pk.FactoryAboutKey;
import com.basicit.framework.pk.TableEnum;
import com.basicit.mapper.auth.PermissionMapper;
import com.basicit.model.auth.Permission;
import com.basicit.service.auth.PermissionService;
import com.basicit.web.shiro.vo.PermissionVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    private PermissionVo convertToVo(Permission per) {
        PermissionVo pvo = new PermissionVo();
        pvo.setId(per.getId());
        pvo.setName(per.getName());
        pvo.setCssClass(per.getCssClass());
        pvo.setUrl(per.getUrl());
        pvo.setSkey(per.getSkey());
        pvo.setParentKey(per.getParentKey());
        pvo.setHide(per.getHide());
        pvo.setLev(per.getLev());
        pvo.setSort(per.getSort());
        return pvo;
    }

    @Override
    public List<PermissionVo> getPermissions(String userId) {
        List<Permission> permissions = permissionMapper.findPermissionByUserId(userId);

        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptyList();
        }

        if (CollectionUtils.isNotEmpty(permissions)) {

            // 메뉴
            Map<String, PermissionVo> oneMap = new LinkedHashMap<>();
            // 보조 메뉴
            Map<String, PermissionVo> twoMap = new LinkedHashMap<>();
            // 3차 메뉴
            Map<String, PermissionVo> threeMap = new LinkedHashMap<>();

            String key;
            Integer lev;
            PermissionVo permissionVo;
            for (Permission p : permissions) {
                key = p.getSkey();
                lev = p.getLev();
                permissionVo = convertToVo(p);
                // 모듈인지 확인
                if (1 == lev) {
                    oneMap.put(key, permissionVo);
                }
                // 메뉴 카테고리인지 확인
                if (2 == lev) {
                    twoMap.put(key, permissionVo);
                }
                // 메뉴인지 확인
                if (3 == lev) {
                    threeMap.put(key, permissionVo);
                }
            }

            // 모든 레벨 3 메뉴를 반복합니다.， 레벨 3 메뉴를 레벨 2 메뉴 카테고리 아래에 걸기
            pingMenu(twoMap, threeMap);

            // 모든 레벨 2 메뉴를 반복합니다.， 1단계 메뉴 카테고리 아래 2단계 메뉴 걸기
            pingMenu(oneMap, twoMap);

            return new ArrayList<>(oneMap.values());
        } else {
            return Collections.emptyList();
        }
    }

    private void pingMenu(Map<String, PermissionVo> parentMenuMap, Map<String, PermissionVo> childMenuMap) {
        PermissionVo child;
        String parentKey;
        PermissionVo parent;
        List<PermissionVo> vos;
        for (Entry<String, PermissionVo> vo : childMenuMap.entrySet()) {
            // 하위 메뉴
            child = vo.getValue();
            // 하위 메뉴에 해당하는 상위 메뉴 KEY 가져오기，즉, 부모 노드 KEY
            parentKey = child.getParentKey();
            if (parentMenuMap.containsKey(parentKey)) {
                // 해당 상위 메뉴 가져오기
                parent = parentMenuMap.get(parentKey);

                // 상위 메뉴에서 상위 메뉴 컬렉션 가져오기
                vos = parent.getChildren();
                if (CollectionUtils.isEmpty(vos)) {
                    vos = new ArrayList<>();
                }
                // 상위 메뉴 아래에 하위 메뉴 걸기
                vos.add(child);
                parent.setChildren(vos);
                parentMenuMap.put(parentKey, parent);
            }
        }
    }

    @Override
    public void addPermission(Permission permission) {
        if (permission == null || StringUtils.isBlank(permission.getSkey()) || StringUtils.isBlank(permission.getName())) {
            throw new BusinessException("permission-fail", "## 메뉴를 만드는 동안 오류가 발생했습니다.；불완전한 메뉴 항목 데이터，생성할 수 없습니다.");
        }
        Permission p = permissionMapper.findPermissionByKey(permission.getSkey());
        if (p != null) {
            throw new BusinessException("permission-fail", "#메뉴를 만드는 동안 오류가 발생했습니다.메뉴 Key이미 존재 함,key=" + permission.getSkey());
        }
        permission.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_PERMISSION));
        permissionMapper.insert(permission);
    }

}
