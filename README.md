# How to generate Liquibase changesets using Spring Data JDBC.   

This project contains a sample application to generate Liquibase changesets using Spring Data JDBC.   

Example code is here: [LiquibaseChangeSetWriterApplication](https://github.com/kurtn718/springdata-relational-liquibasechangeset-creation-sampleapp/blob/main/src/main/java/com/kurtniemi/liquibasechangesetdemo/app/LiquibaseChangeSetWriterApplication.java)

Steps assuming you have an existing Spring Data JDBC application:

1) Add Liquibase to your POM file


```
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
			<version>4.21.1</version>
		</dependency>
```

2) Get the JdbcMappingContext bean from your application context, and then create a MappedTables object

```
		JdbcMappingContext context = (JdbcMappingContext) applicationContext.getBean(JdbcMappingContext.class);
		MappedTables sourceModel = new MappedTables(context);
```

3) Optional:  If generating a changeset against an existing database - create a Liquibase Database object as follows:

```
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		DatabaseConnection databaseConnection = new JdbcConnection(dataSource.getConnection());

		Database database = new SQLiteDatabase();
		database.setConnection(databaseConnection);
```
Replace SQLiteDatabase with the type of your database (i.e. OracleDatabase if using Oracle).     

4) Create a LiquibaseChangeSetWriter object passing in the MappedTables and Liquibase Database object

```		LiquibaseChangeSetWriter changeSetWriter = new LiquibaseChangeSetWriter(sourceModel, database);
		outputFileResource = new FileSystemResource( "changeset.yml");
		changeSetWriter.writeChangeSet(outputFileResource);
```
Note: If not generating against an existing database use the contructor that just takes in a MappedTables object

5) Best practice tip:  Put the above code in a Spring Boot application and have it on a separate Sprint Profile (that is different than your main application)

Even though generating a Liquibase changeset is not a destructive operation, having it on a separate Spring profile means it will not run when running
your main application.   
