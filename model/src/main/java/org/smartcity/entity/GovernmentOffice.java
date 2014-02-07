package org.smartcity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * todo javadoc
 */
@Entity
@Table(
		name = GovernmentOffice.TABLE_NAME
)
public class GovernmentOffice
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( GovernmentOffice.class );

	public static final String TABLE_NAME                            = "GOVERNMENT_OFFICES";
	public static final String GENERATOR_NAME                        = "GOVERNMENT_OFFICE_ID_GENERATOR";
	public static final String SEQUENCE_NAME                         = "GOVERNMENT_OFFICE_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME                        = "GOVERNMENT_OFFICE_ID";
	public static final String NAME_COLUMN_NAME                      = "NAME";
	public static final String GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_TYPE_ID";

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD                 = "ID";
	public static final String NAME_FIELD               = "name";
	public static final String ADDRESS_FIELD            = "address";
	public static final String OFFICE_TYPE_FIELD        = "officeType";
	public static final String RELEASED_DOCUMENTS_FIELD = "releasedDocuments";

	@Id
	@GeneratedValue(
			generator = GovernmentOffice.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = GovernmentOffice.GENERATOR_NAME,
			sequenceName = GovernmentOffice.SEQUENCE_NAME,
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = GovernmentOffice.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger           ID;
	@Column(
			name = GovernmentOffice.NAME_COLUMN_NAME,
			nullable = false
	)
	private String               name;
	@Embedded
	private Address              address;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOffice.GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeType officeType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = Document.OFFICE_FIELD
	)
	private Set<Document>        releasedDocuments;

	public GovernmentOffice() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public GovernmentOffice(
			String name,
			Address address,
			GovernmentOfficeType officeType ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setName( name )
				.setAddress( address )
				.setOfficeType( officeType )
				.setReleasedDocuments( new HashSet<Document>() );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public GovernmentOffice setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public GovernmentOffice setAddress( Address address ) {
		LOG.debug( "Address is " + address );
		this.address = address;
		return this;
	}

	public TelephoneNumber getTelephoneNumber() {
		return address.getTelephoneNumber();
	}

	public GovernmentOffice setTelephoneNumber( TelephoneNumber telephoneNumber ) {
		address.setTelephoneNumber( telephoneNumber );
		return this;
	}

	public GovernmentOfficeType getOfficeType() {
		return officeType;
	}

	public GovernmentOffice setOfficeType( GovernmentOfficeType officeType ) {
		LOG.debug( "Office type is " + officeType );
		this.officeType = officeType;
		return this;
	}

	public Set<Document> getReleasedDocuments() {
		return releasedDocuments;
	}

	public GovernmentOffice setReleasedDocuments( Set<Document> releasedDocuments ) {
		this.releasedDocuments = releasedDocuments;
		return this;
	}

	public GovernmentOffice addReleasedDocument( Document releasedDocument ) {
		releasedDocuments.add( releasedDocument );
		return this;
	}

	public GovernmentOffice addReleasedDocuments( Collection<Document> releasedDocuments ) {
		this.releasedDocuments.addAll( releasedDocuments );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			GovernmentOffice governmentOffice = (GovernmentOffice) object;
			return governmentOffice.getName().equals( getName() )
				   && governmentOffice.getOfficeType().equals( getOfficeType() )
				   && governmentOffice.getAddress().equals( getAddress() )
				   && governmentOffice.getTelephoneNumber().equals( getTelephoneNumber() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode()
			   + getOfficeType().hashCode()
			   + getAddress().hashCode()
			   + getTelephoneNumber().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Government Office with ID = " )
				.append( getID() )
				.append( " Name = " )
				.append( getName() )
				.append( " ]" );
		return sb.toString();
	}

}
