/**
 * 
 */
package com.metasoft.ibilling.service;

import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.User;


public interface UserService extends ModelBasedService<UserDao, User, Integer>
{
	User findByUserName(String username);
}
