package com.pfe.ldb.event.mapper;

import org.springframework.expression.ParseException;

import com.pfe.ldb.core.protogest.event.EventGroup;
import com.pfe.ldb.core.protogest.utils.AbstractModel;
import com.pfe.ldb.entities.AbstractEntity;
import com.pfe.ldb.entities.EventGroupEntity;
import com.pfe.ldb.event.imapper.IMapper;

public class EventGroupMapper implements IMapper{

	@Override
	public AbstractModel convertToDTO(AbstractEntity entity) throws ParseException {

		EventGroupEntity eventGroupEntity = (EventGroupEntity) entity;
		EventGroup eventGroup = new EventGroup(eventGroupEntity.getId(), eventGroupEntity.getName());
		return eventGroup;
	}

	@Override
	public AbstractEntity convertToEntity(AbstractModel model) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
