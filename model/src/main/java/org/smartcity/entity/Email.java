package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

public interface Email<E extends Email,
					   U extends User>
		extends		Serializable {

	String TABLE_NAME                = "USER_EMAILS";
	String GENERATOR_NAME            = "USER_EMAIL_ADDRESS_ID_GENERATOR";
	String SEQUENCE_NAME             = "USER_EMAIL_ADDRESS_ID_SEQUENCE";
	String ID_COLUMN_NAME            = "EMAIL_ADDRESS_ID";
	String EMAIL_ADDRESS_COLUMN_NAME = "EMAIL_ADDRESS";
	String USER_ID_COLUMN_NAME       = "USER_ID";
	String IS_MAIN_EMAIL_COLUMN      = "IS_MAIN_EMAIL";

	public BigInteger getID();

	public String getEmailAddress();

	public E setEmailAddress( String emailAddress );

	public U getOwner();

	public E setOwner( U owner );

	public boolean isMainEmail();

	public E setMainEmail( boolean mainEmail );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
