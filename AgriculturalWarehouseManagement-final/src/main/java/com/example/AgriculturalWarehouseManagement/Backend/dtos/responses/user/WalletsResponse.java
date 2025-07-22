package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.math.BigDecimal;

public class WalletsResponse {
    private int walletId;
    private int userId;
    private BigDecimal balance;

    public WalletsResponse(int walletId, int userId, BigDecimal balance) {
        this.walletId = walletId;
        this.userId = userId;
        this.balance = balance;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletsResponse{" +
                "walletId=" + walletId +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}
