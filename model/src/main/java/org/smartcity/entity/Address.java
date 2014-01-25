package org.smartcity.entity;

import java.io.Serializable;

public interface Address<
		A extends Address,
		TN extends TelephoneNumber>
		extends Serializable {

	String STATE_COLUMN_NAME           = "STATE";
	String CITY_COLUMN_NAME            = "CITY";
	String STREET_COLUMN_NAME          = "STREET";
	String BUILDING_NUMBER_COLUMN_NAME = "BUILDING_NUMBER";

	String getState();

	A setState( String state );

	String getCity();

	A setCity( String city );

	String getStreet();

	A setStreet( String street );

	Short getBuildingNumber();

	A setBuildingNumber( Short buildingNumber );

	TN getTelephoneNumber();

	A setTelephoneNumber( TN telephoneNumber );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
