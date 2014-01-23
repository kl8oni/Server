package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

public interface BankAccount<BA extends BankAccount,
							 U extends User,
							 B extends Bank,
							 BB extends BankBranch>
		extends		Serializable {

	String TABLE_NAME                 = "BANK_ACCOUNTS";
	String GENERATOR_NAME             = "BANK_ACCOUNT_ID_GENERATOR";
	String SEQUENCE_NAME              = "BANK_ACCOUNT_ID_SEQUENCE";
	String ID_COLUMN_NAME             = "BANK_ACCOUNT_ID";
	String ACCOUNT_NUMBER_COLUMN_NAME = "ACCOUNT_NUMBER";
	String USER_ID_COLUMN_NAME        = "USER_ID";
	String BANK_ID_COLUMN_NAME        = "BANK_ID";
	String BANK_BRANCH_ID_COLUMN_NAME = "BANK_BRANCH_ID";

	public BigInteger getID();

	public Long getBankAccountNumber();

	public BA setBankAccountNumber( Long bankAccountNumber );

	public U getBankAccountOwner();

	public BA setBankAccountOwner( U owner );

	public B getBank();

	public BA setBank( B bank );

	public BB getBankBranch();

	public BA setBankBranch( BB bankBranch );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}
