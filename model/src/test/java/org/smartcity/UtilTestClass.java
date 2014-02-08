package org.smartcity;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import org.smartcity.dao.UserDAOImpl;
import org.smartcity.entity.User;

public class UtilTestClass {

	public static final String PRODUCTION_DEPLOYMENT = "Production";

	public static final UtilTestClass INSTANCE = new UtilTestClass();

	private UtilTestClass() {
	}

	public EnterpriseArchive createDeploymentArchive() {
		EnterpriseArchive ear = ShrinkWrap.create( EnterpriseArchive.class, "Smart-City.ear" );
		File[] libs = Maven.resolver()
				.loadPomFromFile( "pom.xml" )
				.importCompileAndRuntimeDependencies()
				.resolve()
				.withTransitivity()
				.asFile();
		ear.addAsLibraries( libs );
		JavaArchive build = ShrinkWrap.create( JavaArchive.class, "model.jar" );
		for( int i = 0; i < getModelConfigurationFileNames().size(); i++ ) {
			String file = getModelConfigurationFileNames().get( i );
			build.addAsResource( file );
		}
		for( int i = 0; i < getManifestResourcesFileNames().size(); i++ ) {
			String manifestResource = getManifestResourcesFileNames().get( i );
			build.addAsManifestResource( manifestResource );
		}
		for( int i = 0; i < getPackages().size(); i++ ) {
			Package packageEntity = getPackages().get( i );
			build.addPackage( packageEntity );
		}
		ear.addAsModule( build );
		ear.addAsResource( "application.xml", "application.xml" );
		return ear;
	}

	private List<Package> projectPackages;
	private List<String>  configFileNames;
	private List<String>  manifestResourcesFileNames;

	private List<Package> getPackages() {
		if( projectPackages == null ) {
			projectPackages = new ArrayList<>();
			projectPackages.add( getClass().getPackage() );
			projectPackages.add( User.class.getPackage() );
			projectPackages.add( UserDAOImpl.class.getPackage() );
		}
		return projectPackages;
	}

	private List<String> getModelConfigurationFileNames() {
		if( configFileNames == null ) {
			configFileNames = new ArrayList<>();
			configFileNames.add( "hibernate.cfg.xml" );
			configFileNames.add( "jboss-deployment-structure.xml" );
			configFileNames.add( "infinispan.xml" );
		}
		return configFileNames;
	}

	private List<String> getManifestResourcesFileNames() {
		if( manifestResourcesFileNames == null ) {
			manifestResourcesFileNames = new ArrayList<>();
			manifestResourcesFileNames.add( "persistence.xml" );
			manifestResourcesFileNames.add( "beans.xml" );
		}
		return manifestResourcesFileNames;
	}

}
