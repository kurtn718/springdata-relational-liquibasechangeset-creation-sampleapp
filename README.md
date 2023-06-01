# How to generate Liquibase changesets using Spring Data JDBC.   

This project contains a sample application to generate Liquibase changesets using Spring Data JDBC.   

The example code is here: [a relative link](src/main/java/kurtniemi/liquibasechangesetdemo/LiquibaseChangeSetWriterApplication.java)

Pre-requisites beyond typical Spring Data JDBC setup

1) Add Liquibase to your POM file

Demo of SQL Generation for Spring Data JDBC

```
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
			<version>4.21.1</version>
		</dependency>
```

2) Implement an application similar to the example code.   

Best practice tip:  Make the application use a different Spring Profile.  

Even though generating a Liquibase changeset is not a destructive operation, having it on a separate Spring profile means it will not run when running
your main application.   
