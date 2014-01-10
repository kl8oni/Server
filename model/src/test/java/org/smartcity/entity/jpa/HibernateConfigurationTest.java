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

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.smartcity.TestPropertiesContainer;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

@RunWith(
		value = Arquillian.class
)
public class HibernateConfigurationTest {

	private Configuration   configuration;
	private ServiceRegistry serviceRegistry;

	@Deployment
	public static JavaArchive createArchive() {
		JavaArchive jar = ShrinkWrap.create( JavaArchive.class );
		Iterator<Map.Entry<String, String>> iteratorProperties = TestPropertiesContainer.INSTANCE.iteratorProperties();
		while( iteratorProperties.hasNext() ) {
			Map.Entry<String, String> property = iteratorProperties.next();
			jar.addAsManifestResource( property.getKey(), property.getValue() );
		}
		Iterator<Package> iteratorPackages = TestPropertiesContainer.INSTANCE.iteratorPackages();
		while( iteratorPackages.hasNext() ) {
			Package packageEntity = iteratorPackages.next();
			jar.addPackage( packageEntity );
		}
		jar.addPackage( ProxoolConnectionProvider.class.getPackage() );
		jar.addPackage( EhCacheRegionFactory.class.getPackage() );
		jar.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
		jar.addAsManifestResource( "test-hibernate.cfg.xml" );
		File hib = new File( "./src/main/resources/META-INF/hibernate.cfg.xml" );
		jar.addAsManifestResource( hib, "deployed-hibernate.cfg.xml" );
		return jar;
	}

	@Before
	public void beforeTest() {
		configuration = new Configuration().configure( "/META-INF/hibernate.cfg.xml" );
		serviceRegistry = new ServiceRegistryBuilder()
				.applySettings( configuration.getProperties() ).buildServiceRegistry();
	}

	@Test
	public void testCreateHibernateSessionFactory() {
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Assert.assertNotNull( factory );
	}

	@Test
	public void testHibernateConnectionProvider() {
		ConnectionProviderInitiator initiator = ConnectionProviderInitiator.INSTANCE;
		ConnectionProvider provider = initiator.initiateService( configuration.getProperties(), (ServiceRegistryImplementor) serviceRegistry );
		Assert.assertEquals( ProxoolConnectionProvider.class, provider.getClass() );
	}

	@Test
	public void testHibernateSecondLevelCacheProvider() {
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Settings settings = ( (SessionFactoryImpl) factory ).getSettings();
		RegionFactory regionFactory = settings.getRegionFactory();
		Assert.assertEquals( EhCacheRegionFactory.class, regionFactory.getClass() );
	}

	@Test
	public void testHibernateDialect() {
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Dialect dialect = ( (SessionFactoryImplementor) factory ).getDialect();
		Assert.assertEquals( PostgresPlusDialect.class, dialect.getClass() );
	}

	@Test
	public void testConfigFiles() {
		Configuration testConfiguration = new Configuration().configure( "/META-INF/test-hibernate.cfg.xml" );
		Configuration deployedConfiguration = new Configuration().configure( "/META-INF/deployed-hibernate.cfg.xml" );
		Properties testProperties = testConfiguration.getProperties();
		Properties deployedProperties = deployedConfiguration.getProperties();
		Assert.assertTrue( testProperties.equals( deployedProperties ) );
	}

}
