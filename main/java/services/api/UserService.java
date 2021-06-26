package services.api;

public interface UserService {
    public void addUser(String userName, String email, String passwordHash, String passwordSalt, String type);
    public void updateUserInfo(String... args);
    public void logUserIn(String userName, String password);
    public void logUserOut();
}
