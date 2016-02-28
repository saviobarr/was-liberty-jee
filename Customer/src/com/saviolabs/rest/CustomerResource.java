package com.saviolabs.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.saviolabs.bo.Customer;
import com.saviolabs.ejb.CustomerEJB;

@Resource
@Path("/customer")
public class CustomerResource {

	@EJB
	private CustomerEJB customerEJB;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(Customer customer){
		
		try{
			customerEJB.insert(customer);
			
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.CREATED).entity(customer).build();

	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response list(){
		try{
			List<Customer> customerList = customerEJB.listAll();
			GenericEntity<List<Customer>> entity = new GenericEntity<List<Customer>>(customerList) {};
			Response response = Response.ok(entity).build();
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
