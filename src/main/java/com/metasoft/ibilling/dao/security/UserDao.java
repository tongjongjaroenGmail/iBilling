package com.metasoft.ibilling.dao.security;

import com.metasoft.ibilling.dao.AbstractDao;
import com.metasoft.ibilling.model.SecUser;

public interface UserDao extends AbstractDao<SecUser, Integer>{

	SecUser findByUserName(String userName);

}