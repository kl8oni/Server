package org.smartcity.entity;
            /*
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.smartcity.UtilTestClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

@RunWith(
		value = Arquillian.class
)               */
public class EntitiesRelationTest {
	       /*
	@PersistenceContext(
			type = PersistenceContextType.EXTENDED
	)
	private EntityManager   em;
	@Inject
	private UserTransaction utx;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive<?> createDeploymentArchive() {
		return UtilTestClass.INSTANCE
				.createDeploymentArchive();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testBankAccountBankRelation()
			throws Exception {
		Bank bank = new Bank( "bank-name", "bank-web-site" );
		TelephoneNumber bankBranchTelephone = new TelephoneNumber( (short) 38, (short) 542, 964658, null );
		Address bankBranchAddress = new Address( "Ukraine", "Sumy", "Soborna", (short) 10, bankBranchTelephone );
		BankBranch bankBranch = new BankBranch( "Sobornyi", bankBranchAddress, bank );
		User owner = new User( "last-name", "first-name", "middle-name", "nick-name", "password" );
		BankAccount bankAccount = new BankAccount( 100L, owner, bank, bankBranch );
		try {
			utx.begin();
			em.joinTransaction();
			em.persist( bank );
			em.persist( bankBranch );
			em.persist( bankAccount );
			em.persist( owner );
		}
		finally {
			utx.commit();
		}

		try {
			utx.begin();
			em.joinTransaction();
			Bank foundBank = em.find( Bank.class, bank.getID() );
			Assert.assertEquals( 1, foundBank.getBankAccounts().size() );
		}
		catch( Exception e ) {
			throw e;
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testBankBranchBankRelation()
			throws Exception {
		Bank bank = new Bank( "bank-name", "bank-web-site" );
		TelephoneNumber bankBranchTelephone = new TelephoneNumber( (short) 38, (short) 542, 964658, null );
		Address bankBranchAddress = new Address( "Ukraine", "Sumy", "Soborna", (short) 10, bankBranchTelephone );
		BankBranch bankBranch = new BankBranch( "Sobornyi", bankBranchAddress, bank );
		User owner = new User( "last-name", "first-name", "middle-name", "nick-name", "password" );
		BankAccount bankAccount = new BankAccount( 100L, owner, bank, bankBranch );
		try {
			utx.begin();
			em.joinTransaction();
			em.persist( bank );
			em.persist( bankBranch );
			em.persist( bankAccount );
			em.persist( owner );
		}
		finally {
			utx.commit();
		}

		try {
			utx.begin();
			em.joinTransaction();
			Bank foundBank = em.find( Bank.class, bank.getID() );
			Assert.assertEquals( 1, foundBank.getBankBranches().size() );
		}
		catch( Exception e ) {
			throw e;
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testBankUserRelation()
			throws Exception {
		Bank bank = new Bank( "bank-name", "bank-web-site" );
		TelephoneNumber bankBranchTelephone = new TelephoneNumber( (short) 38, (short) 542, 964658, null );
		Address bankBranchAddress = new Address( "Ukraine", "Sumy", "Soborna", (short) 10, bankBranchTelephone );
		BankBranch bankBranch = new BankBranch( "Sobornyi", bankBranchAddress, bank );
		User owner = new User( "last-name", "first-name", "middle-name", "nick-name", "password" );
		BankAccount bankAccount = new BankAccount( 100L, owner, bank, bankBranch );
		BankAccount newBankAccount = new BankAccount( 200L, owner, bank, bankBranch );
		User otherOwner = new User(
				"other-last-name",
				"other-first-name",
				"other-middle-name",
				"other-nick-name",
				"other-password" );
		BankAccount otherBankAccount = new BankAccount( 300L, otherOwner, bank, bankBranch );
		try {
			utx.begin();
			em.joinTransaction();
			em.persist( bank );
			em.persist( bankBranch );
			em.persist( bankAccount );
			em.persist( newBankAccount );
			em.persist( otherBankAccount );
			em.persist( owner );
			em.persist( otherOwner );
		}
		finally {
			utx.commit();
		}

		try {
			utx.begin();
			em.joinTransaction();
			Bank foundBank = em.find( Bank.class, bank.getID() );
			Set<User> bankUser = new HashSet<>();
			Set<BankAccount> bankAccountSet = foundBank.getBankAccounts();
			for( BankAccount ba : bankAccountSet ) {
				bankUser.add( ba.getBankAccountOwner() );
			}
			Assert.assertEquals( 2, bankUser.size() );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testUserBankAccountRelation()
			throws Exception {
		Bank bank = new Bank( "bank-name", "bank-web-site" );
		TelephoneNumber bankBranchTelephone = new TelephoneNumber( (short) 38, (short) 542, 964658, null );
		Address bankBranchAddress = new Address( "Ukraine", "Sumy", "Soborna", (short) 10, bankBranchTelephone );
		BankBranch bankBranch = new BankBranch( "Sobornyi", bankBranchAddress, bank );
		User owner = new User( "last-name", "first-name", "middle-name", "nick-name", "password" );
		BankAccount bankAccount = new BankAccount( 100L, owner, bank, bankBranch );
		BankAccount newBankAccount = new BankAccount( 200L, owner, bank, bankBranch );
		try {
			utx.begin();
			em.joinTransaction();
			em.persist( owner );
			em.persist( bank );
			em.persist( bankAccount );
			em.persist( newBankAccount );
		}
		finally {
			utx.commit();
		}

		try {
			utx.begin();
			User foundUser = em.find( User.class, owner.getID() );
			Bank foundBank = em.find( Bank.class, bank.getID() );
			Set<BankAccount> bankAccounts = new HashSet<>();
			for( BankAccount ba : foundBank.getBankAccounts() ) {
				if( foundUser.equals( ba.getBankAccountOwner() ) ) {
					bankAccounts.add( ba );
				}
			}
			Assert.assertEquals( 2, bankAccounts.size() );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testOfficeTypeDocumentTypeRelation()
			throws Exception {
		GovernmentOfficeType parentOfficeType = new GovernmentOfficeType( "parent", null );
		GovernmentOfficeType childOfficeType = new GovernmentOfficeType( "child", parentOfficeType );
		DocumentTemplate documentTemplate = new DocumentTemplate(
				"passport-template",
				"%02s",
				"%06d",
				childOfficeType );
		try {
			utx.begin();
			em.joinTransaction();
			em.persist( parentOfficeType );
			em.persist( childOfficeType );
			em.persist( documentTemplate );
		}
		finally {
			utx.commit();
		}

		try {
			utx.begin();
			GovernmentOfficeType foundOfficeType = em.find( GovernmentOfficeType.class, parentOfficeType.getID() );
			Assert.assertEquals( 1, foundOfficeType.getDocumentTemplates().size() );
		}
		catch( Exception e ) {
			throw e;
		}
		finally {
			utx.commit();
		}
	}
	    */
}
