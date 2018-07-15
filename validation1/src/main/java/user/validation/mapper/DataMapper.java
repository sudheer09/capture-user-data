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
		//System.out.println("JDBC URL : "+dataSource.getConnection().getMetaData().getURL());
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public void mapRequest(Exchange exchange) throws CustomException
	{
		UserInfo req = exchange.getIn().getBody(UserInfo.class);
		
		if(null == req.getGender() || null == req.getPassword() || null == req.getTelephoneNumber() || null == req.getUserName())
		{
			CustomException exc = new CustomException();
			exc.setStatus(400);
			exc.setTitle("Bad Request");
			exc.setDetails("mandatory field(s) missing");
			throw exc;
		}
		System.out.println("inside dataMapper :"+req.getUserName());
		int x = jdbcTemplate.update("INSERT INTO user_info VALUES('"+req.getUserName()+"','"+req.getTelephoneNumber()+"','"+req.getPassword()+"','"+req.getGender()+"')");
		System.out.println("Updated Rows "+x);
		
	}
}
