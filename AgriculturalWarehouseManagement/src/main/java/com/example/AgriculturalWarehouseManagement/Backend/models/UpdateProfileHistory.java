package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "updateprofilehistory")
public class UpdateProfileHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historyid")
    private int historyID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    private User user;

    @Column(name = "updateinfo", length = 1000)
    private String updateInfo;

    @Column(name = "updatetime", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    public void prePersist() {
        if (updateTime == null) {
            updateTime = new Date();
        }
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
