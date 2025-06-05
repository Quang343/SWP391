Main là nhánh code chính của dự án. Mọi thay đổi vào nhánh này cần phải được xem xét kỹ để tránh ảnh hưởng đến toàn bộ hệ thống.

🚫 Trước khi thống nhất code với nhau:
Không push trực tiếp lên main bằng lệnh: git push.

✅ Nên làm:
Push code lên nhánh riêng: git push origin [ten-nhanh-moi]
Xoá 1 nhánh bất kỳ (trừ main): git push origin --delete [ten-nhanh]
