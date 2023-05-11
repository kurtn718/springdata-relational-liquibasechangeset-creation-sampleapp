package com.kurtniemi.sqlgenerationdemo;

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
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.relational.core.mapping.schemasqlgeneration.ColumnModel;
import org.springframework.data.relational.core.mapping.schemasqlgeneration.SchemaSQLGenerationDataModel;
import org.springframework.data.relational.core.mapping.schemasqlgeneration.TableModel;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class SqlgenerationdemoApplication implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SqlgenerationdemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		// Create Liquibase database
		// ::TODO:: Do we want to provide helper methods (i.e. detect that Database is MySQL and create the appropriate class
		// (i.e. a method that takes in a RelationalContext and a Datasource class)
		Database database = new SQLiteDatabase();
		JdbcMappingContext context = (JdbcMappingContext) applicationContext.getBean(JdbcMappingContext.class);
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		DatabaseConnection databaseConnection = new JdbcConnection(dataSource.getConnection());
		database.setConnection(databaseConnection);

		// Create Model class
		SchemaSQLGenerationDataModel model = new SchemaSQLGenerationDataModel(context);

		// Pass in Liquibase database and output file
		String outputFileName = "./changeset.yml";
		model.generateLiquibaseChangeset(database, outputFileName);

		// We know what we should have...
/*		List<TableModel> tables = model.getTableData();
		for (TableModel t : tables) {
			System.out.println(t.getName());
			for (ColumnModel c : t.getColumns()) {
				System.out.println(c.getName());
			}
		}
*/
		//context.writeAndGetSnapshot(d.getConnection());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


}
