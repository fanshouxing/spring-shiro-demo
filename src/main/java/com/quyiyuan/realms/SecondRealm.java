/*
 * Copyright (c) 2016. 上海趣医网络技术服务有限公司 版权所有
 * Shanghai QuYi Network Technology Co., Ltd. All Rights Reserved.
 *
 * This is NOT a freeware,use is subject to license terms.
 */

package com.quyiyuan.realms;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Fsx on 2016/12/5.
 */
@Slf4j
public class SecondRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo:"+token);

        log.info("[SecondRealm]");
        //1、把AuthenticationToken转换为UsernamePasswordToekn
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //2、从UsernamePasswordToken 中获取username
        String username = usernamePasswordToken.getUsername();

        //3、调用数据库方法、从数据库中查询username对应的用户记录
        log.info("从数据库中获取username:"+ username + "所对应的用户信息");

        //4、若用户不存在，则抛出UnknowAccountException 异常
        if ("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在!");
        }
        //5、根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }
        //6、根据用户的情况，来构建AuthenticationInfo对象并返回。
        //1)、principal：认证实体信息，可以是username，也可以是数据表对应的用户实体类对象
        Object principal = username;
        //2)、credentials: 密码
        Object credentials = null;//"fc1709d0a95a6be30bc5926fdb7f22f4";
        if ("admin".equals(username)){
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }else if ("user".equals(username)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }
        //3)、realmName： 当前realm对象的name，调用父类的getName（）方法即可
        String realmName = getName();
        //4)、盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);
        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        return info;
    }

    public static void main(String[] args) {

        String hashAlgorithmName = "SHA1";
        Object credentials = "123456";
        Object slat = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName,credentials,slat,hashIterations);
        System.out.println(result);
    }
}
