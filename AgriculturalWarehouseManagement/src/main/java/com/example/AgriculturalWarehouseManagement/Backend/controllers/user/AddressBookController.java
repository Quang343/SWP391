package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.AddressBookRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.AddressBookResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.models.MyAddressBook;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private jakarta.servlet.http.HttpSession session;


    @GetMapping("/addressBook/{userid}")
    public ResponseEntity<List<AddressBookResponse>> getAddressBook(@PathVariable Long userid) {
        List<AddressBookResponse> result = addressBookService.getAddressBookByUserId(userid);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/addressBook")
    public ResponseEntity<AddressBookResponse> insertAddressBook(@RequestBody AddressBookRequest addressBookRequest) {
        AddressBookResponse addressBookResponse = addressBookService.insetAddressBook(addressBookRequest);

        return ResponseEntity.ok(addressBookResponse);
    }

    @PutMapping("/addressBook/{userid}/{addressbookid}")
    public ResponseEntity<AddressBookResponse> updateAddressBook(@PathVariable Long userid,
                                                                 @PathVariable Long addressbookid,
                                                                 @RequestBody AddressBookRequest addressBookRequest) {
        AddressBookResponse addressBookResponse = addressBookService.updateAddressBook(addressbookid, userid, addressBookRequest);

        return ResponseEntity.ok(addressBookResponse);
    }

    @DeleteMapping("/addressBook/{userid}/{addressbookid}")
    public ResponseEntity<ResponseResult<MyAddressBook>> deleteAddressBook(@PathVariable Long userid,
                                                                           @PathVariable Long addressbookid) {
        String deleteAddresssBookResponse = addressBookService.deleteAddressBook(userid, addressbookid);
//        System.out.println(deleteAddresssBookResponse);
        if (deleteAddresssBookResponse != null) {
            return ResponseEntity.ok(new ResponseResult<>("SUCCESS", "Xóa thành công !!!", true));
        } else  {
            return ResponseEntity.ok(new ResponseResult<>("ERROR", "Xóa không thành công!!!", false));
        }

    }


}
