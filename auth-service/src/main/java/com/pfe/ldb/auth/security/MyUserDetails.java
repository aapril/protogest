package com.pfe.ldb.auth.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.repositories.RoleRepository;
import com.pfe.ldb.auth.repositories.UserRepository;
import com.pfe.ldb.entities.AuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) 
		  throws UsernameNotFoundException {
	  
    final UserEntity user = userRepository.findByUsername(username);
    final Collection<AuthoritiesEntity> roles = roleRepository.findByUserId(user.getId());
    
    return org.springframework.security.core.userdetails.User
        .withUsername(username)
        .password(user.getPassword())
        .authorities((GrantedAuthority[]) roles.toArray())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }

}
