/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/14 10:58
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.jwt.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/14 10:58
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/14 10:58
 */
@RestController
@RequestMapping("/user")
public class UserApi {

    public final Key KEY = new SecretKeySpec("jwtSecretKey".getBytes(),SignatureAlgorithm.HS512.getJcaName());

    @PostMapping("login")
    public String login(String userName,String password) {

        boolean auth = checkAuthFromDB(userName, password);
        if (!auth) {
            throw new IllegalArgumentException("401");
        }

        Map<String,Object> payload = new HashMap<>();
        payload.put("userId","id100001");
        payload.put("version",1);
        payload.put("name","张三");

        Date exp = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setClaims(payload)//peyload 负载
                .signWith(SignatureAlgorithm.HS512, KEY)//签名算法
                .setExpiration(exp)//失效时间
                .compact();
    }

    @GetMapping("valid")
    public String valid(HttpServletRequest request) {

        String token = request.getHeader("token");
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        System.out.println(claimsJws.getBody());
        System.out.println(claimsJws.getHeader());

        return claimsJws.getBody().get("userId").toString();
    }
    private boolean checkAuthFromDB(String userNm,String pass) {

        //db 查询结果
        return true;
    }
}
