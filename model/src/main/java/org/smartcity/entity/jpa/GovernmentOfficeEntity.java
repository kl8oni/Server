package org.smartcity.entity.jpa;

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

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;

@Entity
@Table(
		name = GovernmentOffice.TABLE_NAME
)
public class GovernmentOfficeEntity
		implements	GovernmentOffice<GovernmentOfficeEntity, DocumentEntity, GovernmentOfficeTypeEntity,
									 AddressEmbedded, TelephoneNumberEmbedded> {

	private static final Log LOG = LogFactory.getLog( GovernmentOfficeEntity.class );

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
			initialValue = 1
	)
	@Column(
			name = GovernmentOffice.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger                 ID;
	@Column(
			name = GovernmentOffice.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                     name;
	@Embedded
	private AddressEmbedded            address;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOffice.GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeTypeEntity officeType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = DocumentEntity.OFFICE_FIELD
	)
	private Set<DocumentEntity>        releasedDocuments;

	public GovernmentOfficeEntity() {
	}

	public GovernmentOfficeEntity(
			String name,
			AddressEmbedded address,
			GovernmentOfficeTypeEntity officeType ) {
		setName( name )
				.setAddress( address )
				.setOfficeType( officeType )
				.setReleasedDocuments( new HashSet<DocumentEntity>() );
	}

	@Override
	public BigInteger getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public GovernmentOfficeEntity setName( String name ) {
		this.name = name;
		return this;
	}

	@Override
	public AddressEmbedded getAddress() {
		return address;
	}

	@Override
	public GovernmentOfficeEntity setAddress( AddressEmbedded address ) {
		this.address = address;
		return this;
	}

	@Override
	public TelephoneNumberEmbedded getTelephoneNumber() {
		return address.getTelephoneNumber();
	}

	@Override
	public GovernmentOfficeEntity setTelephoneNumber( TelephoneNumberEmbedded telephoneNumber ) {
		address.setTelephoneNumber( telephoneNumber );
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity getOfficeType() {
		return officeType;
	}

	@Override
	public GovernmentOfficeEntity setOfficeType( GovernmentOfficeTypeEntity officeType ) {
		this.officeType = officeType;
		return this;
	}

	@Override
	public Set<DocumentEntity> getReleasedDocuments() {
		return releasedDocuments;
	}

	@Override
	public GovernmentOfficeEntity setReleasedDocuments( Set<DocumentEntity> releasedDocuments ) {
		this.releasedDocuments = releasedDocuments;
		return this;
	}

	@Override
	public GovernmentOfficeEntity addReleasedDocument( DocumentEntity releasedDocument ) {
		releasedDocuments.add( releasedDocument );
		return this;
	}

	@Override
	public GovernmentOfficeEntity addReleasedDocuments( Collection<DocumentEntity> releasedDocuments ) {
		this.releasedDocuments.addAll( releasedDocuments );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			GovernmentOfficeEntity governmentOffice = (GovernmentOfficeEntity)object;
			return governmentOffice.getName().equals( getName() )
				   && governmentOffice.getOfficeType().equals( getOfficeType() )
				   && governmentOffice.getAddress().equals( getAddress() )
				   && governmentOffice.getTelephoneNumber().equals( getTelephoneNumber() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getOfficeType().hashCode() + getAddress().hashCode() + getTelephoneNumber().hashCode();
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
