package com.pfe.ldb.auth.services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.pfe.ldb.entities.UserEntity;

@Service
public interface UserService {

	public String signin(final String username, final String password);

	public String signup(final UserEntity user);

	public void delete(final String username);

	public UserEntity search(final String username);

	public UserEntity whoami(final HttpServletRequest req);

}