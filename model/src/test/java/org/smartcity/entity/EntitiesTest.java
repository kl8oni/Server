package org.smartcity.entity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.junit.Assert;
import org.smartcity.UtilTestClass;
import org.smartcity.entity.Address;
import org.smartcity.entity.Bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.TelephoneNumber;
import org.smartcity.entity.User;

import java.math.BigInteger;

@RunWith(
		value = Arquillian.class
)
public class EntitiesTest {

	@PersistenceContext(
			type = javax.persistence.PersistenceContextType.EXTENDED
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
	public void testCreateBank()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		Bank bank = new Bank( "bank-name", "bank-web-site" );
		TelephoneNumber bankBranchTelephone = new TelephoneNumber( (short) 38, (short) 542, 964658, null );
		Address bankBranchAddress = new Address( "Ukraine", "Sumy", "Soborna", (short) 10, bankBranchTelephone );
		BankBranch bankBranch = new BankBranch( "Sobornyi", bankBranchAddress, bank );
		User owner = new User( "last-name", "first-name", "middle-name", "nick-name", "password" );
		BankAccount bankAccount = new BankAccount( 100L, owner, bank, bankBranch );
		em.persist( bank );
		em.persist( bankBranch );
		em.persist( bankAccount );
		em.persist( owner );
		utx.commit();
		Assert.assertNotNull( owner.getID() );

		utx.begin();
		em.joinTransaction();
		User user = em.find( User.class, owner.getID() );
		utx.commit();
		Assert.assertNotNull( user );
	}

}
