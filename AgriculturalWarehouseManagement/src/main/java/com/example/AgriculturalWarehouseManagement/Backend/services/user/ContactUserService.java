package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.CapitalizedEachWord;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.ContactUsUserRequests;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ContactUsUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.models.ContactUsUser;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ContactUsUserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactUserService {

    @Autowired
    private ContactUsUserRepository contactUsUserRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseResult<ContactUsUserResponse> insertContactUsUser(ContactUsUserRequests contactUsUserRequests, int userID) {

        CapitalizedEachWord capitalizedEachWord = new CapitalizedEachWord();

        if (!capitalizedEachWord.isCapitalizedEachWord(contactUsUserRequests.getFullName())) {
            return new ResponseResult<>("ERROR", "FullName cannot be capitalized Each Word", false);
        }

        String address = contactUsUserRequests.getAddress();
        // Bỏ toàn bộ ký tự trắng (space, tab, xuống dòng, v.v.)
        String addressWithoutSpaces = address.replaceAll("\\s+", "");

        if (addressWithoutSpaces.length() < 9){
            return new ResponseResult<>("ERROR", "Address cannot be less than 9 characters", false);
        }

        String message = contactUsUserRequests.getMessage();
        String messageWithoutSpaces = message.replaceAll("\\s+", "");

        if (messageWithoutSpaces.length() < 9){
            return new ResponseResult<>("ERROR", "Message cannot be less than 9 characters", false);
        }

        Optional<User> user = userRepository.findByUserIdNative(userID);

        ContactUsUser contactUsUser = new ContactUsUser();
        contactUsUser.setUser(user.get());
        contactUsUser.setFullName(contactUsUserRequests.getFullName());
        contactUsUser.setEmail(contactUsUserRequests.getEmail());
        contactUsUser.setAddress(contactUsUserRequests.getAddress());
        contactUsUser.setPhone(contactUsUserRequests.getPhone());
        contactUsUser.setMessage(contactUsUserRequests.getMessage());

        contactUsUserRepository.save(contactUsUser);

        ContactUsUserResponse contactUsUserResponse = new ContactUsUserResponse();
        contactUsUserResponse.setContactUsUserId(contactUsUser.getContactusid());
        contactUsUserResponse.setFullName(contactUsUser.getFullName());
        contactUsUserResponse.setEmail(contactUsUser.getEmail());
        contactUsUserResponse.setAddress(contactUsUser.getAddress());
        contactUsUserResponse.setPhone(contactUsUser.getPhone());
        contactUsUserResponse.setMessage(contactUsUser.getMessage());

        return new ResponseResult<>("SUCCESS", "ContactUsUser successfully inserted", true);
    }
}
