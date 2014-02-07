package org.smartcity.dao;

import org.smartcity.entity.TelephoneNumber;

interface TelephoneNumberDAO {

	public TelephoneNumber create( short stateCode, short cityCode, int number, String pattern );

}