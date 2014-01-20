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
_<?xml version="1.0" encoding="UTF-8"?>_
_	<module xmlns="urn:jboss:module:1.1" name="org.postgresql">_
_<resources>_
_<resource-root path="postgresql-9.1-901.jdbc4.jar"/>_
_<!-- Insert resources here -->_
_</resources>_
_<dependencies>_
_<module name="javax.api"/>_
_<module name="javax.transaction.api"/>_
_<module name="javax.servlet.api" optional="true"/>_
_</dependencies>_
_</module>_
7. For configure datasource add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:
_<subsystem xmlns="urn:jboss:domain:datasources:1.0">_
_<datasources>_
_..._
_<datasource jndi-name="java:jboss/datasources/SmartCityDataSourceTest" pool-name="smart-city-db-pool-test" enabled="true" use-java-context="true">_
_<connection-url>jdbc:postgresql://localhost/smart-city-test</connection-url>_
_<driver>postgresql</driver>_
_<security>_
_<user-name>${user-name}</user-name>_
_<password>${password}</password>_
_</security>_
_</datasource>_
_<drivers>_
_..._
_<driver name="postgresql" module="org.postgresql">_
_<xa-datasource-class>org.postgresql.ds.PGPoolingDataSource</xa-datasource-class>_
_</driver>_
_</drivers>_
_..._
_</datasources>_
_</subsystem>_
8. For configure logging add to `$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:
_<subsystem xmlns="urn:jboss:domain:logging:1.1">_
_..._
_<periodic-rotating-file-handler name="SMART-CITY-LOG">_
_<formatter>_
_<pattern-formatter pattern="%d{HH:mm:ss,SSS} [%c.%M] %s%E%n"/>_
_</formatter>_
_<file relative-to="jboss.server.log.dir" path="smart-city.log"/>_
_<suffix value=".yyyy-MM-dd"/>_
_<append value="true"/>_
_</periodic-rotating-file-handler>_
_..._
_<logger category="org.smartcity" use-parent-handlers="false">_
_<level name="DEBUG"/>_
_<handlers>_
_<handler name="SMART-CITY-LOG"/>_
_</handlers>_
_</logger>_
_..._
_</subsystem>_
9. (Optional) For configure logging hibernate framework in file add to
`$JBOSS_HOME/standalone/configuration/standalone.xml` next lines:
_<subsystem xmlns="urn:jboss:domain:logging:1.1">_
_..._
_<periodic-rotating-file-handler name="HIBERNATE-LOG">_
_<formatter>_
_<pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] %s%E%n"/>_
_</formatter>_
_<file relative-to="jboss.server.log.dir" path="hibernate.log"/>_
_<suffix value=".yyyy-MM-dd"/>_
_<append value="true"/>_
_</periodic-rotating-file-handler>_
_..._
_<logger category="org.hibernate" use-parent-handlers="false">_
_<level name="INFO"/>_
_<handlers>_
_<handler name="HIBERNATE-LOG"/>_
_</handlers>_
_</logger>_
_..._
_</subsystem>_
10. Create PostgreSQL database with commands
_createdb -u {user-name} smart-city-test_
_plsql -u {user-name} -d smart-city-test_
_\i path/to/smart-city-project/scripts/create.sql_
