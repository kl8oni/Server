package org.smartcity.entity.jpa;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

@RunWith(
		value = Arquillian.class
)
public class HibernateConfigurationTest {

	@Deployment
	public static JavaArchive createArchive() {
		JavaArchive jar = ShrinkWrap.create( JavaArchive.class );
		for( Map.Entry<String, String> property : TestPropertiesContainer.INSTANCE ) {
			jar.addAsManifestResource( property.getKey(), property.getValue() );
		}
		jar.addPackage( Package.getPackage( TestPropertiesContainer.JPA_PACKAGE_NAME ) );
		jar.addPackage( Package.getPackage( TestPropertiesContainer.ENTITY_PACKAGE_NAME ) );
		jar.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
		return jar;
	}

	@Test
	public void testHibernateSessionFactory() {
		Configuration configuration = new Configuration().configure( "/META-INF/hibernate.cfg.xml" );
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings( configuration.getProperties() ).buildServiceRegistry();
		SessionFactory factory = configuration.buildSessionFactory( serviceRegistry );
		Assert.assertNotNull( factory );

	}

}
