package utils;

public class AuthTokenManager {

    // Static variable to store the authentication token
    private static String authToken;

    // set the token
    public static void setAuthToken(String token) {
        authToken = token;
    }

    // get the token
    public static String getAuthToken() {
        return authToken;
    }
}
