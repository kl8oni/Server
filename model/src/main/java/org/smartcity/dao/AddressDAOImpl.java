package org.smartcity.dao;


import org.smartcity.entity.Address;
import org.smartcity.entity.TelephoneNumber;

class AddressDAOImpl
		implements AddressDAO {

	@Override
	public Address create(
			String state, String city, String street, short buildingNumber, TelephoneNumber telephoneNumber ) {
		return new Address( state, city, street, buildingNumber, telephoneNumber );
	}

}
