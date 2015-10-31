package com.metasoft.ibilling.dao;

import com.metasoft.ibilling.model.User;

public interface UserDao extends AbstractDao<User, Integer>{

	User findByUserName(String userName);

}