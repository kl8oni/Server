package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;

public interface Document<
		D extends Document,
		U extends User,
		DT extends DocumentTemplate,
		O extends GovernmentOffice>
		extends Serializable {

	String TABLE_NAME                       = "DOCUMENTS";
	String GENERATOR_NAME                   = "DOCUMENT_ID_GENERATOR";
	String SEQUENCE_NAME                    = "DOCUMENT_ID_SEQUENCE";
	String ID_COLUMN_NAME                   = "DOCUMENT_ID";
	String NAME_COLUMN_NAME                 = "NAME";
	String SERIES_COLUMN_NAME               = "SERIES";
	String NUMBER_COLUMN_NAME               = "NUMBER";
	String DELIVERY_DATE_COLUMN_NAME        = "DELIVERY_DATE";
	String USER_ID_COLUMN_NAME              = "USER_ID";
	String GOVERNMENT_OFFICE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_ID";
	String DOCUMENT_TEMPLATE_ID_COLUMN_NAME = "DOCUMENT_TEMPLATE_ID";

	BigInteger getID();

	String getName();

	D setName( String name );

	String getSeries();

	D setSeries( String series );

	Long getNumber();

	D setNumber( Long number );

	Date getDeliveryDate();

	D setDeliveryDate( Date deliveryDate );

	U getOwner();

	D setOwner( U owner );

	DT getTemplate();

	D setTemplate( DT template );

	O getOffice();

	D setOffice( O office );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
