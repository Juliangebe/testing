package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class APITest {

@BeforeClass
    public void setUp(){
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
}
    @Test
    public void testGetPosts() {
        Response response = RestAssured.get("/posts");

        // Validate the response status code and content
        response.then().statusCode(200);
        response.then().log().body();
    }

    @Test
    public void testCreatePost() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New Post");
        requestParams.put("body", "This is the body of my new post");
        requestParams.put("userId", 1);

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestParams.toString())
                .when()
                .post("/posts");

        response.then().statusCode(201);
        response.then().log().body();
    }
    @Test
    public void testUpdatePost() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", 1);
        requestParams.put("title", "Updated Title");
        requestParams.put("body", "Updated body content");
        requestParams.put("userId", 1);

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestParams.toString())
                .when()
                .put("/posts/1");

        response.then().statusCode(200);
        response.then().log().body();
    }

    @Test
    public void testDeletePost() {
        Response response = RestAssured.delete("/posts/1");

        response.then().statusCode(200);
        response.then().log().body();
    }

    @Test
    public void testCreateAndUpdatePost() {
        // Create a new post
        JSONObject createParams = new JSONObject();
        createParams.put("title", "New Post");
        createParams.put("body", "This is the body of my new post");
        createParams.put("userId", 1);

        Response createResponse = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(createParams.toString())
                .when()
                .post("/posts");

        int postId = createResponse.jsonPath().getInt("id");

        // Update the created post
        JSONObject updateParams = new JSONObject();
        updateParams.put("id", postId);
        updateParams.put("title", "Updated Title");
        updateParams.put("body", "Updated body content");
        updateParams.put("userId", 1);

        Response updateResponse = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updateParams.toString())
                .when()
                .put("/posts/" + postId);

        updateResponse.then().statusCode(200);
        updateResponse.then().log().body();
    }


}
