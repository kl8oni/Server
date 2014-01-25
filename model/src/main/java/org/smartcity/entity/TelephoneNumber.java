package org.smartcity.entity;

import java.io.Serializable;

public interface TelephoneNumber<
		TN extends TelephoneNumber>
		extends Serializable {

	String TELEPHONE_NUMBER_COLUMN_NAME = "TELEPHONE_NUMBER";

	TN setTelephoneNumberValue( String telephoneNumberValue );

	String getTelephoneNumberValue();

	TN setStateCode( Short stateCode );

	TN setCityCode( Short cityCode );

	TN setTelephonePattern( String pattern );

	TN setNumber( Integer number );

	String getTelephonePattern();

	boolean equals( Object object );

	int hashCode();

	String toString();

}
