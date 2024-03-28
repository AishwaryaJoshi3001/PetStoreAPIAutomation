package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payload.User;

public class UserEndpoints {

	public static Response createUser(User Payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Payload)
				.when()
				.post(Routes.post_url);
		
		return response;
		
	}
	
	public static Response ReadUser(String Username)
	{
		Response response=given()
				.pathParam("username", Username)
				.when()
				.get(Routes.get_url);
		
		return response;
		
	}
	
	public static Response UpdateUser(String Username, User Payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", Username)
				.body(Payload)
				.when()
				.put(Routes.update_url);
		
		return response;
		
	}
	
	public static Response DeleteUser(String Username)
	{
		Response response=given()
				.pathParam("username", Username)
				.when()
				.delete(Routes.delete_url);
		
		return response;
		
	}
}
