package org.smartcity;
              /*
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.testng.Arquillian;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.jboss.shrinkwrap.api.Archive;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;
                             */
public class ConfigurationTest/*
		extends		Arquillian*/ {/*

	private DocumentBuilder documentBuilder;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive<?> createDeploymentArchive() {
		return UtilTestClass.INSTANCE.createDeploymentArchive();
	}

	@BeforeTest(
			groups = "configuration"
	)
	public void configurationTest()
			throws ParserConfigurationException {
		XMLUnit.setControlParser( "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl" );
		XMLUnit.setTestParser( "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl" );
		XMLUnit.setSAXParserFactory( "org.apache.xerces.jaxp.SAXParserFactoryImpl" );
		XMLUnit.setTransformerFactory( "org.apache.xalan.processor.TransformerFactoryImpl" );
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIMEOUT
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testJpaConfiguration()
			throws IOException, SAXException {
		Document persistenceXML = documentBuilder.parse( "/deploy-persistence.xml" );
		Document testPersistenceXML = documentBuilder.parse( "/deploy-test-persistence.xml" );
		Diff diff = new Diff( testPersistenceXML, persistenceXML );
		Assert.assertTrue( diff.similar() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIMEOUT
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testHibernateConfiguration()
			throws IOException, SAXException {
		Document hibernateXML = documentBuilder.parse( "/deploy-hibernate.cfg.xml" );
		Document testHibernateXML = documentBuilder.parse( "/deploy-test-hibernate.cfg.xml" );
		Diff diff = new Diff( testHibernateXML, hibernateXML );
		Assert.assertTrue( diff.similar() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIMEOUT
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testProxoolConfiguration()
			throws IOException {
		Properties proxool = new Properties();
		proxool.load( new FileReader( "/deploy-proxool.properties" ) );
		Properties testProxool = new Properties();
		testProxool.load( new FileReader( "/deploy-test-proxool.properties" ) );
		Assert.assertTrue( testProxool.equals( proxool ) );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIMEOUT
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testJbossDeploymentStructureConfiguration()
			throws IOException, SAXException {
		Document jbossDeploymentStructure = documentBuilder.parse( "/deploy-jboss-deployment-structure.xml" );
		Document testJbossDeploymentStructure = documentBuilder.parse( "/deploy-test-jboss-deployment-structure.xml" );
		Diff diff = new Diff( testJbossDeploymentStructure, jbossDeploymentStructure );
		Assert.assertTrue( diff.similar() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIMEOUT
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testEhcacheConfiguration()
			throws IOException, SAXException {
		Document ehcache = documentBuilder.parse( "/deploy-ehcache.xml" );
		Document testEhcache = documentBuilder.parse( "/deploy-test-ehcache.xml" );
		Diff diff = new Diff( testEhcache, ehcache );
		Assert.assertTrue( diff.similar() );
	}
	     */
}
