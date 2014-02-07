package org.smartcity.entity;

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

import java.io.Serializable;
import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * todo javadoc
 */
@Entity
@Table(
		name = GovernmentOfficeType.TABLE_NAME
)
public class GovernmentOfficeType
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( GovernmentOfficeType.class );

	public static final String TABLE_NAME            = "GOVERNMENT_OFFICE_TYPES";
	public static final String GENERATOR_NAME        = "GOVERNMENT_OFFICE_TYPE_ID_GENERATOR";
	public static final String SEQUENCE_NAME         = "GOVERNMENT_OFFICE_TYPE_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME        = "GOVERNMENT_OFFICE_TYPE_ID";
	public static final String NAME_COLUMN_NAME      = "NAME";
	public static final String PARENT_ID_COLUMN_NAME = "PARENT_ID";

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
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = GovernmentOfficeType.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger                ID;
	@Column(
			name = GovernmentOfficeType.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                    name;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = GovernmentOfficeType.PARENT_ID_COLUMN_NAME,
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeType      parent;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = GovernmentOfficeType.PARENT_FIELD
	)
	private Set<GovernmentOfficeType> children;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = GovernmentOffice.OFFICE_TYPE_FIELD
	)
	private Set<GovernmentOffice>     officesThisType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = DocumentTemplate.GOVERNMENT_OFFICE_TYPE_FIELD
	)
	private Set<DocumentTemplate>     documentTemplates;

	public GovernmentOfficeType() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public GovernmentOfficeType( String name, GovernmentOfficeType parent ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setName( name )
				.setParent( parent )
				.setChildren( new HashSet<GovernmentOfficeType>() )
				.setOfficesThisType( new HashSet<GovernmentOffice>() )
				.setDocumentTemplates( new HashSet<DocumentTemplate>() );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public GovernmentOfficeType setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	public GovernmentOfficeType getParent() {
		return parent;
	}

	public GovernmentOfficeType setParent( GovernmentOfficeType parent ) {
		LOG.debug( "Parent is " + parent );
		this.parent = parent;
		return this;
	}

	public Set<GovernmentOfficeType> getChildren() {
		return children;
	}

	public GovernmentOfficeType setChildren( Set<GovernmentOfficeType> children ) {
		this.children = children;
		return this;
	}

	public GovernmentOfficeType addChild( GovernmentOfficeType child ) {
		children.add( child );
		return this;
	}

	public GovernmentOfficeType addChildren( Collection<GovernmentOfficeType> children ) {
		this.children.addAll( children );
		return this;
	}

	public Set<GovernmentOffice> getOfficesThisType() {
		return officesThisType;
	}

	public GovernmentOfficeType setOfficesThisType( Set<GovernmentOffice> officesThisType ) {
		this.officesThisType = officesThisType;
		return this;
	}

	public GovernmentOfficeType addOfficeThisType( GovernmentOffice officeThisType ) {
		officesThisType.add( officeThisType );
		return this;
	}

	public GovernmentOfficeType addOfficesThisType( Collection<GovernmentOffice> officesThisType ) {
		this.officesThisType.addAll( officesThisType );
		return this;
	}

	public Set<DocumentTemplate> getDocumentTemplates() {
		return documentTemplates;
	}

	public GovernmentOfficeType setDocumentTemplates( Set<DocumentTemplate> documentTemplates ) {
		this.documentTemplates = documentTemplates;
		return this;
	}

	public GovernmentOfficeType addDocumentTemplate( DocumentTemplate documentTemplate ) {
		documentTemplates.add( documentTemplate );
		return this;
	}

	public GovernmentOfficeType addDocumentTemplates( Collection<DocumentTemplate> documentTemplates ) {
		this.documentTemplates.addAll( documentTemplates );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			GovernmentOfficeType governmentOfficeType = (GovernmentOfficeType) object;
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
