package org.smartcity.entity;

import java.io.Serializable;

public interface TelephoneNumber<TN extends TelephoneNumber>
		extends Serializable {

	public String getTelephoneNumberValue();

	public TN setStateCode( short stateCode );

	public TN setCityCode( short cityCode );

	public TN setTelephonePattern( String pattern );

	public TN setNumber( int number );

	public String getTelephonePattern();

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
