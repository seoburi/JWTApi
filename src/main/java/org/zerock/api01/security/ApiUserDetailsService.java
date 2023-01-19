package org.zerock.api01.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.api01.domain.ApiUser;
import org.zerock.api01.domain.ApiUserRepository;
import org.zerock.api01.dto.ApiUserDto;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ApiUserDetailsService implements UserDetailsService {

    private final ApiUserRepository apiUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ApiUser> result = apiUserRepository.findById(username);

        ApiUser apiUser = result.orElseThrow(() -> new UsernameNotFoundException("Cannot find mid"));

        log.info("ApiUserDetailsService apiUser -------------------------");

        ApiUserDto dto = new ApiUserDto(apiUser.getMid(), apiUser.getMpw(), List.of(new SimpleGrantedAuthority("ROLE_USER")));

        log.info(dto);

        return dto;
    }
}
