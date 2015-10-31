/**
 * 
 */
package com.metasoft.ibilling.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.User;

/**
 * @author
 * 
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User findByUserName(String userName) {
		return (User) getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("userName", userName)).uniqueResult();
	}

}
