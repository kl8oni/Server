package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

public interface Email<
		E extends Email,
		U extends User>
		extends Serializable {

	String TABLE_NAME                = "USER_EMAILS";
	String GENERATOR_NAME            = "USER_EMAIL_ID_GENERATOR";
	String SEQUENCE_NAME             = "USER_EMAIL_ID_SEQUENCE";
	String ID_COLUMN_NAME            = "USER_EMAIL_ID";
	String EMAIL_ADDRESS_COLUMN_NAME = "EMAIL_ADDRESS";
	String USER_ID_COLUMN_NAME       = "USER_ID";
	String MAIN_EMAIL_COLUMN         = "MAIN_EMAIL";

	BigInteger getID();

	String getEmailAddress();

	E setEmailAddress( String emailAddress );

	U getOwner();

	E setOwner( U owner );

	boolean isMainEmail();

	E setMainEmail( boolean mainEmail );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
