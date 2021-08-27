package com.hemooffice.suopu.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author YL
 * @date 2019/3/27 15:52
 * @apiNote
 */
public class JWTUtils {

    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * 验证token
     * @param token
     * @param username
     * @param password
     * @return
     */
    public static boolean verify(String token, String username, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成token
     * @param username
     * @param password
     * @return
     */
    public static String sign(String username, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析token得到用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        if (token == null || "".equals(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
