Server
======

Server part of new software system for control
city infrastructure

How to install test environment
===============================

1. Install PostgreSQL 9.1.11
2. Install Jboss AS 7
3. Add to Jboss new module for PostgreSQL
4. Create dir `$JBOSS_HOME/modules/org/postgresql/main`
5. Copy `postgresql-9.1-901.jdbc4.jar` to `$JBOSS_HOME/modules/org/postgresql/main`
6. Create `module.xml` with contents:

		<?xml version="1.0" encoding="UTF-8"?>
		<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
			<resources>
				<resource-root path="postgresql-9.1-901.jdbc4.jar"/>
			</resources>
		</module>

7. Add to Jboss new module for Proxool database pool
8. Create dir `$JBOSS_HOME/modules/org/proxool/main`
9. Copy `proxool-0.8.3.jar` to `$JBOSS_HOME/modules/org/proxool/main`
10. Create `module.xml` with contents:

		<?xml version="1.0" encoding="UTF-8"?>
		<module xmlns="urn:jboss:module:1.1" name="org.proxool">
			<resources>
				<resource-root path="proxool-0.8.3.jar"/>
			</resources>
		</module>

11. Add to Jboss new module Ehcache
12. Create dir `$JBOSS_HOME/modules/net/sf/ehcache/main`
13. Copy `ehcache-2.8.0.jar` to `$JBOSS_HOME/modules/net/sf/ehcache/main`
14. Create `module.xml` with contents:

		<?xml version="1.0" encoding="UTF-8"?>
		<module xmlns="urn:jboss:module:1.1" name="net.sf.ehcache">
			<resources>
				<resource-root path="ehcache-2.8.0.jar"/>
			</resources>
			<dependencies>
				<module name="org.slf4j"/>
			</dependencies>
		</module>

10. For configure datasource add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:datasources:1.0">
			<datasources>
				<...>
				<datasource jndi-name="java:jboss/datasources/SmartCityDataSource" pool-name="smart-city-db-pool" enabled="true" use-java-context="true">
					<connection-url>jdbc:postgresql://localhost/smart-city</connection-url>
					<driver>postgresql</driver>
					<security>
						<user-name>${user-name}</user-name>
						<password>${password}</password>
					</security>
				</datasource>
				<...>
				<drivers>
					<...>
					<driver name="postgresql" module="org.postgresql">
						<xa-datasource-class>org.postgresql.ds.PGPoolingDataSource</xa-datasource-class>
					</driver>
					<...>
				</drivers>
				<...>
			</datasources>
		</subsystem>

11. For configure logging smart-city module
add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:logging:1.1">
		<...>
			<periodic-rotating-file-handler name="SMART-CITY-LOG">
				<formatter>
					<pattern-formatter pattern="%d{HH:mm:ss,SSS} [%c.%M] %s%E%n"/>
				</formatter>
				<file relative-to="jboss.server.log.dir" path="smart-city.log"/>
				<suffix value=".yyyy-MM-dd"/>
				<append value="true"/>
			</periodic-rotating-file-handler>
			<...>
			<logger category="org.smartcity" use-parent-handlers="false">
				<level name="DEBUG"/>
				<handlers>
					<handler name="SMART-CITY-LOG"/>
				</handlers>
			</logger>
		<...>
		</subsystem>

12. (Optional) For configure logging hibernate framework in file
add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:logging:1.1">
		<...>
			<periodic-rotating-file-handler name="HIBERNATE-LOG">
				<formatter>
					<pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] %s%E%n"/>
				</formatter>
				<file relative-to="jboss.server.log.dir" path="hibernate.log"/>
				<suffix value=".yyyy-MM-dd"/>
				<append value="true"/>
			</periodic-rotating-file-handler>
			<...>
			<logger category="org.hibernate" use-parent-handlers="false">
				<level name="INFO"/>
				<handlers>
					<handler name="HIBERNATE-LOG"/>
				</handlers>
			</logger>
		<...>
		</subsystem>

13. Add dom4j as a global module.

		<subsystem xmlns="urn:jboss:domain:ee:1.1">
		<...>
			<global-modules>
				<module name="org.dom4j" slot="main"/>
			</global-modules>
		<...>
		</subsystem>

14. Create PostgreSQL database with commands

		createdb -U {user-name} smart-city
		plsql -U {user-name} -d smart-city
		\i path/to/smart-city-project/scripts/create.sql
