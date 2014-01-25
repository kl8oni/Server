package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public interface GovernmentOfficeType<
		OT extends GovernmentOfficeType,
		DT extends DocumentTemplate,
		O extends GovernmentOffice>
		extends Serializable {

	String TABLE_NAME            = "GOVERNMENT_OFFICE_TYPES";
	String GENERATOR_NAME        = "GOVERNMENT_OFFICE_TYPE_ID_GENERATOR";
	String SEQUENCE_NAME         = "GOVERNMENT_OFFICE_TYPE_ID_SEQUENCE";
	String ID_COLUMN_NAME        = "GOVERNMENT_OFFICE_TYPE_ID";
	String NAME_COLUMN_NAME      = "NAME";
	String PARENT_ID_COLUMN_NAME = "PARENT_ID";

	BigInteger getID();

	String getName();

	OT setName( String name );

	OT getParent();

	OT setParent( OT parent );

	Set<OT> getChildren();

	OT setChildren( Set<OT> children );

	OT addChild( OT child );

	OT addChildren( Collection<OT> children );

	Set<O> getOfficesThisType();

	OT setOfficesThisType( Set<O> officesThisType );

	OT addOfficeThisType( O officeThisType );

	OT addOfficesThisType( Collection<O> officesThisType );

	Set<DT> getDocumentTemplates();

	OT setDocumentTemplates( Set<DT> documentTemplates );

	OT addDocumentTemplate( DT documentTemplate );

	OT addDocumentTemplates( Collection<DT> documentTemplates );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
