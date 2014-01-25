package org.smartcity.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public interface GovernmentOffice<
		O extends GovernmentOffice,
		D extends Document,
		OT extends GovernmentOfficeType,
		A extends Address,
		T extends TelephoneNumber>
		extends Serializable {

	String TABLE_NAME                            = "GOVERNMENT_OFFICES";
	String GENERATOR_NAME                        = "GOVERNMENT_OFFICE_ID_GENERATOR";
	String SEQUENCE_NAME                         = "GOVERNMENT_OFFICE_ID_SEQUENCE";
	String ID_COLUMN_NAME                        = "GOVERNMENT_OFFICE_ID";
	String NAME_COLUMN_NAME                      = "NAME";
	String GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_TYPE_ID";

	BigInteger getID();

	String getName();

	O setName( String name );

	A getAddress();

	O setAddress( A address );

	T getTelephoneNumber();

	O setTelephoneNumber( T telephoneNumber );

	OT getOfficeType();

	O setOfficeType( OT officeType );

	Set<D> getReleasedDocuments();

	O setReleasedDocuments( Set<D> releasedDocuments );

	O addReleasedDocument( D releasedDocument );

	O addReleasedDocuments( Collection<D> releasedDocuments );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
