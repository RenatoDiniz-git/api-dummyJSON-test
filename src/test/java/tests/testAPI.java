package tests;

import config.ApiConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.AuthTokenManager;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testAPI {

    private static int productId;
    private static String testUsername;
    private static String testPassword;

    @Step("Getting a user from the response")
    private void selectUserFromResponse(Response response) {
        testUsername = response.jsonPath().getString("users[0].username");
        testPassword = response.jsonPath().getString("users[0].password");
    }

    private Response sendGetRequest(String endpoint) {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .when()
                .get(endpoint);
    }

    private Response sendPostRequest(String endpoint, String body) {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .body(body)
                .when()
                .post(endpoint);
    }

    private Response sendAuthenticatedGetRequest(String endpoint) {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .header("Authorization", "Bearer " + AuthTokenManager.getAuthToken())
                .when()
                .get(endpoint);
    }

    //@BeforeAll
    @Test
    @Order(0)
    @Feature("User")
    @Description("Get all user")
    public void testGetUsers() {
        Response response = given()
                .spec(ApiConfig.getRequestSpecification())
                .when()
                .get("/users");

        response.then().statusCode(200);
        Assertions.assertTrue(response.getBody().asString().contains("users"));

        // Get first user on response
        testUsername = response.jsonPath().getString("users[0].username");
        testPassword = response.jsonPath().getString("users[0].password");

        new testAPI().selectUserFromResponse(response);
    }

    @Test
    @Feature("User")
    @Description("Testing login with user from testGetUsers")
    public void testLogin() {
        Assertions.assertNotNull(testUsername, "testUsername not defined. Run 'testGetUsers' first.");
        Assertions.assertNotNull(testPassword, "testPassword not defined. Run 'testGetUsers' first.");

        String loginPayload = String.format("{ \"username\": \"%s\", \"password\": \"%s\" }", testUsername, testPassword);

        Response response = sendPostRequest("/auth/login", loginPayload);
        response.then().statusCode(200);

        String token = response.jsonPath().getString("accessToken");
        AuthTokenManager.setAuthToken(token);
        System.out.println(loginPayload);
    }

    @Test
    @Feature("Products")
    @Description("Check Auth product response 200 ok")
    public void testGetAuthProducts() {
        Response response = sendAuthenticatedGetRequest("/auth/products");

        response.then().statusCode(200);
        Assertions.assertTrue(response.getBody().asString().contains("products"));
    }

    @Test
    @Feature("Products")
    @Description("Adding a product")
    public void testAddProduct() {
        String productPayload = """
        {
            "title": "Perfume Oil",
            "description": "Mega Discount, Impression of A...",
            "price": 13,
            "discountPercentage": 8.4,
            "rating": 4.26,
            "stock": 65,
            "brand": "Impression of Acqua Di Gio",
            "category": "fragrances",
            "thumbnail": "https://i.dummyjson.com/data/products/11/thumnail.jpg"
        }
        """;

        Response response = sendPostRequest("/products/add", productPayload);

        response.then().statusCode(201);
        Assertions.assertTrue(response.getBody().asString().contains("Perfume Oil"));
    }

    @Test
    @Order(1)
    @Feature("Products")
    @Description("Get all products and store a product ID")
    public void allProducts() {
        Response response = sendGetRequest("/products");

        response.then().statusCode(200);
        productId = response.jsonPath().getInt("products[0].id");
        Allure.step("Chosen product: " + productId);
    }

    @Test
    @Order(2)
    @Feature("Products")
    @Description("Get a specific product by ID")
    public void testProductWithId() {
        Assertions.assertNotEquals(0, productId, "productId not defined. Run 'allProducts' first.");

        Response response = sendGetRequest("/products/" + productId);

        response.then().statusCode(200);
        Allure.step("Product access successfully.");
    }

    @Test
    @Feature("Products")
    @Description("Try to get a product using an invalid token")
    public void testGetProductWithInvalidToken() {
        String invalidToken = "invalid_token_123";

        Response response = given()
                .spec(ApiConfig.getRequestSpecification())
                .header("Authorization", "Bearer " + invalidToken)
                .when()
                .get("/auth/products");

        response.then().statusCode(401);
        Allure.step("Check if returns status 401 Unauthorized.");
    }

    @Test
    @Feature("Products")
    @Description("Try to get a product that does not exist")
    public void testGetNonExistentProduct() {
        int nonExistentProductId = 999999; // Non Existent product

        Response response = sendGetRequest("/products/" + nonExistentProductId);

        response.then().statusCode(404);
        Allure.step("Check nonexistent product returns status 404 Not Found.");
    }

    @Test
    @Feature("User")
    @Description("Check if a nonexistent user return 400 when try login")
    public void testNonExistentUserLogin() {
        String invalidLoginPayload = "{ \"username\": \"invalidUser\", \"password\": \"wrongPassword\" }";
        Response response = given()
                .spec(ApiConfig.getRequestSpecification())
                .body(invalidLoginPayload)
                .when()
                .post("/auth/login");


        response.then().statusCode(400); // checking return 401 (Unauthorized)
        Assertions.assertTrue(response.getBody().asString().contains("Invalid credentials"),
                "Expected error message in the response not return");
    }
}
