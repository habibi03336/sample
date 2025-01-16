package com.hollysgang.sample.framework.module.security;

import com.hollysgang.sample.framework.module.entity.Account;
import com.hollysgang.sample.framework.module.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class DemoUserDetailsService implements UserDetailsService {

    public DemoUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByName(username).orElseThrow();
        return DemoUserDetails.builder()
                .name(account.getName())
                .demoGrantedAuthorities(
                        account.getUserAuthorities().stream().map(
                                (auth) -> DemoGrantedAuthority.builder()
                                        .authority(auth.getAuthority().getId().toString())
                                        .build()
                        ).toArray(DemoGrantedAuthority[]::new)
                )
                .build();
    }
}
