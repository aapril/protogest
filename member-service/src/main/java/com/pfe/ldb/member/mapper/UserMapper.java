package com.pfe.ldb.member.mapper;

import org.springframework.expression.ParseException;

import com.pfe.ldb.core.protogest.user.User;
import com.pfe.ldb.core.protogest.utils.AbstractModel;
import com.pfe.ldb.entities.AbstractEntity;
import com.pfe.ldb.entities.MemberEntity;
import com.pfe.ldb.entities.UserEntity;
import com.pfe.ldb.member.imapper.IMapper;


public class UserMapper implements IMapper {

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
		UserEntity userEntity = new UserEntity(user.getEmail(), user.getPassword(), memberEntity);
		return userEntity;
	}

}
