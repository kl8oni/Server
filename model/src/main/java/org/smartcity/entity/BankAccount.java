package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;

public interface BankAccount<
		BA extends BankAccount,
		U extends User,
		B extends Bank,
		BB extends BankBranch>
		extends Serializable {

	String TABLE_NAME                 = "BANK_ACCOUNTS";
	String GENERATOR_NAME             = "BANK_ACCOUNT_ID_GENERATOR";
	String SEQUENCE_NAME              = "BANK_ACCOUNT_ID_SEQUENCE";
	String ID_COLUMN_NAME             = "BANK_ACCOUNT_ID";
	String ACCOUNT_NUMBER_COLUMN_NAME = "ACCOUNT_NUMBER";
	String USER_ID_COLUMN_NAME        = "USER_ID";
	String BANK_ID_COLUMN_NAME        = "BANK_ID";
	String BANK_BRANCH_ID_COLUMN_NAME = "BANK_BRANCH_ID";

	BigInteger getID();

	Long getBankAccountNumber();

	BA setBankAccountNumber( Long bankAccountNumber );

	U getBankAccountOwner();

	BA setBankAccountOwner( U owner );

	B getBank();

	BA setBank( B bank );

	BB getBankBranch();

	BA setBankBranch( BB bankBranch );

	boolean equals( Object object );

	int hashCode();

	String toString();

}
