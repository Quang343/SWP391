| **Nhóm lệnh**           | **Lệnh Git**                  | **Giải thích**                                                               |
| ----------------------- | ----------------------------- | ---------------------------------------------------------------------------- |
| **Khởi tạo & Nhân bản** | `git init`                    | Khởi tạo một repository Git mới trong thư mục hiện tại                       |
|                         | `git clone <repository>`      | Tải về (nhân bản) một repository từ remote về máy local                      |
| **Kiểm tra trạng thái** | `git status`                  | Hiển thị trạng thái các file (đã sửa, đã stage, chưa commit,...)             |
| **Staging & Commit**    | `git add <file>`              | Thêm file chỉ định vào vùng staging                                          |
|                         | `git add .`                   | Thêm tất cả thay đổi vào vùng staging                                        |
|                         | `git commit -m "Message"`     | Lưu các thay đổi đã stage cùng với thông điệp mô tả                          |
| **Làm việc với nhánh**  | `git branch`                  | Liệt kê các branch hiện có                                                   |
|                         | `git branch <name>`           | Tạo một branch mới với tên chỉ định                                          |
|                         | `git checkout <branch>`       | Chuyển sang branch chỉ định                                                  |
|                         | `git checkout -b <branch>`    | Tạo mới và chuyển sang branch mới                                            |
| **Push & Pull**         | `git pull <remote> <branch>`  | Lấy và hợp nhất thay đổi từ remote về branch hiện tại                        |
|                         | `git push <remote> <branch>`  | Đẩy các commit từ branch hiện tại lên remote repository                      |
| **Merge & Rebase**      | `git merge <branch>`          | Gộp một branch vào branch hiện tại                                           |
|                         | `git rebase <branch>`         | Chuyển lại lịch sử commit của branch hiện tại trên branch chỉ định           |
| **Kiểm tra lịch sử**    | `git log`                     | Hiển thị lịch sử các commit                                                  |
|                         | `git diff <file>`             | So sánh sự thay đổi của file so với lần commit gần nhất                      |
| **Stash & Advanced**    | `git stash`                   | Cất tạm thời các thay đổi chưa commit                                        |
|                         | `git cherry-pick <commit>`    | Áp dụng một commit cụ thể vào branch hiện tại                                |
|                         | `git bisect`                  | Tìm commit gây ra bug bằng cách chia đôi lịch sử commit                      |
| **Hoàn tác**            | `git reset --hard HEAD~1`     | Đưa repo về trạng thái commit trước đó, xóa toàn bộ thay đổi kể từ commit đó |
|                         | `git revert <commit>`         | Tạo commit mới đảo ngược thay đổi của commit chỉ định                        |
| **Remote**              | `git remote -v`               | Liệt kê các remote repository đang liên kết                                  |
|                         | `git remote add <name> <url>` | Thêm một remote repository mới với tên và đường dẫn chỉ định                 |
