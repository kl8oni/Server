package org.smartcity.dao;

import org.smartcity.entity.Address;
import org.smartcity.entity.TelephoneNumber;

interface AddressDAO {

	public Address create(
			String state,
			String city,
			String street,
			short buildingNumber,
			TelephoneNumber telephoneNumber );

}