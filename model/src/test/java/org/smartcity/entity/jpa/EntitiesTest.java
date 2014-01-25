package org.smartcity.entity.jpa;
  /*
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.testng.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.smartcity.UtilTestClass;
import org.smartcity.entity.Address;
import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.Document;
import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.Email;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;
import org.smartcity.entity.TelephoneNumber;
import org.smartcity.entity.User;

//import org.junit.Test;
//import org.junit.runner.RunWith;

import org.testng.annotations.Test;
                           */
//@RunWith(
//		value = Arquillian.class
//)
public class EntitiesTest/*
		extends Arquillian */{
	                         /*
	private Address              address;
	private Bank                 bank;
	private BankAccount          bankAccount;
	private BankBranch           bankBranch;
	private Document             document;
	private DocumentTemplate     documentTemplate;
	private Email                email;
	private GovernmentOffice     governmentOffice;
	private GovernmentOfficeType governmentOfficeType;
	private TelephoneNumber      telephoneNumber;
	private User                 user;
	@PersistenceContext
	private EntityManager        em;
	@Inject
	private UserTransaction      utx;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive<?> createDeploymentArchive() {
		return UtilTestClass.INSTANCE
				.createDeploymentArchive();
	}

	@Test(
			groups = "entities",
			timeOut = 10000
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBank()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		bank = new BankEntity( "Bank", "Website" );
		em.persist( bank );
		utx.commit();
	}

	@Test(
			groups = "entities",
			timeOut = 10000,
			dependsOnMethods = "testCreateBank"
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankBranch()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		telephoneNumber = new TelephoneNumberEmbedded( (short) 10, (short) 10, (short) 50, null );
		address = new AddressEmbedded( "State",
									   "City",
									   "Street",
									   (short) 10,
									   (TelephoneNumberEmbedded) telephoneNumber );
		bankBranch = new BankBranchEntity( "Bank Branch", (AddressEmbedded) address, (BankEntity) bank );
		em.persist( bankBranch );
		utx.commit();
	}
*/
}
