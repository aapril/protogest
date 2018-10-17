package com.pfe.ldb.member.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.UserAuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;
import com.pfe.ldb.member.repository.RoleRepository;
import com.pfe.ldb.member.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
	private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
		List<UserAuthoritiesEntity> userAuthorities = roleRepository.findByUserId(user.getId());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserAuthoritiesEntity role : userAuthorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}