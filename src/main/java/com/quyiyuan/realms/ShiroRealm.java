package com.quyiyuan.realms;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * Created by Fsx on 2016/12/5.
 */
@Slf4j
public class ShiroRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo:"+token);

        //1、把AuthenticationToken转换为UsernamePasswordToekn
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //2、从UsernamePasswordToken 中获取username
        String username = usernamePasswordToken.getUsername();

        //3、调用数据库方法、从数据库中查询username对应的用户记录


        //4、若用户不存在，则抛出UnknowAccountException 异常

        //5、根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常

        //6、根据用户的情况，来构建AuthenticationInfo对象并返回。

        return null;
    }
}
