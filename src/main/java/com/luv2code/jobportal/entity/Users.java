package com.luv2code.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "users")
public class Users {

    //TODO why deviate from using id like in most of the other tables?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty
    private String password;

    private boolean isActive;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    // Below No need for referencedColumnName - the default joins to the primary key
    // @JoinColumn(name = "user_type_id", referencedColumnName = "userTypeId")
    // see: https://codingnomads.com/spring-data-jpa-joincolumn-configuration
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_type_id")
    private UsersType userTypeId;

    public Users() {}

    public Users(int userId, String email, String password, boolean isActive, Date registrationDate, UsersType usersTypeId) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.registrationDate = registrationDate;
        this.userTypeId = usersTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UsersType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UsersType usersType) {
        this.userTypeId = usersType;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", registrationDate=" + registrationDate +
                ", usersType=" + userTypeId +
                '}';
    }
}
