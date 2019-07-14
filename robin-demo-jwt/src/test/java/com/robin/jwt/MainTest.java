/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/14 11:02
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.robin.jwt.api.UserApi;
import io.jsonwebtoken.*;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/14 11:02
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/14 11:02
 */

public class MainTest {

    private final String secret = "1234567";



    @Test
    public void testKey() {
        RSAKeyProvider keyProvider = new RSAKeyProvider() {

            @Override public RSAPublicKey getPublicKeyById(String keyId) {

                return null;
            }

            @Override public RSAPrivateKey getPrivateKey() {
                return null;
            }

            @Override public String getPrivateKeyId() {
                return null;
            }
        };

    }
    @Test
    public void testAlgorithm() {

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withIssuer("auth0").sign(algorithm);
        System.out.println(token);

        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
        DecodedJWT decodedJWT = verifier.verify(token);
        System.out.println(decodedJWT);
    }

    @Test
    public void claimsTest() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        HashMap<String,Object> map = new HashMap<>();
        map.put("owner","zhaosc");
        map.put("password","123456");
        String token = JWT.create().withIssuer("auth0").withHeader(map).withClaim("root", "root123")
                .withKeyId(UUID.randomUUID().toString()).sign(algorithm);
        System.out.println(token);

        DecodedJWT jwt = JWT.decode(token);

        String header = jwt.getHeader();
        System.out.println(header);
        String payload = jwt.getPayload();
        System.out.println(payload);

        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
        DecodedJWT verify = verifier.verify(token);
        System.out.println(verify.getHeader());
        System.out.println(verify.getPayload());
    }

    @Test
    public void jwtsTest() {

        Key KEY = new SecretKeySpec("javastack".getBytes(),
                SignatureAlgorithm.HS512.getJcaName());

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("type", "1");
//        String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"2018-01-01 0:00:00\"}";
        Map<String,Object> payload = new HashMap<>();
        payload.put("user_id","111111111");
        payload.put("expire_time","2018-01-01 0:00:00");
        String compactJws = Jwts.builder().setHeader(stringObjectMap)
//                .setPayload(payload)
                .setClaims(payload)
                .signWith(SignatureAlgorithm.HS512, KEY).compact();

        System.out.println("jwt key:" + new String(KEY.getEncoded()));
        System.out.println("jwt payload:" + payload);
        System.out.println("jwt encoded:" + compactJws);


        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(compactJws);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();

        System.out.println("jwt header:" + header);
        System.out.println("jwt body:" + body);
        System.out.println("jwt body user-id:" + body.get("user_id", String.class));
    }

    @Test
    public void buildJwt() {

        UserApi api = new UserApi();
        String token = api.login("", "");

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(api.KEY).parseClaimsJws(token);
        System.out.println(claimsJws.getBody());
    }
}
