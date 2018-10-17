package com.pfe.ldb.member.imapper;

import org.springframework.expression.ParseException;

import com.pfe.ldb.core.protogest.user.User;
import com.pfe.ldb.core.protogest.utils.AbstractModel;
import com.pfe.ldb.entities.AbstractEntity;
import com.pfe.ldb.entities.UserEntity;
/**
 *  @createdBy: Walid Bezzaoui , 2018
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 **/
public interface IMapper {

	public AbstractModel convertToDTO(AbstractEntity entity) throws ParseException;
	public AbstractEntity convertToEntity(AbstractModel model) throws ParseException;
}
