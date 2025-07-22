package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.AddressBookRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.AddressBookResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.models.MyAddressBook;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.AddressRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public AddressBookResponse insetAddressBook(AddressBookRequest addressBookRequest) {
        MyAddressBook myAddressBook = new MyAddressBook();

        Optional<User> userOptional = userRepository.findByUserIdNative(addressBookRequest.getUserId());

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (user.getMyAddressBooks() == null) {
                user.setMyAddressBooks(new ArrayList<>());
            }

            myAddressBook.setUser(user);
            myAddressBook.setFullName(addressBookRequest.getFullName());
            myAddressBook.setPhone(addressBookRequest.getPhone());
            myAddressBook.setAddress(addressBookRequest.getAddress());
            myAddressBook.setNote(addressBookRequest.getNote());
            myAddressBook.setSetToAddress(addressBookRequest.getSetToAddress());
            myAddressBook.setEmail(addressBookRequest.getEmail());

            addressRepository.save(myAddressBook);

            user.getMyAddressBooks().add(myAddressBook);

            userRepository.save(user);

            AddressBookResponse addressBookResponse = new AddressBookResponse();

            addressBookResponse.setUserId(myAddressBook.getUser().getUserId());
            addressBookResponse.setFullName(myAddressBook.getFullName());
            addressBookResponse.setPhone(myAddressBook.getPhone());
            addressBookResponse.setAddress(myAddressBook.getAddress());
            addressBookResponse.setNote(myAddressBook.getNote());
            addressBookResponse.setSetToAddress(myAddressBook.getSetToAddress());
            addressBookResponse.setDateCreated(myAddressBook.getCreatedAt());

            return addressBookResponse;
        } else {
            throw new RuntimeException("User not found with ID: " + addressBookRequest.getUserId());
        }
    }

    public List<AddressBookResponse> getAddressBookByUserId(Long userid) {

        Optional<User> userOptional = userRepository.findByUserIdNative(Math.toIntExact(userid));

        List<MyAddressBook> addressBooks = addressRepository.findByUser(userOptional.get());

        List<AddressBookResponse> addressBookResponses = new ArrayList<>();

        for (MyAddressBook myAddressBook : addressBooks) {

            AddressBookResponse addressBookResponse = new AddressBookResponse();
            addressBookResponse.setAddressBookId(myAddressBook.getAddressBookID());
            addressBookResponse.setUserId(myAddressBook.getUser().getUserId());
            addressBookResponse.setFullName(myAddressBook.getFullName());
            addressBookResponse.setPhone(myAddressBook.getPhone());
            addressBookResponse.setAddress(myAddressBook.getAddress());
            addressBookResponse.setNote(myAddressBook.getNote());
            addressBookResponse.setSetToAddress(myAddressBook.getSetToAddress());
            addressBookResponse.setDateCreated(myAddressBook.getCreatedAt());
            addressBookResponse.setEmail(myAddressBook.getEmail());

            addressBookResponses.add(addressBookResponse);
        }

        return addressBookResponses;
    }

    public AddressBookResponse updateAddressBook(Long addressbookid, Long userid, AddressBookRequest addressBookRequest) {

        if (userid == null) {
            return null;
        } else {
            if (addressbookid == null) {
                return null;
            } else {
                MyAddressBook myAddressBook = addressRepository.findById(addressbookid).get();

                myAddressBook.setAddressBookID(Math.toIntExact(addressbookid));
                myAddressBook.setFullName(addressBookRequest.getFullName());
                myAddressBook.setPhone(addressBookRequest.getPhone());
                myAddressBook.setAddress(addressBookRequest.getAddress());
                myAddressBook.setNote(addressBookRequest.getNote());
                myAddressBook.setSetToAddress(addressBookRequest.getSetToAddress());
                myAddressBook.setEmail(addressBookRequest.getEmail());

                addressRepository.save(myAddressBook);

                AddressBookResponse addressBookResponse = new AddressBookResponse();

                addressBookResponse.setAddressBookId(myAddressBook.getAddressBookID());
                addressBookResponse.setUserId(myAddressBook.getUser().getUserId());
                addressBookResponse.setFullName(myAddressBook.getFullName());
                addressBookResponse.setPhone(myAddressBook.getPhone());
                addressBookResponse.setAddress(myAddressBook.getAddress());
                addressBookResponse.setNote(myAddressBook.getNote());
                addressBookResponse.setSetToAddress(myAddressBook.getSetToAddress());
                addressBookResponse.setEmail(myAddressBook.getEmail());
                addressBookResponse.setDateCreated(myAddressBook.getCreatedAt());

                return addressBookResponse;
            }
        }

    }

    public String deleteAddressBook(Long userid, Long addressbookid) {
        Optional<User> userOptional = userRepository.findByUserIdNative(Math.toIntExact(userid));

        if (!userOptional.isPresent()) {
            return null;
        } else {

            Optional<MyAddressBook> result = addressRepository.findById(addressbookid);
            if ( result.isEmpty()) {
                return null;
            } else {
                addressRepository.deleteById(addressbookid);
                return "Address book deleted successfully";
            }

        }


    }
}

