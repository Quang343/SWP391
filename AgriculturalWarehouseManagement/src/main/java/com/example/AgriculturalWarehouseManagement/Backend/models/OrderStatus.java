package com.example.AgriculturalWarehouseManagement.Backend.models;

/**
 * Trạng thái đơn hàng chi tiết:
 * - PENDING: Người dùng vừa đặt, chờ xác nhận.
 * - CONFIRMED: Seller/Admin xác nhận đơn.
 * - CANCELLED: Bị huỷ (khi chưa xuất kho).
 * - STOCKOUT: Warehouse đã xuất kho.
 * - DELIVERED: Shipper đã giao đến người nhận.
 * - COMPLETED: Người dùng xác nhận đã nhận hàng thành công.
 */
public enum OrderStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    STOCKOUT,
    DELIVERED,
    COMPLETED
}
