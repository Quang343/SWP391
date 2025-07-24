package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Wishlist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepository extends CrudRepository<Wishlist, Long> {

    @Query(value = """
            SELECT DISTINCT  UserID, productid
            FROM  managerwarehouse.wishlist
            """, nativeQuery = true)
    List<Object[]> listofWishlists();


    @Query(value = """
                SELECT * FROM managerwarehouse.wishlist;
            """, nativeQuery = true)
    List<Object[]> allWishlists();

    @Override
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM managerwarehouse.wishlist WHERE UserID = :userID", nativeQuery = true)
    int deleteByUserId(@Param("userID") int userID);   // trả về số row xoá

}
