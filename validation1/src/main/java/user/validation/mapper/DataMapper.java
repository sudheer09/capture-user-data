package user.validation.mapper;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.springframework.jdbc.core.JdbcTemplate;

import user.validation.bo.UserInfo;

public class DataMapper {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) throws SQLException {
		System.out.println("gotchaa"+dataSource.getConnection().getMetaData().getURL());
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
		//int x = jdbcTemplate.update("INSERT INTO user_info VALUES('sud','123','abc','male')"); 
		System.out.println("updated rows");
	}
	public void mapRequest(Exchange exchange)
	{
		UserInfo req = exchange.getIn().getBody(UserInfo.class);
		System.out.println("inside dataMapper :"+req.getUserName());
		int x = jdbcTemplate.update("INSERT INTO user_info VALUES('"+req.getUserName()+"','"+req.getTelephoneNumber()+"','"+req.getPassword()+"','"+req.getGender()+"')");
		System.out.println("Updated Rows "+x);
		
	}
}
