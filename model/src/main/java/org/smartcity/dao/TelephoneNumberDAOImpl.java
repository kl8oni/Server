package org.smartcity.dao;


import org.smartcity.entity.TelephoneNumber;

public class TelephoneNumberDAOImpl
		implements TelephoneNumberDAO {

	@Override
	public TelephoneNumber create( short stateCode, short cityCode, int number, String pattern ) {
		return new TelephoneNumber( stateCode, cityCode, number, pattern );
	}

}
