package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.ProfileRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WalletsUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WishlistServices;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
//@RestController
//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//        maxFileSize = 1024 * 1024 * 10, // 10MB
//        maxRequestSize = 1024 * 1024 * 50 // 50MB
//)
public class ProfileController {

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private WishlistServices wishlistServices;

    @Autowired
    private OrderUsersService orderUsersService;

    @Autowired
    private WalletsUsersService walletsUsersService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    CheckOutRequest checkOutRequestGlobal = new CheckOutRequest();
    int accountIdGlobal = 0;

    @GetMapping("profileUser")
    public String getProfileUser(Model model) {

        String token = (String) session.getAttribute("authToken");

        if (token == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            session.invalidate();
            return "redirect:/login";
        }

        Object account = session.getAttribute("account");
        if (account == null) {
            session.invalidate();
            return "redirect:/login";
        }


        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            if (session.getAttribute("errorUpdateProfile") != null) {
                String errorUpdateImage = (String) session.getAttribute("errorUpdateProfile");
                model.addAttribute("errorUpdateProfile", errorUpdateImage);
                session.removeAttribute("errorUpdateProfile");
            }

            if (session.getAttribute("errorUpdateImage") != null) {
                String errorUpdateImage = (String) session.getAttribute("errorUpdateImage");
                model.addAttribute("errorUpdateImage", errorUpdateImage);
                session.removeAttribute("errorUpdateImage");
            }

            if (session.getAttribute("errorChangePassword") != null) {
                String errorChangePassword = (String) session.getAttribute("errorChangePassword");
                model.addAttribute("errorChangePassword", errorChangePassword);
                session.removeAttribute("errorChangePassword");
            }

            if (session.getAttribute("successChangePassword") != null) {
                String successChangePassword = (String) session.getAttribute("successChangePassword");
                model.addAttribute("successChangePassword", successChangePassword);
                session.removeAttribute("successChangePassword");
            }

            // View Order
            List<OrderUserResponse> orderUserResponses = orderUsersService.getListOrdersOrEmpty(userEntity.getUserId());
            model.addAttribute("orderUserResponses", orderUserResponses);

            // Total Order
            model.addAttribute("totalOrder", orderUserResponses.size());

            // Total Wishlist
            List<ProductUserHomepageResponse> wishlistResponses = wishlistServices.getListOfWishlist(userEntity.getUserId());
            model.addAttribute("totalWishlist", wishlistResponses.size());

            WalletsResponse walletsResponse = walletsUsersService.getBalanceWallet(userEntity.getUserId());
            model.addAttribute("walletsResponse", walletsResponse);

            return "FrontEnd/Home/userDashboard";

        }

    }

    @PostMapping("/profileUserEdit")
    public String editProfileUser(@ModelAttribute ProfileRequest profileRequest) {

        if (profileRequest == null) {
            return "FrontEnd/Home/userDashboard";
        }


        System.out.println(profileRequest.toString());

        ResponseResult<ProfileResponse> result = userCustomerService.editProfileUser(profileRequest);

        if (result.isActive()) {
            User userEntity = userCustomerService.loadUserByEmail(profileRequest.getEmail());
            UserResponse userResponse = userCustomerService.getUser(userEntity);
            session.setAttribute("account", userResponse);
        } else {
            session.setAttribute("errorUpdateProfile", result.getMessage());
        }


        return "redirect:/profileUser";
    }

    @GetMapping("/profileUserEdit/image")
    public String editProfileUserImage() {
        return "FrontEnd/Home/userDashboard";
    }

    @PostMapping("/profileUserEdit/image")
    public String editProfileUserImage(@RequestParam("image") MultipartFile file,
                                       @Value("${app.upload.product-dir}" + "/User") String uploadDir,
                                       HttpSession session) throws IOException {
        UserResponse userResponseSession = (UserResponse) session.getAttribute("account");

        String imagePath = userResponseSession.getImageUrl(); // giữ ảnh cũ

        if (file != null && !file.isEmpty()) {
            Files.createDirectories(Paths.get(uploadDir)); // tạo thư mục nếu chưa có -> C:\Users/ADMIN/uploads/user,

            String fileName = System.currentTimeMillis() + "_" +
                    StringUtils.cleanPath(file.getOriginalFilename());

            // Đưa file vào uploadDir
            Path filePath = Paths.get(uploadDir, fileName);

            // Sao chép dữ liệu từ MultipartFile sang file mới tại đường dẫn vừa tạo
            file.transferTo(filePath.toFile());

            // lưu đường dẫn truy cập công khai (trình duyệt sẽ dùng đường dẫn này)
            imagePath = "/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/" + fileName;
        }

        ResponseResult<User> result = userCustomerService.editProfileUserImage(userResponseSession.getEmail(), imagePath, file);
        System.out.println("hello" + result.getStatus());
        if (result.isActive()) {
            User userEntity = userCustomerService.loadUserByEmail(userResponseSession.getEmail());
            UserResponse userResponse = userCustomerService.getUser(userEntity);
            session.setAttribute("account", userResponse);
            session.setAttribute("accountImage", userResponse.getImageUrl());
            return "redirect:/profileUser";
        } else {
            session.setAttribute("errorUpdateImage", result.getMessage());
            return "redirect:/profileUser";
        }
    }

    @PostMapping("/profileUserEdit/password")
    public String editProfileUserPassword(@RequestParam("oldPassword") String oldPassword,
                                          @RequestParam("newPassword") String newPassword) {
        UserResponse userResponseSession = (UserResponse) session.getAttribute("account");
        ResponseResult<User> checkOldPassword = userCustomerService.checkOldPassword(userResponseSession.getEmail(), oldPassword);
        if (!checkOldPassword.isActive()) {
            session.setAttribute("errorChangePassword", checkOldPassword.getMessage());
            return "redirect:/profileUser";
        } else {
            ResponseResult<User> result = userCustomerService.changePassword(userResponseSession.getEmail(), newPassword);
            if (result.isActive()) {
                session.setAttribute("successChangePassword", result.getMessage());
                return "redirect:/profileUser";
            } else {
                session.setAttribute("errorChangePassword", result.getMessage());
                return "redirect:/profileUser";
            }

        }
    }

    @PostMapping("/cancellOrder")
    public String cancelOrder(@RequestParam("orderId")  String orderId) {

        String token = (String) session.getAttribute("authToken");

        if (token == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {
//            System.out.println("hello" + orderId);
            ResponseResult<OrderUserResponse> orderUserResponseResponseResult = orderUsersService.cancelOrder((String) orderId, userEntity.getUserId());
            return "redirect:/profileUser";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/depositWalletProfile", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deposit(@RequestParam(name = "totalPrice", defaultValue = "0", required = false) double totalPrice,
                          HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        CheckOutRequest checkOutRequest = new CheckOutRequest();
        checkOutRequest.setTotalPrice(totalPrice);
        session.setAttribute("checkOutRequestDepositProfile", checkOutRequest);
        checkOutRequestGlobal = checkOutRequest;

        Object accountIdObj = session.getAttribute("accountId");
        accountIdGlobal = (int) accountIdObj;

        request.getRequestDispatcher("/createPaymentWalletsProfileLink").forward(request, response);
        return "forward:/home";
    }

    @GetMapping("/updateWalletsDepositProfile")
    public String updateWalletDeposit() {
        if (checkOutRequestGlobal.getTotalPrice() != 0.00 && accountIdGlobal != 0) {
            System.out.println("hello"+checkOutRequestGlobal.getTotalPrice());
            walletsUsersService.updateWalletsDeposit(accountIdGlobal, checkOutRequestGlobal);

            accountIdGlobal = 0;
            checkOutRequestGlobal.setTotalPrice(0.00);
            return "redirect:/successWalletProfile";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/successWalletProfile")
    public String successWalletProfile() {
        return "FrontEnd/Home/walletProfile";
    }

    @GetMapping("/cancelWalletProfile")
    public String cancellWalletProfile() {
        return "redirect:/profileUser";
    }




}
