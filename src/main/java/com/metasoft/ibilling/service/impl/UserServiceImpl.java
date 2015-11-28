/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.UserService;

@Service("userService")
public class UserServiceImpl extends ModelBasedServiceImpl<UserDao, User, Integer> implements UserService
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
	public User findByUserName(String userName) {		
		return userDao.findByUserName(userName);
	}

}
