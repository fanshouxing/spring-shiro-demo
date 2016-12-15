/*
 * Copyright (c) 2016. 上海趣医网络技术服务有限公司 版权所有
 * Shanghai QuYi Network Technology Co., Ltd. All Rights Reserved.
 *
 * This is NOT a freeware,use is subject to license terms.
 */

package com.quyiyuan.service;

import org.apache.shiro.authz.annotation.RequiresRoles;

import java.time.LocalDateTime;

/**
 * Created by Fsx on 2016/12/15.
 */
public class ShiroService {

    @RequiresRoles(value = {"admin"})
    public void testMethod(){
        System.out.println("testMethod Time:"+ LocalDateTime.now());
    }
}
