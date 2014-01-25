package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Set;

public interface BankBranch<
		BB extends BankBranch,
		B extends Bank,
		A extends Address,
		BA extends BankAccount>
		extends Serializable {

	String TABLE_NAME          = "BANK_BRANCHES";
	String GENERATOR_NAME      = "BANK_BRANCH_ID_GENERATOR";
	String SEQUENCE_NAME       = "BANK_BRANCH_ID_SEQUENCE";
	String ID_COLUMN_NAME      = "BANK_BRANCH_ID";
	String NAME_COLUMN_NAME    = "NAME";
	String BANK_ID_COLUMN_NAME = "BANK_ID";

	BigInteger getID();

	String getName();

	BB setName( String name );

	A getAddress();

	BB setAddress( A address );

	B getBank();

	BB setBank( B bank );

	Set<BA> getBankAccounts();

	BB setBankAccounts( Set<BA> bankAccounts );

	BB addBankAccount( BA bankAccount );

	BB addBankAccounts( Collection<BA> bankAccounts );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
