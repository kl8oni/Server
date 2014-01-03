package org.smartcity.entity.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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

import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;

import org.smartcity.entity.jpa.converters.LongToBigIntegerConverter;

@Entity
@Table(
		name = GovernmentOfficeType.TABLE_NAME
)
public class GovernmentOfficeTypeEntity
		implements	GovernmentOfficeType<GovernmentOfficeTypeEntity, DocumentTemplateEntity, GovernmentOfficeEntity> {

	@Id
	@GeneratedValue(
			generator = GovernmentOfficeType.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = GovernmentOfficeType.GENERATOR_NAME,
			sequenceName = GovernmentOfficeType.SEQUENCE_NAME,
			initialValue = 1
	)
	@Column(
			name = GovernmentOfficeType.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	@Convert(
			converter = LongToBigIntegerConverter.class
	)
	private BigInteger                      ID;
	@Column(
			name = GovernmentOfficeType.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                          name;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOfficeType.PARENT_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeTypeEntity      parent;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOfficeType.ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOfficeType.PARENT_ID_COLUMN_NAME
	)
	private Set<GovernmentOfficeTypeEntity> children;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOfficeType.ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOffice.GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME
	)
	private Set<GovernmentOfficeEntity>     officesThisType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOfficeType.ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = DocumentTemplate.GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME
	)
	private Set<DocumentTemplateEntity>     documentTemplates;

	public GovernmentOfficeTypeEntity() {
	}

	public GovernmentOfficeTypeEntity( String name, GovernmentOfficeTypeEntity parent ) {
		setName( name )
				.setParent( parent )
				.setChildren( new HashSet<GovernmentOfficeTypeEntity>() )
				.setOfficesThisType( new HashSet<GovernmentOfficeEntity>() )
				.setDocumentTemplates( new HashSet<DocumentTemplateEntity>() );
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
	public GovernmentOfficeTypeEntity setName( String name ) {
		this.name = name;
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity getParent() {
		return parent;
	}

	@Override
	public GovernmentOfficeTypeEntity setParent( GovernmentOfficeTypeEntity parent ) {
		this.parent = parent;
		return this;
	}

	@Override
	public Set<GovernmentOfficeTypeEntity> getChildren() {
		return children;
	}

	@Override
	public GovernmentOfficeTypeEntity setChildren( Set<GovernmentOfficeTypeEntity> children ) {
		this.children = children;
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity addChild( GovernmentOfficeTypeEntity child ) {
		children.add( child );
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity addChildren( Collection<GovernmentOfficeTypeEntity> children ) {
		this.children.addAll( children );
		return this;
	}

	@Override
	public Set<GovernmentOfficeEntity> getOfficesThisType() {
		return officesThisType;
	}

	@Override
	public GovernmentOfficeTypeEntity setOfficesThisType( Set<GovernmentOfficeEntity> officesThisType ) {
		this.officesThisType = officesThisType;
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity addOfficeThisType( GovernmentOfficeEntity officeThisType ) {
		officesThisType.add( officeThisType );
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity addOfficesThisType( Collection<GovernmentOfficeEntity> officesThisType ) {
		this.officesThisType.addAll( officesThisType );
		return this;
	}

	@Override
	public Set<DocumentTemplateEntity> getDocumentTemplates() {
		return documentTemplates;
	}

	@Override
	public GovernmentOfficeTypeEntity setDocumentTemplates( Set<DocumentTemplateEntity> documentTemplates ) {
		this.documentTemplates = documentTemplates;
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity addDocumentTemplate( DocumentTemplateEntity documentTemplate ) {
		documentTemplates.add( documentTemplate );
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity addDocumentTemplates( Collection<DocumentTemplateEntity> documentTemplates ) {
		this.documentTemplates.addAll( documentTemplates );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			GovernmentOfficeTypeEntity governmentOfficeType = (GovernmentOfficeTypeEntity)object;
			return governmentOfficeType.getName().equals( getName() )
				   && governmentOfficeType.getParent().equals( getParent() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getParent().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Government Office Type with ID = " )
				.append( getID() )
				.append( " Name = " )
				.append( getName() );
		return sb.toString();
	}

}