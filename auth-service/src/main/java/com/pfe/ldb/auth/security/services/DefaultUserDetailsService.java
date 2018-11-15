package com.pfe.ldb.auth.security.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.repositories.UserRepository;
import com.pfe.ldb.auth.repositories.UserRoleRepository;
import com.pfe.ldb.entities.AuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserRoleRepository userRoleRepository;

  
  @Override
  public UserDetails loadUserByUsername(final String username) 
		  throws UsernameNotFoundException {
	  
    final UserEntity userEntity = userRepository.findByUsername(username);
    final Collection<AuthoritiesEntity> roleEntities = userRoleRepository.findByUserId(userEntity.getId());
    
    return org.springframework.security.core.userdetails.User
        .withUsername(username)
        .password(userEntity.getPassword())
        .authorities((GrantedAuthority[]) roleEntities.toArray())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }
}
