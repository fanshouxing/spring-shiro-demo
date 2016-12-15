package com.quyiyuan.realms;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fsx on 2016/12/5.
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo:"+token);

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
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if ("user".equals(username)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
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

        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object slat = ByteSource.Util.bytes("user");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName,credentials,slat,hashIterations);
        System.out.println(result);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1、PrincipalCollection 获取登录用户信息
        Object principal = principals.getPrimaryPrincipal();
        //2、利用登录的用户信息来获取用户当前用户的角色或者权限（可能需要查询数据库）
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(principal)){
            roles.add("admin");
        }
        //3、创建SimpleAuthorizationInfo ,并设置reles属性

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //4、 返回SimpleAuthorizationInfo对象

        return info;
    }
}
