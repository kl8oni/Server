package org.smartcity.dao;

import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.User;

import java.math.BigInteger;

public interface BankDAO {

	Bank createBank( String name, String website );

	void removeBank( Bank bank );

	Bank findBank( BigInteger bankID );

	BankAccount createBankAccount(
			Long bankAccountNumber,
			User owner,
			String bankName,
			String bankWebsite,
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer number,
			String pattern );

	BankAccount createBankAccount(
			Long bankAccountNumber,
			User owner,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer telephoneNumber,
			String telephoneNumberPattern,
			Bank bank );

	BankAccount createBankAccount(
			Long bankAccountNumber,
			User owner,
			BankBranch bankBranch,
			Bank bank );

	void removeBankAccount( BankAccount bankAccount );

	BankAccount findBankAccount( BigInteger bankAccountID );

	BankBranch createBankBranch(
			String bankName,
			String bankWebsite,
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer telephoneNumber,
			String telephoneNumberPattern );

	BankBranch createBankBranch(
			Bank bank,
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer telephoneNumber,
			String telephoneNumberPattern );

	void removeBankBranch( BankBranch bankBranch );

	BankBranch findBankBranch( BigInteger bankBranchID );

}