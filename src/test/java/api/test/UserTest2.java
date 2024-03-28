package api.test;

import static org.testng.Assert.assertEquals;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	User userPayload;
	

	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode()); // generate dynamic id
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	
	}
	@Test(priority=1)
	public void testpostUser()
	{
		
	Response response=UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}

@Test(priority=2)
	public void testGetUser()
	{
	
	Response response=UserEndpoints2.ReadUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		

	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		//update existing data, below steps will regenerate data for first name last name.
	//	logger.info("***************User update***********");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
	Response response=UserEndpoints2.UpdateUser(this.userPayload.getUsername(), userPayload);
	
	response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		//checking data 
		
		Response responseAfterUpdate=UserEndpoints2.ReadUser(this.userPayload.getUsername());
		//logger.info("***************User updated***********");

		responseAfterUpdate.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
	//	logger.info("***************User Deletion***********");

	Response response=UserEndpoints2.ReadUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	//	logger.info("***************User Deleted***********");

	}
	
}
