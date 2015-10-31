package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.DepartmentDao;
import com.metasoft.ibilling.model.Department;

@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl extends AbstractDaoImpl<Department, Integer> implements DepartmentDao {
	public DepartmentDaoImpl() {
		super(Department.class);
	}
}
