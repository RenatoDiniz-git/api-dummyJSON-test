# API Test Automation Project

## 📌 Project Overview

This project is an API test automation framework built using **Java**, **RestAssured**, and **JUnit 5**. It is designed to validate the functionality of an API (DummyJSON - https://dummyjson.com/) that manages electronic products. The automation covers authentication, product retrieval, and API response validation.

## 🚀 How to Run the Tests

### Prerequisites

Make sure you have the following requirements installed:

- **Java 17**
- **Maven**
- **Git**

### Execution Steps

1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd api-test-project
   ```
2. Run the tests with Maven:
   ```sh
   mvn test
   ```
3. Generate Allure reports (if enabled):
   ```sh
   mvn allure:report
   ```
4. View the Allure report in the browser:
   ```sh
   mvn allure:serve
   ```

## 📝 Test Plan

### Scope

The test plan includes:

- Authentication tests (login validation)
- Product retrieval tests (get all products, get product by ID)
- API availability tests
- Validation of endpoints protected by authorization

### Test Scenarios

| Test Case                   | Description                                          | Expected Result                         |
| --------------------------- | ---------------------------------------------------- | --------------------------------------- |
| API Health Check            | Verify if the API is operational                    | Returns 200 OK                          |
| User Authentication         | Validate login with correct credentials             | Returns access token                    |
| Retrieve All Products       | Fetch all products from the API                     | Returns a list of products              |
| Get Product by ID           | Fetch a specific product by ID                      | Returns the correct product details     |
| Unauthorized Access         | Try to access protected endpoints without authentication | Returns 400                             |
| Non-Existent Product        | Try to access a product endpoint with an invalid ID | Returns 400                             |

## ✅ Tests Performed

The following test cases have been implemented and executed:

- **API Health Check** (validation of the `/test` endpoint)
- **Login Test** (ensuring authentication works correctly)
- **Fetch All Products** (validation of the `/products` endpoint)
- **Fetch Specific Product by ID** (using a dynamic ID)
- **Product Addition Test** (verifying product creation via API)
- **Authorization Validation** (ensuring protected endpoints require authentication)

### Users
- **Fetch all users** (`testGetUsers`)
- **Login with an API user** (`testLogin`)
- **Attempt login with a non-existent user** (`testNonExistentUserLogin`)

### Products
- **Fetch all products and store an ID** (`allProducts`)
- **Fetch a specific product by ID** (`testProductWithId`)
- **Add a product** (`testAddProduct`)
- **Fetch products while authenticated** (`testGetAuthProducts`)
- **Attempt to fetch a product with an invalid token** (`testGetProductWithInvalidToken`)
- **Attempt to fetch a non-existent product** (`testGetNonExistentProduct`)

### Test Execution Summary

- All tests were successfully executed ✅
- Allure reports available at `target/site/allure-maven-plugin/`

---

