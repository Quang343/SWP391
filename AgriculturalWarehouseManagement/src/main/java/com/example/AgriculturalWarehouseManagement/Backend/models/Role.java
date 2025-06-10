package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int roleID;

    @Column(name = "rolename", nullable = false, length = 100)
    private String roleName;

    @Column(name = "status", length = 20, nullable = false, columnDefinition = "varchar(20) default 'Active'")
    private String status = "Active";

    public static String ADMIN = "admin";
    public static String USER = "user";

    // Getters and Setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
