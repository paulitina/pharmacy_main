package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User{
    @Id
    @SequenceGenerator(name="my_seq",sequenceName="my_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
    @Column(name = "user_id")
    Long userId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "email")
    String email;

    @Column(name = "password_hash")
    String passwordHash;

    @Column(name = "password_salt")
    String passwordSalt;

    @Column(name = "type")
    String type;

    public User() {
    }

    public User(String userName, String email, String passwordHash, String passwordSalt, String type) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
