package org.smartcity.entity;

import java.io.Serializable;

public interface Address<A extends Address,
						 TN extends TelephoneNumber>
		extends		Serializable {

	public String getState();

	public A setState( String state );

	public String getCity();

	public A setCity( String city );

	public String getStreet();

	public A setStreet( String street );

	public short getBuildingNumber();

	public A setBuildingNumber( short buildingNumber );

	public TN getTelephoneNumber();

	public A setTelephoneNumber( TN telephoneNumber );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
