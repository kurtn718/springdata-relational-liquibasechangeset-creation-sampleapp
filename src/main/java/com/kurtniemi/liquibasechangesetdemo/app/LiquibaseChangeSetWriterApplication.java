package com.kurtniemi.liquibasechangesetdemo.app;

import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.core.SQLiteDatabase;
import liquibase.database.jvm.JdbcConnection;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.relational.core.mapping.schemasqlgeneration.LiquibaseChangeSetWriter;
import org.springframework.data.relational.core.mapping.schemasqlgeneration.MappedTables;

import javax.sql.DataSource;

@SpringBootApplication
@Profile("generate-changeset")
public class LiquibaseChangeSetWriterApplication implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseChangeSetWriterApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		// Code sample shows how to create a changeset that can be applied against an empty/new database,
		// as well as against an existing database

		// Get Database state as modeled in code
		JdbcMappingContext context = (JdbcMappingContext) applicationContext.getBean(JdbcMappingContext.class);
		MappedTables sourceModel = new MappedTables(context);

		// Create changeset that can be applied against an empty (or new) database
		LiquibaseChangeSetWriter emptyDBChangeSetWriter = new LiquibaseChangeSetWriter(sourceModel);
		Resource outputFileResource = new FileSystemResource( "empty-db-changeset.yml");
		emptyDBChangeSetWriter.writeChangeSet(outputFileResource);

		// Create a changeset that can be applied against an existing database.
		// Step #1: Get a DatabaseConnection to the database
		// Step #2: Create a Liquibase Database object of the type that matches your database (example here is for MySQL)
		// Step #3: Use constructor in LiquibaseChangeSetWriter that takes in a Database object

		DataSource dataSource = applicationContext.getBean(DataSource.class);
		DatabaseConnection databaseConnection = new JdbcConnection(dataSource.getConnection());

		Database database = new SQLiteDatabase();
		database.setConnection(databaseConnection);

		LiquibaseChangeSetWriter changeSetWriter = new LiquibaseChangeSetWriter(sourceModel, database);
		outputFileResource = new FileSystemResource( "changeset.yml");
		changeSetWriter.writeChangeSet(outputFileResource);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
