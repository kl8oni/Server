package org.smartcity.dao;

import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.User;

public interface BankDAO {

	Bank createBank( String name, String website );

	void removeBank( Bank bank );

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
			Integer number,
			String pattern,
			Bank bank );

	BankAccount createBankAccount(
			Long bankAccountNumber,
			User owner,
			String bankName,
			BankBranch bankBranch,
			Bank bank );

	void removeBankAccount( BankAccount bankAccount );

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
			Integer number,
			String pattern );

	BankBranch createBankBranch(
			Bank bank,
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer number,
			String pattern );

	void removeBankBranch( BankBranch bankBranch );

}