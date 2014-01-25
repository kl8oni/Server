package org.smartcity.entity.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import org.smartcity.entity.GovernmentOfficeType;

@Entity
@Table(
		name = GovernmentOfficeType.TABLE_NAME
)
public class GovernmentOfficeTypeEntity
		implements GovernmentOfficeType<
		GovernmentOfficeTypeEntity,
		DocumentTemplateEntity,
		GovernmentOfficeEntity> {

	private static final Log LOG = LogFactory.getLog( GovernmentOfficeTypeEntity.class );

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD                 = "ID";
	public static final String NAME_FIELD               = "name";
	public static final String PARENT_FIELD             = "parent";
	public static final String CHILDREN_FIELD           = "children";
	public static final String OFFICES_THIS_TYPE_FIELD  = "officesThisType";
	public static final String DOCUMENT_TEMPLATES_FIELD = "documentTemplates";

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
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeTypeEntity      parent;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = GovernmentOfficeTypeEntity.PARENT_FIELD
	)
	private Set<GovernmentOfficeTypeEntity> children;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = GovernmentOfficeEntity.OFFICE_TYPE_FIELD
	)
	private Set<GovernmentOfficeEntity>     officesThisType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = DocumentTemplateEntity.GOVERNMENT_OFFICE_TYPE_FIELD
	)
	private Set<DocumentTemplateEntity>     documentTemplates;

	public GovernmentOfficeTypeEntity() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public GovernmentOfficeTypeEntity( String name, GovernmentOfficeTypeEntity parent ) {
		LOG.debug( "Constructor with parameters is invoked" );
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
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity getParent() {
		return parent;
	}

	@Override
	public GovernmentOfficeTypeEntity setParent( GovernmentOfficeTypeEntity parent ) {
		LOG.debug( "Parent is " + parent );
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
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			GovernmentOfficeTypeEntity governmentOfficeType = (GovernmentOfficeTypeEntity) object;
			return governmentOfficeType.getName().equals( getName() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
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
