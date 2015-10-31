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

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(User e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveOrUpdate(User e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
