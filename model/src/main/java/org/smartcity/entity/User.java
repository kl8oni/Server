package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Set;

public interface User<
		U extends User,
		E extends Email,
		D extends Document>
		extends Serializable {

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

	BigInteger getID();

	String getLastName();

	U setLastName( String lastName );

	String getFirstName();

	U setFirstName( String firstName );

	String getMiddleName();

	U setMiddleName( String middleName );

	String getNickName();

	U setNickName( String nickName );

	String getPassword();

	U setPassword( String password );

	E getMainEmail();

	U setMainEmail( E email );

	Set<E> getUserEmails();

	U setUserEmails( Set<E> userEmails );

	U addUserEmail( E email );

	U addUserEmails( Collection<E> emails );

	D getIdentifyDocument();

	U setIdentifyDocument( D identifyDocument );

	Set<D> getDocuments();

	U setDocuments( Set<D> documents );

	U addDocument( D document );

	U addDocuments( Collection<D> documents );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
