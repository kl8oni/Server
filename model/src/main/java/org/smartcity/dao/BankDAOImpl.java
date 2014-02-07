package org.smartcity.dao;


import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.User;

public class BankDAOImpl
		extends AbstractEmbeddableEntityDAO
		implements BankDAO,
				   EmbeddableEntityDAO {

	@Override
	public Bank createBank( String name, String website ) {
		return null;
	}

	@Override
	public void removeBank( Bank bank ) {

	}

	@Override
	public BankAccount createBankAccount(
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
			String pattern ) {
		return null;
	}

	@Override
	public BankAccount createBankAccount(
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
			Bank bank ) {
		return null;
	}

	@Override
	public BankAccount createBankAccount(
			Long bankAccountNumber, User owner, String bankName, BankBranch bankBranch, Bank bank ) {
		return null;
	}

	@Override
	public void removeBankAccount( BankAccount bankAccount ) {

	}

	@Override
	public BankBranch createBankBranch(
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
			String pattern ) {
		return null;
	}

	@Override
	public BankBranch createBankBranch(
			Bank bank,
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer number,
			String pattern ) {
		return null;
	}

	@Override
	public void removeBankBranch( BankBranch bankBranch ) {

	}

}
