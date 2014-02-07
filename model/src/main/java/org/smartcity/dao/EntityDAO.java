package org.smartcity.dao;


import java.math.BigInteger;

interface EntityDAO {

	<T> T retrieveEntityByID( BigInteger ID, Class<T> entityClass );

	void removeEntityByID( BigInteger ID );

}
