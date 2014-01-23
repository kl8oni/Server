package org.smartcity.entity;

import java.io.Serializable;

public interface Address<A extends Address,
						 TN extends TelephoneNumber>
		extends		Serializable {

	String STATE_COLUMN_NAME = "STATE";
	String CITY_COLUMN_NAME = "CITY";
	String STREET_COLUMN_NAME = "STREET";
	String BUILDING_NUMBER_COLUMN_NAME = "BUILDING_NUMBER";

	public String getState();

	public A setState( String state );

	public String getCity();

	public A setCity( String city );

	public String getStreet();

	public A setStreet( String street );

	public Short getBuildingNumber();

	public A setBuildingNumber( Short buildingNumber );

	public TN getTelephoneNumber();

	public A setTelephoneNumber( TN telephoneNumber );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
