package org.smartcity.dao;


import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.User;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class BankDAOImpl
		extends AbstractEmbeddableEntityDAO
		implements BankDAO,
				   EmbeddableEntityDAO {

	@Override
	public Bank createBank( String name, String website ) {
		EntityManager em = getEntityDAO().getEntitiesManager();
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
			Long bankAccountNumber, User owner, BankBranch bankBranch, Bank bank ) {
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

	@Override
	public Bank findBank( BigInteger bankID ) {
		return null;
	}

	@Override
	public BankAccount findBankAccount( BigInteger bankAccountID ) {
		return null;
	}

	@Override
	public BankBranch findBankBranch( BigInteger bankBranchID ) {
		return null;
	}

}
