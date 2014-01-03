package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;

public interface Document <D extends Document,
								U extends User,
								DT extends DocumentTemplate,
								O extends GovernmentOffice>
		extends		Serializable {

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

	public BigInteger getID();

	public String getName();

	public D setName( String name );

	public String getSeries();

	public D setSeries( String series );

	public long getNumber();

	public D setNumber( long number );

	public Date getDeliveryDate();

	public D setDeliveryDate( Date deliveryDate );

	public U getOwner();

	public D setOwner( U owner );

	public DT getTemplate();

	public D setTemplate( DT template );

	public O getOffice();

	public D setOffice( O office );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}