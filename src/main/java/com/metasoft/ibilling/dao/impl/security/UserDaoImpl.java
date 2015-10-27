/**
 * 
 */
package com.metasoft.ibilling.dao.impl.security;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.security.UserDao;
import com.metasoft.ibilling.model.SecUser;

/**
 * @author
 * 
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDaoImpl<SecUser, Integer> implements UserDao {

	public UserDaoImpl() {
		super(SecUser.class);
	}

	@Override
	public SecUser findByUserName(String userName) {
		return (SecUser) getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("userName", userName)).uniqueResult();
	}

}
