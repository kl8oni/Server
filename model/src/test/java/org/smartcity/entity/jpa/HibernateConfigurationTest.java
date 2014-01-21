package org.smartcity.entity.jpa;

import org.hibernate.SessionFactory;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgresPlusDialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.internal.ConnectionProviderInitiator;
import org.hibernate.service.jdbc.connections.internal.ProxoolConnectionProvider;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;

import org.smartcity.BuildDeployment;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HibernateConfigurationTest
		extends		BuildDeployment {

	private Configuration   configuration;
	private ServiceRegistry serviceRegistry;

	@BeforeClass(
			groups = "configuration"
	)
	public void beforeTest() {
		configuration = new Configuration().configure( "/hibernate.cfg.xml" );
		serviceRegistry = new ServiceRegistryBuilder()
				.applySettings( configuration.getProperties() )
				.buildServiceRegistry();
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = 1000
	)
	@OperateOnDeployment(
			value = BuildDeployment.PRODUCTION_DEPLOYMENT
	)
	public void testCreateHibernateSessionFactory() {
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Assert.assertNotNull( factory );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = 1000
	)
	@OperateOnDeployment(
			value = BuildDeployment.PRODUCTION_DEPLOYMENT
	)
	public void testHibernateConnectionProvider() {
		ConnectionProviderInitiator initiator = ConnectionProviderInitiator.INSTANCE;
		ConnectionProvider provider = initiator.initiateService(
				configuration.getProperties(),
				(ServiceRegistryImplementor) serviceRegistry );
		Assert.assertEquals( ProxoolConnectionProvider.class, provider.getClass() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = 1000
	)
	@OperateOnDeployment(
			value = BuildDeployment.PRODUCTION_DEPLOYMENT
	)
	public void testHibernateSecondLevelCacheProvider() {
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Settings settings = ( (SessionFactoryImpl) factory ).getSettings();
		RegionFactory regionFactory = settings.getRegionFactory();
		Assert.assertEquals( EhCacheRegionFactory.class, regionFactory.getClass() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = 1000
	)
	@OperateOnDeployment(
			value = BuildDeployment.PRODUCTION_DEPLOYMENT
	)
	public void testHibernateDialect() {
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Dialect dialect = ( (SessionFactoryImplementor) factory ).getDialect();
		Assert.assertEquals( PostgresPlusDialect.class, dialect.getClass() );
	}

}
