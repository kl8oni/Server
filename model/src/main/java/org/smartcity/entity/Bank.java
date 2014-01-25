package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Set;

public interface Bank<
		B extends Bank,
		BB extends BankBranch,
		BA extends BankAccount>
		extends Serializable {

	String TABLE_NAME          = "BANKS";
	String GENERATOR_NAME      = "BANK_ID_GENERATOR";
	String SEQUENCE_NAME       = "BANK_ID_SEQUENCE";
	String ID_COLUMN_NAME      = "BANK_ID";
	String NAME_COLUMN_NAME    = "NAME";
	String WEBSITE_COLUMN_NAME = "WEBSITE";

	BigInteger getID();

	String getName();

	B setName( String name );

	String getWebsite();

	B setWebsite( String website );

	Set<BB> getBankBranches();

	B setBankBranches( Set<BB> bankBranches );

	B addBankBranch( BB bankBranch );

	B addBankBranches( Collection<BB> bankBranches );

	Set<BA> getBankAccounts();

	Set<BA> getBankAccountsByBankBranch( BB bankBranch );

	B setBankAccounts( Set<BA> bankAccounts );

	B addBankBranchBankAccounts( BB bankBranch );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
