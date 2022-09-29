package dao;

public interface CredentialsValidator {
    Boolean authenticate(String username, String password);
}