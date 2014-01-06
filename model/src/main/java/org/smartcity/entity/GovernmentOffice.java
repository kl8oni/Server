package org.smartcity.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public interface GovernmentOffice<O extends GovernmentOffice,
								  D extends Document,
								  OT extends GovernmentOfficeType,
								  A extends Address,
								  T extends TelephoneNumber>
		extends		Serializable {

	String TABLE_NAME                            = "GOVERNMENT_OFFICES";
	String GENERATOR_NAME                        = "GOVERNMENT_OFFICE_ID_GENERATOR";
	String SEQUENCE_NAME                         = "GOVERNMENT_OFFICE_ID_SEQUENCE";
	String ID_COLUMN_NAME                        = "GOVERNMENT_OFFICE_ID";
	String NAME_COLUMN_NAME                      = "NAME";
	String GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_TYPE_ID";

	public abstract BigInteger getID();

	public abstract String getName();

	public abstract O setName( String name );

	public abstract A getAddress();

	public abstract O setAddress( A address );

	public abstract T getTelephoneNumber();

	public abstract O setTelephoneNumber( T telephoneNumber );

	public abstract OT getOfficeType();

	public abstract O setOfficeType( OT officeType );

	public abstract Set<D> getReleasedDocuments();

	public abstract O setReleasedDocuments( Set<D> releasedDocuments );

	public abstract O addReleasedDocument( D releasedDocument );

	public abstract O addReleasedDocuments( Collection<D> releasedDocuments );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
