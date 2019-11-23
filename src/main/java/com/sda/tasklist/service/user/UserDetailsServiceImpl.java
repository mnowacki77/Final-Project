package com.sda.tasklist.service.user;

import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.model.user.UserEntity;
import com.sda.tasklist.model.user.UserRoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> byLogin = userRepository.findUserWithRolesByLogin(login);
        UserEntity userEntity = byLogin.orElseThrow(() -> new UsernameNotFoundException("Not found user with name: " + login));

        return new User(userEntity.getLogin(), userEntity.getPassword(), grantedAuthorities(userEntity.getRoles()));
    }

    private List<GrantedAuthority> grantedAuthorities(Set<UserRoleEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
        }
        return authorities;
    }
}
