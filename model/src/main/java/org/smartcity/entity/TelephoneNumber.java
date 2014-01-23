package org.smartcity.entity;

import java.io.Serializable;

public interface TelephoneNumber<TN extends TelephoneNumber>
		extends		Serializable {

	String TELEPHONE_NUMBER_COLUMN_NAME = "TELEPHONE_NUMBER";

	public TN setTelephoneNumberValue( String telephoneNumberValue );

	public String getTelephoneNumberValue();

	public TN setStateCode( Short stateCode );

	public TN setCityCode( Short cityCode );

	public TN setTelephonePattern( String pattern );

	public TN setNumber( Integer number );

	public String getTelephonePattern();

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
