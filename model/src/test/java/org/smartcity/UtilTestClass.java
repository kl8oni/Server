package org.smartcity;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import org.smartcity.entity.Address;
import org.smartcity.entity.jpa.AddressEmbedded;

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
		for ( String file : getFileNames() ) {
			build.addAsResource( file );
		}
		build.addAsManifestResource( "persistence.xml" );
		Iterator<Package> iteratorPackages = iteratorPackages();
		while ( iteratorPackages.hasNext() ) {
			Package packageEntity = iteratorPackages.next();
			build.addPackage( packageEntity );
		}
		ear.addAsModule( build );
		ear.addAsResource( "application.xml", "application.xml" );
		return ear;
	}

	private List<Package>       projectPackages;
	private Collection<String>  configFileNames;

	private Iterator<Package> iteratorPackages() {
		if ( projectPackages == null ) {
			projectPackages = new ArrayList<>();
			projectPackages.add( getClass().getPackage() );
			projectPackages.add( Address.class.getPackage() );
			projectPackages.add( AddressEmbedded.class.getPackage() );
		}
		return projectPackages.iterator();
	}

	private Collection<String> getFileNames() {
		if ( configFileNames == null ) {
			configFileNames = new ArrayList<>();
			configFileNames.add( "hibernate.cfg.xml" );
			configFileNames.add( "jboss-deployment-structure.xml" );
			configFileNames.add( "proxool.properties" );
			configFileNames.add( "infinispan.xml" );
		}
		return configFileNames;
	}

}
