package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Set;

public interface User<U extends User,
					  E extends Email,
					  D extends Document>
		extends		Serializable {

	String TABLE_NAME                       = "USERS";
	String GENERATOR_NAME                   = "USER_ID_GENERATOR";
	String SEQUENCE_NAME                    = "USER_ID_SEQUENCE";
	String ID_COLUMN_NAME                   = "USER_ID";
	String LAST_NAME_COLUMN_NAME            = "LAST_NAME";
	String FIRST_NAME_COLUMN_NAME           = "FIRST_NAME";
	String MIDDLE_NAME_COLUMN_NAME          = "MIDDLE_NAME";
	String NICK_NAME_COLUMN_NAME            = "NICK_NAME";
	String PASSWORD_COLUMN_NAME             = "PASSWORD";
	String IDENTIFY_DOCUMENT_ID_COLUMN_NAME = "IDENTIFY_DOCUMENT_ID";

	public BigInteger getID();

	public String getLastName();

	public U setLastName( String lastName );

	public String getFirstName();

	public U setFirstName( String firstName );

	public String getMiddleName();

	public U setMiddleName( String middleName );

	public String getNickName();

	public U setNickName( String nickName );

	public String getPassword();

	public U setPassword( String password );

	public E getMainEmail();

	public U setMainEmail( E email );

	public Set<E> getUserEmails();

	public U setUserEmails( Set<E> userEmails );

	public U addUserEmail( E email );

	public U addUserEmails( Collection<E> emails );

	public D getIdentifyDocument();

	public U setIdentifyDocument( D identifyDocument );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}