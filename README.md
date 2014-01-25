Server
======

Server part of new software system for control
city infrastructure

How to install test environment
===============================

1. Install PostgreSQL 9.1.11
2. Install Wildfly 8.0.0.CR1
3. Add to Wildfly new module for PostgreSQL
4. Create dir `$JBOSS_HOME/modules/system/layers/base/org/postgresql/main`
5. Copy `postgresql-9.1-901.jdbc4.jar` to `$JBOSS_HOME/modules/system/layers/base/org/postgresql/main`
6. Create `module.xml` with contents:

		<?xml version="1.0" encoding="UTF-8"?>
		<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
			<resources>
				<resource-root path="postgresql-9.1-901.jdbc4.jar"/>
			</resources>
		</module>

7. For configure datasource add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:datasources:2.0">
			<datasources>
				<...>
					<datasource jndi-name="java:jboss/datasources/SmartCityDataSource" pool-name="proxool.smart-city-db-pool" enabled="true" use-java-context="true">
						<connection-url>jdbc:postgresql://localhost/smart-city</connection-url>
						<driver>postgresql</driver>
						<pool>
							<min-pool-size>10</min-pool-size>
							<initial-pool-size>10</initial-pool-size>
							<max-pool-size>30</max-pool-size>
						</pool>
						<security>
							<user-name>${user.name}</user-name>
							<password>${password}</password>
						</security>
						<statement>
							<prepared-statement-cache-size>50</prepared-statement-cache-size>
						</statement>
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

8. Change line `<local-cache name="entity">` in `$JBOSS_HOME/standalone/configuration/standalone.xml`
to `<local-cache name="local-entity">`

9. For configure logging smart-city module
add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:logging:2.0">
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

10. (Optional) For configure logging hibernate framework in file
add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:logging:2.0">
		<...>
			<periodic-rotating-file-handler name="HIBERNATE-LOG">
				<formatter>
					<pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c.%M] %s%E%n"/>
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

11. (Optional) For configure logging infinispan framework in file
add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:

		<subsystem xmlns="urn:jboss:domain:logging:2.0">
		<...>
			<periodic-rotating-file-handler name="INFINISPAN-LOG">
				<formatter>
					<pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c.%M] %s%E%n"/>
				</formatter>
				<file relative-to="jboss.server.log.dir" path="infinispan.log"/>
				<suffix value=".yyyy-MM-dd"/>
				<append value="true"/>
			</periodic-rotating-file-handler>
			<...>
			<logger category="org.infinispan" use-parent-handlers="false">
				<level name="INFO"/>
				<handlers>
					<handler name="INFINISPAN-LOG"/>
				</handlers>
			</logger>
		<...>
		</subsystem>

12. Create PostgreSQL database with commands

		createdb -U {user-name} smart-city
		plsql -U {user-name} -d smart-city
		\i path/to/smart-city-project/scripts/create.sql
