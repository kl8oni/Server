package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Set;

public interface BankBranch<BB extends BankBranch,
							B extends Bank,
							A extends Address,
							BA extends BankAccount>
		extends		Serializable {

	String TABLE_NAME                   = "BANK_BRANCHES";
	String GENERATOR_NAME               = "BANK_BRANCH_ID_GENERATOR";
	String SEQUENCE_NAME                = "BANK_BRANCH_ID_SEQUENCE";
	String ID_COLUMN_NAME               = "BANK_BRANCH_ID";
	String NAME_COLUMN_NAME             = "NAME";
	String BANK_ID_COLUMN_NAME          = "BANK_ID";

	public BigInteger getID();

	public String getName();

	public BB setName( String name );

	public A getAddress();

	public BB setAddress( A address );

	public B getBank();

	public BB setBank( B bank );

	public Set<BA> getBankAccounts();

	public BB setBankAccounts( Set<BA> bankAccounts );

	public BB addBankAccount( BA bankAccount );

	public BB addBankAccounts( Collection<BA> bankAccounts );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
