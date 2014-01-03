package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public interface GovernmentOfficeType <OT extends GovernmentOfficeType,
									   DT extends DocumentTemplate,
									   O extends GovernmentOffice>
		extends		Serializable {

	String TABLE_NAME            = "GOVERNMENT_OFFICE_TYPES";
	String GENERATOR_NAME        = "GOVERNMENT_OFFICE_TYPE_ID_GENERATOR";
	String SEQUENCE_NAME         = "GOVERNMENT_OFFICE_TYPE_ID_SEQUENCE";
	String ID_COLUMN_NAME        = "GOVERNMENT_OFFICE_TYPE_ID";
	String NAME_COLUMN_NAME      = "NAME";
	String PARENT_ID_COLUMN_NAME = "PARENT_ID";

	public BigInteger getID();

	public String getName();

	public OT setName( String name );

	public OT getParent();

	public OT setParent( OT parent );

	public Set<OT> getChildren();

	public OT setChildren( Set<OT> children );

	public OT addChild( OT child );

	public OT addChildren( Collection<OT> children );

	public Set<O> getOfficesThisType();

	public OT setOfficesThisType( Set<O> officesThisType );

	public OT addOfficeThisType( O officeThisType );

	public OT addOfficesThisType( Collection<O> officesThisType );

	public Set<DT> getDocumentTemplates();

	public OT setDocumentTemplates( Set<DT> documentTemplates );

	public OT addDocumentTemplate( DT documentTemplate );

	public OT addDocumentTemplates( Collection<DT> documentTemplates );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}