package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.MyAddressBook;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<MyAddressBook, Long> {

    List<MyAddressBook> findByUser(User user);

//    MyAddressBook findById();

    @Override
    void deleteById(Long id);

    @Override
    Optional<MyAddressBook> findById(Long aLong);
}
