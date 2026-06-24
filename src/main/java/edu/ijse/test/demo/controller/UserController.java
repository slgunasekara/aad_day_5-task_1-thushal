package edu.ijse.test.demo.controller;

import edu.ijse.test.demo.contant.CommonResponse;
import edu.ijse.test.demo.dto.UserDTO;
import edu.ijse.test.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static edu.ijse.test.demo.contant.ResponseCode.OPERATION_SUCCESS;
import static edu.ijse.test.demo.contant.ResponseMessage.SUCCESS_MESSAGE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse userSave (@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping (value = "/{userId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse findUserById (@PathVariable Long userId) {
        userService.findUserDetails(userId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateUser (@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }
}
