package org.zerock.api01.util;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testGenerate() {
        Map<String, Object> claimMap = Map.of("mid", "ABCDE");

        String jwtStr = jwtUtil.generateToken(claimMap, 1);

        System.out.println(jwtStr);
    }

    @Test
    public void testValidate() {

        //유효기간이 지난 토큰
        String jwtStr = jwtUtil.generateToken(Map.of("mid", "AAAA", "email", "aaaa@bbbb.com"), 1);

        System.out.println(jwtStr);

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

        System.out.println(claim.get("mid"));
        System.out.println(claim.get("email"));
    }
}
