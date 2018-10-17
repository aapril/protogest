package com.pfe.ldb.member.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import com.pfe.ldb.core.protogest.user.Authoritie;
import com.pfe.ldb.core.protogest.user.User;
import com.pfe.ldb.core.protogest.utils.AbstractModel;
import com.pfe.ldb.entities.AbstractEntity;
import com.pfe.ldb.entities.MemberEntity;
import com.pfe.ldb.entities.UserAuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;
import com.pfe.ldb.member.imapper.IMapper;
import com.pfe.ldb.member.repository.UserAuthoritiesRepository;



public class UserMapper implements IMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AbstractModel convertToDTO(AbstractEntity entity) throws ParseException {
		UserEntity userEntity = (UserEntity) entity;
		if (userEntity != null) {

			User user = new User(userEntity.getMember().getEmail(), userEntity.getMember().getEmail(),
					userEntity.getMember().getFirstName(), userEntity.getMember().getLastName(), null);

			return user;
		}
		return null;
	}

	@Override
	public AbstractEntity convertToEntity(AbstractModel model) throws ParseException {
		User user = (User) model;
		MemberEntity memberEntity = new MemberEntity(user.getFirstName(), user.getLastName(), user.getEmail());
		UserEntity userEntity = new UserEntity(memberEntity, user.getEmail(), user.getPassword());
		return userEntity;
	}

}
