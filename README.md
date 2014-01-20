Server
======

Server part of new software system for control
city infrastructure

How to install test environment
===============================

1. Install PostgreSQL 9.1.11
2. Install Jboss AS 7
3. Add to Jboss new module for PostgreSQL
	3.1 Create dir `$JBOSS_HOME/modules/org/postgresql/main`
	3.2 Copy `postgresql-9.1-901.jdbc4.jar` to `$JBOSS_HOME/modules/org/postgresql/main`
	3.3 Create `module.xml` with contents:
		_<?xml version="1.0" encoding="UTF-8"?>
		<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
			<resources>
				<resource-root path="postgresql-9.1-901.jdbc4.jar"/>
				<!-- Insert resources here -->
			</resources>
			<dependencies>
				<module name="javax.api"/>
				<module name="javax.transaction.api"/>
				<module name="javax.servlet.api" optional="true"/>
			</dependencies>
		</module>_
	3.4 For configure datasource add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:
		_<subsystem xmlns="urn:jboss:domain:datasources:1.0">
			<datasources>
				...
				<datasource jndi-name="java:jboss/datasources/SmartCityDataSourceTest" pool-name="smart-city-db-pool-test" enabled="true" use-java-context="true">
					<connection-url>jdbc:postgresql://localhost/smart-city-test</connection-url>
					<driver>postgresql</driver>
					<security>
						<user-name>${user-name}</user-name>
						<password>${password}</password>
					</security>
				</datasource>
				<drivers>
					...
					<driver name="postgresql" module="org.postgresql">
						<xa-datasource-class>org.postgresql.ds.PGPoolingDataSource</xa-datasource-class>
					</driver>
				</drivers>
			...
			</datasources>
		</subsystem>_
	3.5 For configure logging add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:
		_<subsystem xmlns="urn:jboss:domain:logging:1.1">
			...
			<periodic-rotating-file-handler name="SMART-CITY-LOG">
				<formatter>
					<pattern-formatter pattern="%d{HH:mm:ss,SSS} [%c.%M] %s%E%n"/>
				</formatter>
				<file relative-to="jboss.server.log.dir" path="smart-city.log"/>
				<suffix value=".yyyy-MM-dd"/>
				<append value="true"/>
			</periodic-rotating-file-handler>
			...
			<logger category="org.smartcity" use-parent-handlers="false">
				<level name="DEBUG"/>
				<handlers>
					<handler name="SMART-CITY-LOG"/>
				</handlers>
			</logger>
		...
		</subsystem>_
	3.6 (Optional) For configure logging hibernate framework in file add to
	`$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:
		_<subsystem xmlns="urn:jboss:domain:logging:1.1">
		...
			<periodic-rotating-file-handler name="HIBERNATE-LOG">
				<formatter>
					<pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] %s%E%n"/>
				</formatter>
				<file relative-to="jboss.server.log.dir" path="hibernate.log"/>
				<suffix value=".yyyy-MM-dd"/>
				<append value="true"/>
			</periodic-rotating-file-handler>
			...
			<logger category="org.hibernate" use-parent-handlers="false">
				<level name="INFO"/>
				<handlers>
					<handler name="HIBERNATE-LOG"/>
				</handlers>
			</logger>
		...
		</subsystem>_
4. Create PostgreSQL database with commands
	_createdb -u {user-name} smart-city-test
	plsql -u {user-name} -d smart-city-test
	\i path/to/smart-city-project/scripts/create.sql_
