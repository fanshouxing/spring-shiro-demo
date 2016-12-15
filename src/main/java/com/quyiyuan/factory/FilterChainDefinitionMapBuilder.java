/*
 * Copyright (c) 2016. 上海趣医网络技术服务有限公司 版权所有
 * Shanghai QuYi Network Technology Co., Ltd. All Rights Reserved.
 *
 * This is NOT a freeware,use is subject to license terms.
 */

package com.quyiyuan.factory;

import java.util.LinkedHashMap;

/**
 * Created by Fsx on 2016/12/15.
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();


        map.put("/login.jsp","anon");
        map.put("/shiro/login","anon");
        map.put("/shiro/logout","logout");
        map.put("/user.jsp","roles[user]");
        map.put("/admin.jsp","roles[admin]");
        map.put("/**","authc");
        return map;
    }
}
