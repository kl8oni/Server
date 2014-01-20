package org.smartcity;

import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.smartcity.entity.Address;
import org.smartcity.entity.jpa.AddressEmbedded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class TestPropertiesContainer {

	public static final TestPropertiesContainer INSTANCE = new TestPropertiesContainer();

	private Map<String, Map.Entry<String, String>> testProperties;
	private List<Package> projectPackages;

	private static final String TEST_HIBERNATE_CFG_XML_FILE_NAME          = "test-hibernate.cfg.xml";
	private static final String TEST_PERSISTENCE_XML_FILE_NAME            = "test-persistence.xml";
	private static final String TEST_PROXOOL_PROPERTIES_FILE_NAME         = "test-proxool.properties";
	private static final String TEST_JBOSS_DEPLOYMENT_STRUCTURE_FILE_NAME = "test-jboss-deployment-structure.xml";

	private static final String DEPLOYED_HIBERNATE_CFG_XML_FILE_NAME          = "hibernate.cfg.xml";
	private static final String DEPLOYED_PERSISTENCE_XML_FILE_NAME            = "persistence.xml";
	private static final String DEPLOYED_PROXOOL_PROPERTIES_FILE_NAME         = "proxool.properties";
	private static final String DEPLOYED_JBOSS_DEPLOYMENT_STRUCTURE_FILE_NAME = "jboss-deployment-structure.xml";

	public static final String HIBERNATE_CONFIG_FILE                  = "hibernate.config.file";
	public static final String PERSISTENCE_CONFIG_FILE                = "persistence.config.file";
	public static final String PROXOOL_CONFIG_FILE                    = "proxool.config.file";
	public static final String JBOSS_DEPLOYMENT_STRUCTURE_CONFIG_FILE = "jboss.deployment.config.file";

	private TestPropertiesContainer() {
		testProperties = new HashMap<>();
		testProperties.put( HIBERNATE_CONFIG_FILE, new DefaultMapEntry<>( TEST_HIBERNATE_CFG_XML_FILE_NAME, DEPLOYED_HIBERNATE_CFG_XML_FILE_NAME ) );
		testProperties.put( PERSISTENCE_CONFIG_FILE, new DefaultMapEntry<>( TEST_PERSISTENCE_XML_FILE_NAME, DEPLOYED_PERSISTENCE_XML_FILE_NAME ) );
		testProperties.put( PROXOOL_CONFIG_FILE, new DefaultMapEntry<>( TEST_PROXOOL_PROPERTIES_FILE_NAME, DEPLOYED_PROXOOL_PROPERTIES_FILE_NAME ) );
		testProperties.put( JBOSS_DEPLOYMENT_STRUCTURE_CONFIG_FILE, new DefaultMapEntry<>( TEST_JBOSS_DEPLOYMENT_STRUCTURE_FILE_NAME,
																						 DEPLOYED_JBOSS_DEPLOYMENT_STRUCTURE_FILE_NAME ) );
		projectPackages = new ArrayList<>();
		projectPackages.add( getClass().getPackage() );
		projectPackages.add( Address.class.getPackage() );
		projectPackages.add( AddressEmbedded.class.getPackage() );
	}

	public Map.Entry<String, String> getFilesName( String property ) {
		return testProperties.get( property );
	}

	public String getTestFileName( String property ) {
		return testProperties.get( property ).getKey();
	}

	public String getDeployedFileName( String property ) {
		return testProperties.get( property ).getValue();
	}

	public Iterator<Map.Entry<String, String>> iteratorProperties() {
		return testProperties.values().iterator();
	}

	public Iterator<Package> iteratorPackages() {
		return projectPackages.iterator();
	}

}
