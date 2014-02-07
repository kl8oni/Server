package org.smartcity.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import java.math.BigInteger;

class JPAEntityDAOImpl
		implements EntityDAO {

	@PersistenceContext(
			type = PersistenceContextType.EXTENDED
	)
	private EntityManager em;

	@Override
	public <T> T retrieveEntityByID( BigInteger ID, Class<T> entityClass ) {
		return null;
	}

	@Override
	public void removeEntityByID( BigInteger ID ) {

	}
}
