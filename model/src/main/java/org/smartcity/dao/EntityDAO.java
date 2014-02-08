package org.smartcity.dao;

import java.math.BigInteger;

interface EntityDAO<EM> {

	<T> T retrieveEntityByID( BigInteger ID, Class<T> entityClass );

	void removeEntityByID( BigInteger ID );

	EM getEntitiesManager();

}
