package com.example.pretest.user.controller;

import com.example.pretest._enum.StatusCodeEnum;
import com.example.pretest.dto.response.BaseErrorMessage;
import com.example.pretest.dto.response.BaseResponse;
import com.example.pretest.user.dto.request.UserRequest;
import com.example.pretest.user.dto.response.UserResponse;
import com.example.pretest.user.exception.UserException;
import com.example.pretest.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getAllUser() {
        BaseResponse<List<UserResponse>> baseResponse = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();
        try {
            List<UserResponse> userResponses = userService.getAllUser();
            baseResponse.setStatusCodeByEnum(StatusCodeEnum.OK);
            baseResponse.setData(userResponses);

            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        baseResponse.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponse.getErrorMessages().add(BaseErrorMessage.builder().field("Get All User").message(errorMessage).build());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable(name = "id") Long id) {
        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();
        try {
            UserResponse userResponse = userService.getUserById(id);
            baseResponse.setStatusCodeByEnum(StatusCodeEnum.OK);
            baseResponse.setData(userResponse);

            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } catch (UserException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponse.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponse.getErrorMessages().add(BaseErrorMessage.builder().field("get user by id").message(errorMessage).build());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

    @PostMapping
    public ResponseEntity createNewUser(@RequestBody UserRequest userRequest) {
        BaseResponse <?> baseResponse = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();
        try {
            userService.createNewUser(userRequest);
            baseResponse.setStatusCodeByEnum(StatusCodeEnum.OK);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponse.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponse.getErrorMessages().add(BaseErrorMessage.builder().message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable(name = "id") Long id, @RequestBody UserRequest userRequest) {
        BaseResponse<?>  baseResponse = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();
        try {
            userService.updateUser(id, userRequest);
            baseResponse.setStatusCodeByEnum(StatusCodeEnum.OK);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponse.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponse.getErrorMessages().add(BaseErrorMessage.builder().message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") Long id) {
        BaseResponse<?> baseResponse = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();
        try {
            userService.deleteUserById(id);
            baseResponse.setStatusCodeByEnum(StatusCodeEnum.OK);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        } catch (UserException e){
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponse.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponse.getErrorMessages().add(BaseErrorMessage.builder().message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

}
