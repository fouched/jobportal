package com.luv2code.jobportal.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users_type")
public class UsersType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usersTypeId;

    private String usersTypeName;

    @OneToMany(targetEntity = Users.class, mappedBy = "usersTypeId", cascade = CascadeType.ALL)
    private List<Users> users;

    public UsersType() {
    }

    public UsersType(int usersTypeId, String usersTypeName, List<Users> users) {
        this.usersTypeId = usersTypeId;
        this.usersTypeName = usersTypeName;
        this.users = users;
    }

    public int getUsersTypeId() {
        return usersTypeId;
    }

    public void setUsersTypeId(int usersTypeId) {
        this.usersTypeId = usersTypeId;
    }

    public String getUsersTypeName() {
        return usersTypeName;
    }

    public void setUsersTypeName(String usersTypeName) {
        this.usersTypeName = usersTypeName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersType{" +
                "usersTypeId=" + usersTypeId +
                ", usersTypeName='" + usersTypeName + '\'' +
                '}';
    }
}
