package user.validation.mapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import user.validation.bo.UserInfo;

public class MyResource {

	
	 	@POST
	    @Path("/validate")
	 	@Consumes({"application/json", "application/xml"}) 
	 	@Produces({"application/json", "application/xml"})
	    public Response validateUser(UserInfo Req) {
	 		System.out.println("Request recieved by "+Req.getUserName());
	        return null;
	    }
}
