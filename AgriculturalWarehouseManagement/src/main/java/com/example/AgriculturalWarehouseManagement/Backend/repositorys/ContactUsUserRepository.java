package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.ContactUsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactUsUserRepository extends JpaRepository<ContactUsUser, Long> {
    void deleteContactUsUserByContactusid(int id);

    Optional<ContactUsUser> findByContactusid(int contactusid);
}
