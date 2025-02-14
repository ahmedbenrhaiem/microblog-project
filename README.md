

---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
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




---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
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



---------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------

Overview of Lab 7

Lab 7 is about implementing the model layer, which represents database tables as Java classes. Instead of writing SQL queries directly, we define Java objects that interact with the database using JPA (Java Persistence API).
🔹 What is the Model Layer?

    The model layer is the part of the application that manages data and business logic.
    It contains Java classes (models) that represent database tables.
    These models are mapped to the database using JPA annotations, which allow Hibernate to interact with the database automatically.

🔹 Why is this Needed?

    Instead of writing raw SQL queries, we use object-oriented programming to manage data.
    Using JPA and Hibernate allows automatic database handling, reducing the need for manual queries.
    It ensures better maintainability and separation of concerns in the application.


What Was Implemented in Lab 7?

In this lab, we:
✔ Created model classes (User, Post, Follower) to represent database tables.
✔ Used JPA annotations (@Entity, @Table, @Column) to link Java objects to relational tables.
✔ Ensured each class had proper getters and setters.
✔ Updated the Spring configuration (applicationContext.xml) to detect and manage these entity classes.


TASK 1: Define Java Classes as Model Layer

Each database table needs to have a corresponding Java class in a dedicated package.

✅ Step 1: Create the model Package
📌 What is a Package?

    A package in Java is like a folder that organizes related classes together.
    In this case, we are creating the pl.wwsis.microblog.model package, which will store all model classes.

📌 Why is this Needed?

    Helps keep the project structured and makes it easier to find related files.
    Ensures that Spring and Hibernate can detect model classes in the correct location.


✅ Step 2: Define Model Classes

Each model class represents a table in the database.
We created three classes: 1️⃣ User.java → Represents users in the system.
2️⃣ Post.java → Represents posts made by users.
3️⃣ Follower.java → Represents follow relationships between users.
📌 What is an Entity Class?

    An entity class is a Java class that represents a table in the database.
    It contains fields (attributes) that map to columns in the database.

📌 Why Do We Need Entity Classes?

    They make it easy to interact with the database using Java objects.
    JPA annotations automatically handle database mapping, so no manual SQL is needed.



✅ Step 3: Update Spring Configuration to Detect Entity Classes

📂 File Path: src/main/webapp/WEB-INF/applicationContext.xml
📌 What is applicationContext.xml?

    It is a Spring configuration file that tells Spring which packages to scan for entity classes.

📌 Why is this Needed?

    Without this step, Hibernate would not know where to find the model classes.
    Spring needs to automatically detect all entity classes to manage them correctly.

🔹 Action: Update applicationContext.xml

Add the following property to register the model package:

<property name="packagesToScan" value="pl.wwsis.microblog.model" />

✅ Now, Hibernate will automatically detect and manage our model classes.

📌 TASK 2: Understanding JPA and Hibernate in Lab 7

Lab 7 relies on JPA and Hibernate to manage database interactions.
🔹 What is JPA?

    JPA (Java Persistence API) is a standard that defines how Java objects should interact with databases.
    It provides annotations like @Entity, @Table, and @Column to map Java classes to database tables.

🔹 What is Hibernate?

    Hibernate is an implementation of JPA.
    It automatically translates Java objects into database tables and vice versa.
    It eliminates the need to write SQL queries manually.

🔹 Why are JPA and Hibernate Used?

✔ No need to write complex SQL queries.
✔ Makes database interactions easier by using Java objects.
✔ Ensures code is maintainable and scalable.


📌 TASK 3: Explanation of the Three Model Classes

Now, let's explain the three model classes that were implemented.
📝 1️⃣ User.java – Represents Users Table
📌 What This Class Does

    This class stores information about each user (username, email, password).
    Each instance of User corresponds to one row in the users table.

📌 Why This Class is Needed

    Allows the system to store and manage user accounts.
    Ensures data is securely stored using password hashing.

📝 2️⃣ Post.java – Represents Posts Table
📌 What This Class Does

    This class stores information about posts (who posted them, the content, and the timestamp).
    Each instance of Post corresponds to one row in the posts table.

📌 Why This Class is Needed

    Enables users to create posts and store them in the database.
    Ensures each post is linked to a specific user.

📝 3️⃣ Follower.java – Represents Followers Table
📌 What This Class Does

    This class tracks relationships between users (who follows whom).
    Each instance of Follower corresponds to one row in the followers table.

📌 Why This Class is Needed

    Enables users to follow/unfollow each other.
    Stores follower relationships in the database for timeline generation.





---------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------

📌 Overview of Lab 8

In this lab, we implemented the DAO layer, which is responsible for interacting with the database. The DAO layer serves as a bridge between the application and the database, ensuring that data is accessed in a structured and maintainable way.
🔹 What is the DAO Layer?

    DAO (Data Access Object) is a design pattern used to provide a single interface for communication with the database.
    It abstracts database operations, allowing the application to interact with data without needing to know how it is stored or retrieved.
    The DAO layer separates business logic from data access, making the application more maintainable and scalable.

🔹 Why is This Needed?

✔ Keeps database operations organized and reusable.
✔ Prevents SQL logic from being scattered across the application.
✔ Follows the "Bridge Pattern" to separate abstraction (interfaces) from implementation (classes).
✔ Ensures consistency in database transactions using EntityManager.


📌 What Was Implemented in Lab 8?

✔ Created DAO interfaces (UserDao, PostDao, FollowerDao) to define database operations.
✔ Implemented these interfaces in DAO classes (UserDaoImpl, PostDaoImpl, FollowerDaoImpl).
✔ Used EntityManager for performing queries using JPQL (Java Persistence Query Language).
✔ Registered DAO classes as Spring Beans in applicationContext.xml.

📌 TASK 1: Creating DAO Interfaces

Each DAO interface defines the operations that can be performed on a specific database table.
✅ Step 1: Create the DAO Package
📌 What is a DAO Package?

    A package in Java is a directory that groups related classes together.
    The DAO package (com.wwsis.microblog.dao) stores all DAO interfaces.

📌 Why is this Needed?

✔ Helps organize code properly.
✔ Makes it easier to find and manage DAO interfaces.

🔹 Action: Create the DAO Package

Run the following command:

mkdir -p src/main/java/com/wwsis/microblog/dao

✅ Now, all DAO interfaces will be placed inside com.wwsis.microblog.dao.


✅ Step 2: Define DAO Interfaces

Each interface defines the methods that will interact with the database.
📝 UserDao.java – Manages Users

📂 File Path: src/main/java/com/wwsis/microblog/dao/UserDao.java

package com.wwsis.microblog.dao;

import com.wwsis.microblog.model.User;

public interface UserDao {
    User getUserByUsername(String username); // Retrieve a user by username
    void addUser(User user); // Register a new user
}

✔ Defines methods for retrieving and adding users.

📝 PostDao.java – Manages Posts

📂 File Path: src/main/java/com/wwsis/microblog/dao/PostDao.java

package com.wwsis.microblog.dao;

import com.wwsis.microblog.model.Post;
import java.util.List;

public interface PostDao {
    List<Post> getUserPosts(int userId); // Retrieves all posts of a user
    List<Post> getFullTimeline(int userId); // Retrieves all posts from the user and followed users
    List<Post> getPublicPosts(); // Retrieves all public posts
    void addPost(Post post); // Adds a new post
}

✔ Defines methods for retrieving and adding posts.
📝 FollowerDao.java – Manages Followers

📂 File Path: src/main/java/com/wwsis/microblog/dao/FollowerDao.java

package com.wwsis.microblog.dao;

public interface FollowerDao {
    void followUser(int followerId, int followeeId); // Follow a user
    void unfollowUser(int followerId, int followeeId); // Unfollow a user
    boolean isFollowing(int followerId, int followeeId); // Check if a user is following another
}

✔ Defines methods for managing follower relationships.

📌 TASK 2: Implementing DAO Interfaces Using EntityManager

Each DAO interface needs to be implemented in a corresponding class.
✅ Step 1: Create the DAO Implementation Package
📌 What is the DAO Implementation Package?

    This package stores the concrete implementations of DAO interfaces.
    The naming convention requires impl at the end of the package name.

🔹 Action: Create the DAO Implementation Package

mkdir -p src/main/java/com/wwsis/microblog/dao/impl

✅ Now, all DAO implementation classes will be placed inside com.wwsis.microblog.dao.impl.

✅ Step 2: Implement DAO Classes

Each DAO class implements an interface and uses EntityManager to interact with the database.

📝 UserDaoImpl.java – Implements UserDao
📂 File Path: src/main/java/com/wwsis/microblog/dao/impl/UserDaoImpl.java
 Handles database operations for users.

📝 PostDaoImpl.java – Implements PostDao
📂 File Path: src/main/java/com/wwsis/microblog/dao/impl/PostDaoImpl.java
 Handles database operations for posts.


📌 TASK 4: Registering DAO Beans in Spring

📂 File Path: src/main/webapp/WEB-INF/applicationContext.xml
📌 What is a Spring Bean?

    A Spring Bean is a class managed by Spring's IoC (Inversion of Control) container.
    The DAO layer must be registered as beans so they can be injected into other components.

🔹 Action: Register DAO Beans in applicationContext.xml

<bean id="UserDao" class="com.wwsis.microblog.dao.impl.UserDaoImpl"/>
<bean id="PostDao" class="com.wwsis.microblog.dao.impl.PostDaoImpl"/>
<bean id="FollowerDao" class="com.wwsis.microblog.dao.impl.FollowerDaoImpl"/>

✔ Registers DAO classes as Spring Beans.


📌 Summary

✔ Created DAO interfaces (UserDao, PostDao, FollowerDao).
✔ Implemented DAO interfaces using EntityManager.
✔ Used JPQL for querying database objects.
✔ Registered DAO classes as Spring Beans.
✔ Committed and pushed all changes to GitHub.

🚀 Now, Lab 8 is fully documented and added to README.md on GitHub! 