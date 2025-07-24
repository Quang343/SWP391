package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WalletsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.models.Wallets;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WalletsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletsUsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletsRepository walletsRepository;

    public ResponseResult<WalletsResponse> checkWalletUser(int userID, CheckOutRequest checkOutRequest) {
        Optional<User> userOpt = userRepository.findByUserIdNative(userID);

        if (userOpt.isEmpty()) {
            return new ResponseResult<>("ERROR", "Người dùng ko tồn tại", false);
        }

        Optional<Wallets> walletsOpt = walletsRepository.findByUser(userOpt.get());

        if (walletsOpt.isEmpty()) {
            return new ResponseResult<>("ERROR", "Ví tiền ko tồn tại", false);
        }

        Wallets wallet = walletsOpt.get();
        BigDecimal currentBalance = wallet.getBalance();
        BigDecimal totalPrice = BigDecimal.valueOf(checkOutRequest.getTotalPrice()); // totalPrice là số cần thanh toán

        if (currentBalance.compareTo(totalPrice) < 0) {
            return new ResponseResult<>("ERROR", "Số dư trong ví không đủ để thanh toán", false);
        }

        return new ResponseResult<>("SUCCESS", "", true);
    }

    public void updateWalletsDeposit(int accountId, CheckOutRequest checkOutRequestDeposit) {
        Optional<User> userOpt = userRepository.findByUserIdNative(accountId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Optional<Wallets> walletsOpt = walletsRepository.findByUser(user);

            if (walletsOpt.isPresent()) {
                Wallets wallet = walletsOpt.get();

                BigDecimal currentBalance = wallet.getBalance();
                BigDecimal depositAmount = BigDecimal.valueOf(checkOutRequestDeposit.getTotalPrice()); // đảm bảo kiểu BigDecimal

                BigDecimal updatedBalance = currentBalance.add(depositAmount);
                wallet.setBalance(updatedBalance);

                walletsRepository.save(wallet);
            } else {
                System.out.println("Ví không tồn tại cho user ID: " + accountId);
            }
        } else {
            System.out.println("User không tồn tại với ID: " + accountId);
        }
    }

    public void updateWalletAfterOrder(int accountId, CheckOutRequest checkOutRequestDeposit) {
        Optional<User> userOpt = userRepository.findByUserIdNative(accountId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Optional<Wallets> walletsOpt = walletsRepository.findByUser(user);

            if (walletsOpt.isPresent()) {
                Wallets wallet = walletsOpt.get();

                BigDecimal currentBalance = wallet.getBalance();
                BigDecimal depositAmount = BigDecimal.valueOf(checkOutRequestDeposit.getTotalPrice());

                BigDecimal newBalance = currentBalance.subtract(depositAmount);
                wallet.setBalance(newBalance);

                walletsRepository.save(wallet);
            } else {
                System.out.println("Ví không tồn tại cho user ID: " + accountId);
            }
        } else {
            System.out.println("User không tồn tại với ID: " + accountId);
        }
    }

    public WalletsResponse getBalanceWallet(int accountId) {
        Optional<User> userOpt = userRepository.findByUserIdNative(accountId);
        if (userOpt.isPresent()) {
            Optional<Wallets> walletsOpt = walletsRepository.findByUser(userOpt.get());
            if (walletsOpt.isPresent()) {
                Wallets wallet = walletsOpt.get();

                WalletsResponse walletsResponse = new WalletsResponse(Math.toIntExact(wallet.getId()),  accountId,wallet.getBalance());

                return walletsResponse;
            } else {
                return null;
            }

        } else {
            return new WalletsResponse(Math.toIntExact(accountId),  accountId,BigDecimal.ZERO);
        }

    }
}
