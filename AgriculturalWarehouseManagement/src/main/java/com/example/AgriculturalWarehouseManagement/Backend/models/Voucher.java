package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voucher_code", unique = true, nullable = false, length = 50)
    private String voucherCode;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "discount_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Column(name = "discount_value", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountValue;

    private Long quantity;

    @Column(name = "used_quantity")
    private Long usedQuantity;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private VoucherStatus status;

    @Column(name = "min_order_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal minOrderAmount;

    @Column(name = "islocked", nullable = false)
    private Boolean isLocked = false;

    public String getDiscountValueString() {
        if (discountType.equals(DiscountType.PERCENT))
            return String.format("%.0f%%", discountValue);
        else{
            return String.format("%s VND", convertBigDecimalToStringFormatted(discountValue));
        }
    }

    public String getMinOrderAmountString() {
        return String.format("%s VND", convertBigDecimalToStringFormatted(minOrderAmount));
    }

    private String convertBigDecimalToStringFormatted(BigDecimal value) {
        String str = String.valueOf(value.intValue());
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for(int i = str.length() - 1; i >= 0; i--){
            count++;
            builder.append(str.charAt(i));
            if(count == 3 && i != 0){
                builder.append(',');
                count = 0;
            }
        }
        return builder.reverse().toString();
    }
}
