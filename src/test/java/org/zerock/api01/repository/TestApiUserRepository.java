package org.zerock.api01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.api01.domain.ApiUser;
import org.zerock.api01.domain.ApiUserRepository;

import java.util.Map;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TestApiUserRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Test
    public void testInserts() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            ApiUser apiUser = ApiUser.builder()
                    .mid("apiuser" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .build();

            apiUserRepository.save(apiUser);
        });
    }


}
