/**
 * 
 */
package com.metasoft.ibilling.service.impl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.dao.security.UserDao;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.service.impl.ModelBasedServiceImpl;
import com.metasoft.ibilling.service.security.UserService;

@Service("userService")
public class UserServiceImpl extends ModelBasedServiceImpl<UserDao, SecUser, Integer> implements UserService
{
    private UserDao userDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public UserServiceImpl(UserDao dao)
    {
        super(dao);
        this.userDao = dao;
    }

	@Override
	public SecUser findByUserName(String userName) {		
		return userDao.findByUserName(userName);
	}
}
