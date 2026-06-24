package edu.ijse.test.demo.service.impl;

import edu.ijse.test.demo.dto.UserDTO;
import edu.ijse.test.demo.entity.User;
import edu.ijse.test.demo.repository.UserRepository;
import edu.ijse.test.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserDTO userDTO) {
        try {

            log.info("||--------Executed Save User Method-------||");

            User user = new User();

            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setContactNumber(userDTO.getContactNumber());
            user.setAddress(userDTO.getAddress());
            user.setRole(userDTO.getRole());

            userRepository.save(user);

            log.info("User Saved Successfully...");

        } catch (Exception e) {
            log.error("Error Save User Method!!!");
        }
    }

    @Override
    public UserDTO findUserDetails(Long id) {

        try {

            log.info("||--------Executed Find User Details Method-------||");

            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isEmpty()) {
                log.info("Related User Could not be found...");
                throw new RuntimeException("User Not Found");
            }

            User user = userOptional.get();

            log.info("User Found Successfully...");
            return new UserDTO(
                    user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                    user.getContactNumber(), user.getAddress(), user.getRole()
            );

        } catch (Exception e) {
            log.error("Error Find User Details Method!!!");
            throw e;
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {

        try {

            log.info("||--------Executed Update User Method-------||");

            Optional<User> userOptional = userRepository.findById(userDTO.getId());

            if (userOptional.isEmpty()) {
                log.info("This Kind Of User Not Found...");
                throw new RuntimeException("This Kind Of User Not Found");
            }

            User user = userOptional.get();

            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setContactNumber(userDTO.getContactNumber());
            user.setAddress(userDTO.getAddress());
            user.setRole(userDTO.getRole());

            userRepository.save(user);
            log.info("User Updated Successfully...");
        }  catch (Exception e) {
            log.error("Error Update User Method!!!");
            throw e;
        }
    }
}
