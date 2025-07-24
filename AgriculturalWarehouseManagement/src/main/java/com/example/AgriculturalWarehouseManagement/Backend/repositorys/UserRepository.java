package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhone(String phone);
    Optional<User> findByPhone(String phone);
    List<User> findByStatusIsNot(String status);
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByRoleIsNot(Role role);

    //
    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    User FindByEmail(@Param("email") String email);
    //

    @Query("SELECT count(u) from User u")
    long countAllUsers();

    @Modifying
    @Transactional
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updateUser(String email, String password);

    @Query(value = "SELECT * FROM user WHERE userID = ?1", nativeQuery = true)
    Optional<User> findByUserIdNative(int userId);

}
