package user.validation.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import user.validation.bo.UserInfo;
public class DataMapperTest {

	
	//@Test
	public void mapRequestTestForMandatoryFieldsValidation()
	{
		CamelContext ctx = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(ctx);
		UserInfo request = new UserInfo();
		exchange.getIn().setBody(request);
		try {
		new DataMapper().mapRequest(exchange);
		}
		catch(CustomException e)
		{
			assertEquals("Bad Request",e.getTitle());
			assertEquals("mandatory field(s) missing",e.getDetails());
			assertEquals(400,e.getStatus());
		}
	}
	@Test
	public void mapRequestTestForSuccess() throws CustomException, SQLException
	{
		CamelContext ctx = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(ctx);
		DataMapper mapper = new DataMapper();
		
		UserInfo req = mock(UserInfo.class);
		when(req.getGender()).thenReturn("male");
		when(req.getPassword()).thenReturn("abc");
		when(req.getTelephoneNumber()).thenReturn("123");
		when(req.getUserName()).thenReturn("user1");
		
		JdbcTemplate template = mock(JdbcTemplate.class);
		when(template.update(anyString())).thenReturn(1);
		
		DataSource ds = mock(DataSource.class);
		Connection conn = mock(Connection.class);
		Statement stmt = mock(Statement.class);
		when(ds.getConnection()).thenReturn(conn);
		when(conn.createStatement()).thenReturn(stmt);
		mapper.setDataSource(ds);
		
		
		exchange.getIn().setBody(req);
		mapper.mapRequest(exchange);
		}
	}
