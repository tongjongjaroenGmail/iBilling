package com.metasoft.ibilling.dao.impl.standard;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.standard.PositionDao;
import com.metasoft.ibilling.model.StdPosition;

@Repository("positionDao")
@Transactional
public class PositionDaoImpl extends AbstractDaoImpl<StdPosition, Integer> implements PositionDao {
	public PositionDaoImpl() {
		super(StdPosition.class);
	}
}
