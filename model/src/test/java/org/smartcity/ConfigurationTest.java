package org.smartcity;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

public class ConfigurationTest {

	private DocumentBuilder documentBuilder;

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
			timeOut = UtilTestClass.DEFAULT_TEST_TIME
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testJpaConfiguration()
			throws IOException, SAXException {
		Document persistenceXML = documentBuilder.parse( "./src/main/resources/persistence.xml" );
		Document testPersistenceXML = documentBuilder.parse( "./src/test/resources/test-persistence.xml" );
		Diff diff = new Diff( testPersistenceXML, persistenceXML );
		Assert.assertTrue( diff.similar() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIME
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testHibernateConfiguration()
			throws IOException, SAXException {
		Document hibernateXML = documentBuilder.parse( "./src/main/resources/hibernate.cfg.xml" );
		Document testHibernateXML = documentBuilder.parse( "./src/test/resources/test-hibernate.cfg.xml" );
		Diff diff = new Diff( testHibernateXML, hibernateXML );
		Assert.assertTrue( diff.similar() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIME
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testProxoolConfiguration()
			throws IOException {
		Properties proxool = new Properties();
		proxool.load( new FileReader( "./src/main/resources/proxool.properties" ) );
		Properties testProxool = new Properties();
		testProxool.load( new FileReader( "./src/test/resources/test-proxool.properties" ) );
		Assert.assertTrue( testProxool.equals( proxool ) );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIME
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testJbossDeploymentStructureConfiguration()
			throws IOException, SAXException {
		Document jbossDeploymentStructure = documentBuilder.parse( "./src/main/resources/jboss-deployment-structure.xml" );
		Document testJbossDeploymentStructure = documentBuilder.parse( "./src/test/resources/test-jboss-deployment-structure.xml" );
		Diff diff = new Diff( testJbossDeploymentStructure, jbossDeploymentStructure );
		Assert.assertTrue( diff.similar() );
	}

	@Test(
			groups = "configuration",
			suiteName = "production",
			testName = "functional",
			timeOut = UtilTestClass.DEFAULT_TEST_TIME
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testEhcacheConfiguration()
			throws IOException, SAXException {
		Document ehcache = documentBuilder.parse( "./src/main/resources/ehcache.xml" );
		Document testEhcache = documentBuilder.parse( "./src/test/resources/test-ehcache.xml" );
		Diff diff = new Diff( testEhcache, ehcache );
		Assert.assertTrue( diff.similar() );
	}

}
