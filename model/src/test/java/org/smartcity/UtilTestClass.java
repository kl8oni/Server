package org.smartcity;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import org.smartcity.entity.Address;
import org.smartcity.entity.jpa.AddressEmbedded;

public class UtilTestClass {

	public static final String PRODUCTION_DEPLOYMENT = "Production";

	public static final int DEFAULT_TEST_TIME = 10000;

	public static final UtilTestClass INSTANCE = new UtilTestClass();

	private UtilTestClass() {
	}

	public EnterpriseArchive createDeploymentArchive() {
		EnterpriseArchive ear = ShrinkWrap.create( EnterpriseArchive.class );
		File[] libs = Maven.resolver()
				.loadPomFromFile( "pom.xml" )
				.importCompileAndRuntimeDependencies()
				.resolve()
				.withTransitivity()
				.asFile();
		ear.addAsModules( libs );
		for ( Map.Entry<String, String> configFile : INSTANCE.getConfigFiles().entrySet() ) {
			ear.addAsManifestResource( configFile.getKey(), ArchivePaths.create( configFile.getValue() ) );
		}
		for ( Map.Entry<String, Collection<String>> deployFiles : INSTANCE.getConfigFilesForTest().entrySet() ) {
			String fileType = deployFiles.getKey();
			Collection<String> files = deployFiles.getValue();
			for ( String file : files ) {
				ear.addAsManifestResource( INSTANCE.buildConfigurationFile( fileType, file ),
										   ArchivePaths.create( DEPLOY_FILE_PREFIX + file ) );
			}
		}
		Iterator<Package> iteratorPackages = INSTANCE.iteratorPackages();
		JavaArchive build = ShrinkWrap.create( JavaArchive.class, "build.jar" );
		while ( iteratorPackages.hasNext() ) {
			Package packageEntity = iteratorPackages.next();
			build.addPackage( packageEntity );
		}
		ear.addAsModule( build );
		return ear;
	}

	private List<Package>       projectPackages;
	private Collection<String>  configFileNames;
	private Collection<String>  testConfigFileNames;
	private Map<String, String> configFiles;

	private static final String TEST_FILE_PREFIX       = "test-";
	private static final String DEPLOY_FILE_PREFIX     = "deploy-";
	private static final String TEST_FILE_TYPE         = "./src/test";
	private static final String DEPLOY_FILE_TYPE       = "./src/main";
	private static final String INTERMEDIATE_PATH_FILE = "/resources/";

	private Iterator<Package> iteratorPackages() {
		if ( projectPackages == null ) {
			projectPackages = new ArrayList<>();
			projectPackages.add( getClass().getPackage() );
			projectPackages.add( Address.class.getPackage() );
			projectPackages.add( AddressEmbedded.class.getPackage() );
		}
		return projectPackages.iterator();
	}

	private File buildConfigurationFile( String fileType, String fileName ) {
		String filePath = new StringBuilder( fileType )
				.append( INTERMEDIATE_PATH_FILE )
				.append( fileName ).toString();
		return new File( filePath );
	}

	private Collection<String> getFileNames() {
		if ( configFileNames == null ) {
			configFileNames = new ArrayList<>();
			configFileNames.add( "persistence.xml" );
			configFileNames.add( "hibernate.cfg.xml" );
			configFileNames.add( "jboss-deployment-structure.xml" );
			configFileNames.add( "proxool.properties" );
			configFileNames.add( "ehcache.xml" );
		}
		return configFileNames;
	}

	private Map<String, Collection<String>> getConfigFilesForTest() {
		Map<String, Collection<String>> availableFiles = new HashMap<>();
		Collection<String> deployFileNames = getFileNames();
		availableFiles.put( DEPLOY_FILE_TYPE, deployFileNames );
		Collection<String> testFileNames = getTestConfigFiles();
		availableFiles.put( TEST_FILE_TYPE, testFileNames );
		return availableFiles;
	}

	private Collection<String> getTestConfigFiles() {
		if ( testConfigFileNames == null ) {
			testConfigFileNames = new ArrayList<>();
			for ( String fileName : getFileNames() ) {
				testConfigFileNames.add( TEST_FILE_PREFIX + fileName );
			}
		}
		return testConfigFileNames;
	}

	private Map<String, String> getConfigFiles() {
		if ( configFiles == null ) {
			configFiles = new HashMap<>();
			Iterator<String> testConfigFiles = getTestConfigFiles().iterator();
			Iterator<String> configFile = getFileNames().iterator();
			while ( testConfigFiles.hasNext()
					&& configFile.hasNext() ) {
				configFiles.put( testConfigFiles.next(), configFile.next() );
			}
		}
		return configFiles;
	}

}
