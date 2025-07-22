package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.models.VoucherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    List<Voucher> findByStatus(VoucherStatus status);
    boolean existsByVoucherCode(String voucherCode);

    @Query(value = """
                    SELECT\s
                        v.*,
                        (v.quantity - v.used_quantity) AS remaining_quantity,
                        DATEDIFF(v.end_date, CURRENT_DATE) AS days_remaining
                    FROM voucher v
                    WHERE\s
                        v.islocked = 0\s
                        AND v.status = 'ACTIVE'
                        AND (v.quantity - v.used_quantity) > 0  -- Kiểm tra số lượng còn lại
                        AND DATEDIFF(v.end_date, CURRENT_DATE) > 0  -- Kiểm tra ngày hết hạn còn lại
                        AND DATEDIFF(CURRENT_DATE, v.start_date) >= 0;  -- Kiểm tra ngày bắt đầu đã qua
            """, nativeQuery = true)
    List<Object[]> rawGetVouchers();
}
