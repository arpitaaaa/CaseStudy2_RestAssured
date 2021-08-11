package Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class caseStudy {

	public static String baseUrl = "http://rest-api.upskills.in";
	public String accesstoken ;

	@Test (priority =0) // POST - get Access Token
	public void getAccessToken() {
		RestAssured.baseURI = baseUrl;
		Response response = given()
				.header("Authorization" , "Basic dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9jbGllbnQ6dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9zZWNyZXQ=")
				.when()
				.post("/api/rest_admin/oauth2/token/client_credentials")
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();

		String jsonresp = response.asString();
		JsonPath responseBody = new JsonPath(jsonresp);
		accesstoken = responseBody.get("data.access_token");
		System.out.println("Response accessToken : "+accesstoken);
		System.out.println("Test Case 1 Success");

	}

	@Test (priority =1) // POST - login
	public void login() {
		RestAssured.baseURI = baseUrl;

		String requestBody = "{\r\n" + 
				"  \"username\": \"upskills_admin\",\r\n" + 
				"  \"password\": \"Talent4$$\"\r\n" + 
				"}";
		//System.out.println("accesstoken: "+accesstoken);

		Response response = given()
				.header("Authorization" , "bearer "+accesstoken)
				.header("content-Type", "application/json")
				.body(requestBody)
				.when()
				.post("/api/rest_admin/login")
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();

		String jsonresp = response.asString();
		JsonPath responseBody = new JsonPath(jsonresp);
		String userName = responseBody.get("data.username");
		System.out.println("Logged in as : "+userName);

	}

	@Test (priority =2, enabled = false) // GET - User Details
	public void getUserDetails() {
		RestAssured.baseURI = baseUrl;

		Response response = given()
				.header("Authorization" , "bearer "+accesstoken)
				.when()
				.get("/api/rest_admin/user")
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();

		String jsonresp = response.asString();
		JsonPath responseBody = new JsonPath(jsonresp);
		String userName = responseBody.get("data.username");
		System.out.println("UserName is : "+userName);
	}

	@Test (priority =5) // post - Logout
	public void logOut() {
		RestAssured.baseURI = baseUrl;

		given()
		.header("Authorization" , "bearer "+accesstoken)
		.when()
		.post("/api/rest_admin/logout")
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON);
		System.out.println("User logged out");

	}

	@Test (priority =3, enabled =false) // post - addCustomer
	public void addCustomer() {
		RestAssured.baseURI = baseUrl;

		String requestBody = "{\r\n" + 
				"  \"firstname\": \"Demo81\",\r\n" + 
				"  \"lastname\": \"User81\",\r\n" + 
				"  \"email\": \"nash1@tes8t..hu\",\r\n" + 
				"  \"password\": \"password81\",\r\n" + 
				"  \"confirm\": \"password81\",\r\n" + 
				"  \"telephone\": \"1-541-754-3010\",\r\n" + 
				"  \"fax\": \"1-541-754-3010\",\r\n" + 
				"  \"newsletter\": \"1\",\r\n" + 
				"  \"status\": \"1\",\r\n" + 
				"  \"approved\": \"1\",\r\n" + 
				"  \"safe\": \"1\",\r\n" + 
				"  \"customer_group_id\": \"1\",\r\n" + 
				"  \"custom_field\": {\r\n" + 
				"    \"1\": \"1985-02-01\",\r\n" + 
				"    \"2\": \"2\"\r\n" + 
				"  },\r\n" + 
				"  \"address\": [\r\n" + 
				"    {\r\n" + 
				"      \"firstname\": \"Demo81\",\r\n" + 
				"      \"lastname\": \"User81\",\r\n" + 
				"      \"company\": \"XYZ LTD.\",\r\n" + 
				"      \"city\": \"Agra\",\r\n" + 
				"      \"address_1\": \"Demo street\",\r\n" + 
				"      \"country_id\": \"81\",\r\n" + 
				"      \"postcode\": \"1111\",\r\n" + 
				"      \"zone_id\": \"1256\",\r\n" + 
				"      \"address_2\": \"Long street 33\",\r\n" + 
				"      \"address_id\": \"10\",\r\n" + 
				"      \"default\": \"1\",\r\n" + 
				"      \"custom_field\": {\r\n" + 
				"        \"3\": \"https://rest-api.upskills.in\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"affiliate\": {\r\n" + 
				"    \"company\": \"company\",\r\n" + 
				"    \"website\": \"http://demo.com\",\r\n" + 
				"    \"tracking\": \"Tracking nutb89e91\",\r\n" + 
				"    \"commission\": \"3\",\r\n" + 
				"    \"tax\": \"99998888\",\r\n" + 
				"    \"cheque\": \"Demo\",\r\n" + 
				"    \"paypal\": \"demo@demo.com\",\r\n" + 
				"    \"bank_name\": \"Bank Name\",\r\n" + 
				"    \"bank_branch_number\": \"ABA/BSB number (Branch Number)\",\r\n" + 
				"    \"bank_swift_code\": \"SWIFT Code\",\r\n" + 
				"    \"bank_account_name\": \"Account Name\",\r\n" + 
				"    \"bank_account_number\": \"Account Number\",\r\n" + 
				"    \"status\": \"1\",\r\n" + 
				"    \"payment\": \"paypal\",\r\n" + 
				"    \"custom_field\": {\r\n" + 
				"      \"1\": \"custom field\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";


		Response response = given()
				.header("Authorization" , "bearer "+accesstoken)
				.header("content-Type", "application/json")
				.body(requestBody)
				.when()
				.post("/api/rest_admin/customers")
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();

		String jsonresp = response.asString();
		JsonPath responseBody = new JsonPath(jsonresp);
		int userId =  responseBody.get("data.id");
		System.out.println("UserId is : "+userId);
	}

	@Test (priority =4) // GET - List of Customer
	public void listOfCustomer() {
		RestAssured.baseURI = baseUrl;

		Response response = given()
				.header("Authorization" , "bearer "+accesstoken)
				.pathParam("limit", 6)
				.pathParam("page",1)
				.when()
				.get("/api/rest_admin/customergroups/limit/{limit}/page/{page}")
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();

		String jsonresp = response.asString();
		JsonPath responseBody = new JsonPath(jsonresp);
		String userName = responseBody.get("data.username");
		System.out.println("UserName is : "+userName);
	}

}
