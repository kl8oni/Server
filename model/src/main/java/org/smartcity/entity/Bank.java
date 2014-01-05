package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Set;

public interface Bank<B extends Bank,
					  BB extends BankBranch,
					  BA extends BankAccount>
		extends		Serializable {

	String TABLE_NAME          = "BANKS";
	String GENERATOR_NAME      = "BANK_ID_GENERATOR";
	String SEQUENCE_NAME       = "BANK_ID_SEQUENCE";
	String ID_COLUMN_NAME      = "BANK_ID";
	String NAME_COLUMN_NAME    = "NAME";
	String WEBSITE_COLUMN_NAME = "WEBSITE";

	public BigInteger getID();

	public String getName();

	public B setName( String name );

	public String getWebsite();

	public B setWebsite( String website );

	public Set<BB> getBankBranches();

	public B setBankBranches( Set<BB> bankBranches );

	public B addBankBranch( BB bankBranch );

	public B addBankBranches( Collection<BB> bankBranches );

	public Set<BA> getBankAccounts();

	public Set<BA> getBankAccountsByBankBranch( BB bankBranch );

	public B setBankAccounts( Set<BA> bankAccounts );

	public B addBankBranchBankAccounts( BB bankBranch );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
