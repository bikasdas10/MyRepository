package com.xchanging.jpa;

import java.sql.Types;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class TestJDBCTemplate {
	
	private static final String driverClassName = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@actimrdevdatz01:1521:imrdev";
	private static final String dbUsername = "imr_gfish";
	private static final String dbPassword = "imr_gfish";

	private static final String insertSql =

  "INSERT INTO employee (" +

  "	name, " +

  "	surname, " +

  "	title, " +

  "	created) " +

  "VALUES (?, ?, ?, ?)";

	private static DataSource dataSource;
	
	public static void main(String[] args) throws Exception {
	
		dataSource = getDataSource();
		
//		saveRecord("John", "Black", "Software developer", new Date());
		saveRecord("Tom", "Green", "Project Manager", new Date());
		
	}
	
	public static void saveRecord(String name, String surname, String title, Date created) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		final String sql = "INSERT INTO REPOSITORY.TBLTADOCSVALIDATIONDETAILS(SID,TA_MSG_ID,STATUS,TA_VALIDATION_RQ_TIME, MISSING_DOCUMENTS_COUNT,COMMENTS,WORK_PACKAGE_ID) VALUES (REPOSITORY.SEQTADOCSVALIDATIONSID.nextval, ?, ?, ?, ?, ?, ?)";
//		final String sql = "INSERT INTO REPOSITORY.TBLTADOCSVALIDATIONDETAILS(SID,TA_MSG_ID,STATUS,TA_VALIDATION_RQ_TIME) VALUES (?,?,?,?)";
		template.update(sql,new Object[]{"7E833691-34EA-48E5-B126-39047C44E6CF","P",new Date(),"1","comment","EA-29638"});
//        template.update(sql,900,"7E833691-34EA-48E5-B126-39047C44E6CF","P",new Date());
        		
	}
	
	public static DriverManagerDataSource getDataSource() {

  DriverManagerDataSource dataSource = new DriverManagerDataSource();

  dataSource.setDriverClassName(driverClassName);

  dataSource.setUrl(url);

  dataSource.setUsername(dbUsername);

  dataSource.setPassword(dbPassword);

  return dataSource;
    }
		
}
