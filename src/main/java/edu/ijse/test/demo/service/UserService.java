package edu.ijse.test.demo.service;

import edu.ijse.test.demo.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);
    UserDTO findUserDetails(Long id);
    void updateUser(UserDTO userDTO);
}
