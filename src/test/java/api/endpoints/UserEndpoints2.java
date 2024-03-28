package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;

public class UserEndpoints2 {

	//method create to generate URL from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
		return routes;
	}
	
	public static Response createUser(User Payload)
	{
		String post_url=	getURL().getString("post_url");

		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Payload)
				.when()
				.post(post_url);
		
		return response;
		
	}
	
	
	public static Response ReadUser(String Username)
	{
	
	String get_url=	getURL().getString("get_url");
		Response response=given()
				.pathParam("username", Username)
				.when()
				.get(get_url);
		
		return response;
		
	}
	
	public static Response UpdateUser(String Username, User Payload)
	{
		String update_url=	getURL().getString("update_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", Username)
				.body(Payload)
				.when()
				.put(update_url);
		
		return response;
		
	}
	
	public static Response DeleteUser(String Username)
	{
		String delete_url=	getURL().getString("delete_url");

		Response response=given()
				.pathParam("username", Username)
				.when()
				.delete(delete_url);
		
		return response;
		
	}
}
