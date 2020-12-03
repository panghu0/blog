package cn.panghu.blog.common.utils;

import cn.panghu.blog.common.pojo.TokenVO;
import cn.panghu.blog.common.properties.CommonProperties;
import cn.panghu.blog.common.spring.SpringContextHolder;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * @author pang hu
 * @date 2020/6/20 13:52
 */
@Component
public class JwtUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static CommonProperties commonProperties;

    // 版本
    public static String TOKEN_VERSION = "1.0";
    // 设置发行人
    public static String ISSUER = "zrb";
    // 设置抽象主题
    public static String SUBJECT = "subject";

    // 过期时间30分钟
    public static long EXPIRE_TIME = 30 * 60 * 1000;
    //HS256 私钥
    private static final String HS256KEY = "panghu";

    public static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    public static Key signingKey = new SecretKeySpec(Base64.decodeBase64(HS256KEY), signatureAlgorithm.getJcaName());

    static{
        if(getCommonProperties().getSessionTimeOut() != null){
            EXPIRE_TIME = getCommonProperties().getSessionTimeOut() * 1000;
        }
    }
    /***
     * @Description 生成JWT
     * @Param
     * @return
     */
    /**
     * 生成签名
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static TokenVO sign(Integer userId, String userName, Integer type) {
        TokenVO token = new TokenVO();
        Date exp = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String code = UUIDUtils.getUUID();
        JwtBuilder builder = Jwts.builder()
                .setId(code)
                .setIssuer(ISSUER)
                .setSubject(SUBJECT)
                .setHeaderParam("typ", "JWT")
                .claim("userId", userId)
                .claim("userName", userName)
                .claim("type", type)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(exp)
                .setNotBefore(new Date());

        //生成JWT
        token.setCode(code);
        token.setToken(builder.compact());
        return token;
    }

    /***
     * @Description 解析JWT
     * @Param
     * @return
     */
    /**
     * 解析jwt
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken){
        try{
            if(StringUtils.isBlank(jsonWebToken)){
                return null;
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(jsonWebToken)
                    .getBody();
            return claims;
        }catch(ExpiredJwtException e){
            throw e;
        }catch (Exception e){
            logger.error(ExceptionUtils.getErrorStack(e));
        }
        return null;
    }

    private static CommonProperties getCommonProperties(){
        if(commonProperties == null){
            commonProperties = SpringContextHolder.getBean("commonProperties");
        }
        return commonProperties;
    }

    public static Boolean vaildToken(Claims claims){
        return claims.getExpiration().before(new Date());
    }
}
