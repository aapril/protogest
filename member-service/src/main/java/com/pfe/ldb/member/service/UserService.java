package com.pfe.ldb.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.ldb.core.protogest.user.Authoritie;
import com.pfe.ldb.core.protogest.user.User;
import com.pfe.ldb.entities.MemberEntity;
import com.pfe.ldb.entities.UserAuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;
import com.pfe.ldb.member.imapper.IMapper;
import com.pfe.ldb.member.iservice.IUserService;
import com.pfe.ldb.member.mapper.MemberMapper;
import com.pfe.ldb.member.mapper.UserMapper;
import com.pfe.ldb.member.repository.AuthoritiesRepository;
import com.pfe.ldb.member.repository.MemberRepository;
import com.pfe.ldb.member.repository.RoleRepository;
import com.pfe.ldb.member.repository.UserAuthoritiesRepository;
import com.pfe.ldb.member.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
	@Autowired
	private UserAuthoritiesRepository userAuthoritiesRepository;
    
    private final String USER = "USER";
   
    private final static IMapper userMapper = new UserMapper();
    private final static IMapper memberMapper = new MemberMapper();

	@Override
	public List<UserAuthoritiesEntity> loadByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User loadByUsername(String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		User user = (User) userMapper.convertToDTO(userEntity);
		if(user != null) {
			UserAuthoritiesEntity userAuthoritiesEntity = userAuthoritiesRepository.findByUserId(userEntity.getId());
			Authoritie authority = new Authoritie(userAuthoritiesEntity.getId(), userAuthoritiesEntity.getAuthority().getName());
			user.setAuthorities(authority);

		}
		
	/*	List<UserAuthoritiesEntity> userAuthorities = null;
		if(userEntity != null) {
			userAuthorities = roleRepository.findByUserId(userEntity.getId());
		}*/
		return user;
	}
	@Override
	public void save(User user) {
		
		MemberEntity memberEntity = memberRepository.save((MemberEntity)memberMapper.convertToEntity(user));
		UserEntity userEntity = (UserEntity)userMapper.convertToEntity(user);
		userEntity.setMember(memberEntity);
		userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
		UserEntity updatedUser = userRepository.save(userEntity);
		UserAuthoritiesEntity userAuthorityEntity = new UserAuthoritiesEntity(updatedUser, authoritiesRepository.findByName(USER));
		userAuthoritiesRepository.save(userAuthorityEntity);
		
	}

}
