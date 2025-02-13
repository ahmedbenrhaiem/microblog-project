📌 Overview of Lab 5
In this lab, we focused on designing the database schema, validating it on HSQLDB, and storing the SQL scripts in Git.
Why is This Important?
    The database is the backbone of the Microblog application. Without a properly designed schema, the application cannot store users, posts, or followers.
    HSQLDB (HyperSQL Database) allows us to test the database structure before integrating it into the Java application.
    Using Git ensures that all team members have access to the latest SQL scripts and that nothing is lost.

📌 Tasks Completed
✅ Task 1: Database Schema Design in SQL
The first task was to design the database schema by creating three essential tables:
1️⃣ user – Stores users' information (e.g., username, email, password).
2️⃣ follower – Manages user follow relationships (who follows whom).
3️⃣ post – Stores user-generated content (microblog posts).

🔹 Step 1: Create the SQL Schema File
We created a file named create-db.sql in:
📂 src/main/resources/sql/create-db.sql
✅ SQL Schema (create-db.sql):
-----------
📌 What This Does:
    Drops existing tables if they exist (to prevent conflicts).
    Creates the user table with unique constraints on username and email.
    Creates the follower table to track follow relationships.
    Creates the post table to store posts linked to a user.
✅ This ensures our Microblog database has a proper structure.

✅ Task 2: Insert Sample Data into the Database
To test our database, we inserted initial data into a file called insert-data.sql.
📂 File Path: src/main/resources/sql/insert-data.sql
✅ SQL Data Insertion (insert-data.sql):
--------
📌 What This Does:
    Inserts 3 users into the user table.
    Creates follower relationships between users.
    Inserts 3 posts, each linked to a user.
✅ Now the database has some test data for validation.

✅ Task 3: Database Schema Validation on HSQLDB
Once the schema and data scripts were written, we needed to test them in HSQLDB.

🔹 Step 1: Download and Install HSQLDB
    We downloaded HSQLDB from http://hsqldb.org/
    Extracted the files into a folder named hsqldb/

🔹 Step 2: Configure the HSQLDB Server
Inside the hsqldb/ folder, we created a configuration file:
📂 File Path: hsqldb/test.properties
✅ Contents (test.properties):
server.database.0=file:hsqldb/microblogdb
server.dbname.0=testdb
This tells HSQLDB to store the database in a file (microblogdb) instead of running in-memory.

🔹 Step 3: Start the HSQLDB Server
To start the database:
java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/microblogdb --dbname.0 testdb
✅ This launches HSQLDB and makes it listen for connections.

🔹 Step 4: Use the Database Manager GUI
To manually inspect the database, we opened the HSQLDB Database Manager:
java -classpath lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
✅ Using this GUI, we could run SQL queries to check that tables and data were created correctly.

✅ Task 4: Upload SQL Scripts to Git
Once we confirmed that the database schema was correct, we uploaded the SQL scripts to Git.
🔹 Step 1: Stage the SQL Files
git add src/main/resources/sql/create-db.sql
git add src/main/resources/sql/insert-data.sql
🔹 Step 2: Commit the Changes
git commit -m "Added SQL scripts for database schema and test data"
🔹 Step 3: Push to GitHub
git push origin main
✅ Now the SQL scripts are stored in Git for all team members to access.





📌 Overview of Lab 6
In this lab, we configured the Spring application to use a database and Hibernate ORM to manage entity objects. The main objectives were:
✔ Adding missing dependencies to pom.xml for Hibernate and Spring ORM.
✔ Creating Spring configuration files to manage the application's behavior.
✔ Setting up the database connection using HSQLDB and configuring the DataSource.
✔ Configuring EntityManager to enable ORM functionality.
📌 Tasks Completed

✅ Task 1: Adding Missing Dependencies to pom.xml
We added the required dependencies to enable Hibernate ORM and Spring ORM in the project.
🔹 Updated pom.xml with the following dependencies:

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.2.10.Final</version>
</dependency>

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.2.10.Final</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>4.3.8.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.3.8.RELEASE</version>
</dependency>

🔹 Moved version numbers to the <properties> section in pom.xml:

<properties>
    <hibernate.version>5.2.10.Final</hibernate.version>
    <spring.version>4.3.8.RELEASE</spring.version>
</properties>

🔹 Tested the setup using Maven:

mvn clean install

✅ Maven did not return errors after installation.

✅ Task 2: Creating Spring Configuration Files
Spring requires configuration files to manage the DispatcherServlet and application behavior.
1️⃣ Created web.xml in src/main/webapp/WEB-INF/
This file registers the Spring DispatcherServlet, which processes all HTTP requests.
🔹 File Content (web.xml):

<web-app id="WebApp_ID" version="2.4"
         xmlns="http://java.sun.com/xml/ns/j2ee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee 
         http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

  <display-name>Microblog</display-name>

  <servlet>
        <servlet-name>mvc-dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

  <servlet-mapping>
        <servlet-name>mvc-dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

   <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

</web-app>

✅ This ensures Spring handles all requests correctly.

2️⃣ Created applicationContext.xml in WEB-INF/

This file configures Spring scanning for components and annotations.

🔹 File Content (applicationContext.xml):

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
            http://www.springframework.org/schema/mvc
            http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-4.0.xsd">

  <context:component-scan base-package="pl.wwsis.microblog.model" />
    <context:annotation-config />

</beans>

✅ This allows Spring to automatically scan and register Java components.

✅ Task 3: Setting Up the Database Connection (HSQLDB)

Since HSQLDB is not installed via Homebrew, we had to manually download and set it up.
-Downloaded HSQLDB from the official website:
-Unzipped the file:
-Started the HSQLDB database:
java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/microblogdb --dbname.0 testdb
✅ HSQLDB is now running and accepting connections.

🔹 Configured the DataSource in applicationContext.xml:

<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="org.hsqldb.jdbc.JDBCDriver"/>
    <property name="url" value="jdbc:hsqldb:file:hsqldb/microblogdb"/>
    <property name="username" value="sa"/>
    <property name="password" value=""/>
</bean>

🔹 Ensured HSQLDB dependency was present in pom.xml:

<dependency>
    <groupId>org.hsqldb</groupId>
    <artifactId>hsqldb</artifactId>
    <version>2.7.4</version>
</dependency>

✅ Spring is now connected to the HSQLDB database.

✅ Task 4: Configuring EntityManager (JPA + Hibernate)

🔹 Configured EntityManager in applicationContext.xml:

<tx:annotation-driven />

<bean id="entityManagerFactoryBean"
      class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="packagesToScan" value="pl.wwsis.microblog.model"/>
    <property name="jpaVendorAdapter">
        <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
            <property name="showSql" value="false"/>
            <property name="databasePlatform" value="org.hibernate.dialect.HSQLDialect"/>
        </bean>
    </property>
    <property name="jpaProperties">
        <props>
            <prop key="hibernate.hbm2ddl.auto">update</prop>
        </props>
    </property>
</bean>

<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactoryBean"/>
</bean>

✅ Spring can now manage entity objects using JPA and Hibernate.

📌 Summary of Lab 6:
✔ Added missing dependencies to pom.xml for Hibernate & Spring ORM.
✔ Created Spring configuration files (web.xml, applicationContext.xml, rootApplicationContext.xml).
✔ Configured HSQLDB as the database and connected it to Spring.
✔ Set up EntityManager to manage database interactions.
✔ Ran mvn clean install to confirm everything works.
📌 How to Verify Lab 6 is Complete?
 Run:
 mvn clean install
  ✅ there are no errors, Lab 6 is successfully completed!
🚀 Now, our Spring + Hibernate + HSQLDB setup is complete!
