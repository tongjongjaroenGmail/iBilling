/**
 * 
 */
package com.metasoft.ibilling.service.security;

import com.metasoft.ibilling.dao.security.UserDao;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.service.ModelBasedService;


public interface UserService extends ModelBasedService<UserDao, SecUser, Integer>
{
	SecUser findByUserName(String username);
}
