package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.PositionDao;
import com.metasoft.ibilling.model.Position;

@Repository("positionDao")
@Transactional
public class PositionDaoImpl extends AbstractDaoImpl<Position, Integer> implements PositionDao {
	public PositionDaoImpl() {
		super(Position.class);
	}
}
