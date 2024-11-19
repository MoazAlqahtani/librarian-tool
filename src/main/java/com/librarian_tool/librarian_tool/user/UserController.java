package com.librarian_tool.librarian_tool.user;

import com.librarian_tool.librarian_tool.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing user-related operations.
 *
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Builder
@Slf4j
public class UserController {

    private final UserServiceInterface userServiceInterface;

    /**
     * Endpoint to remove a user.
     *
     * @param userDto the {@link UserDto} object identifying the user to remove.
     * @return the removed {@link UserDto} and {@link HttpStatus#OK}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @PostMapping(value = "/remove")
    public ResponseEntity<UserDto> removeUser(@Valid @RequestBody UserDto userDto){
        log.info("Removing user.");
        return new ResponseEntity<>(userServiceInterface.removeUser(userDto),HttpStatus.OK);
    }

    /**
     * Endpoint to fetch all users.
     *
     * @return the list of all {@link UserDto} objects and {@link HttpStatus#ACCEPTED}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @GetMapping(value = "/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("Getting all users.");
        return new ResponseEntity<>(userServiceInterface.getAllUsers().stream().toList(),HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint to update user details.
     *
     * @param userDto the {@link UserDto} object containing updated user details.
     * @return the updated {@link UserDto} and {@link HttpStatus#OK}.
     *
     * @author  Moaz
     * @version 1.0
     */
    @PutMapping(value = "/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto){
        log.info("Updating user.");
        return new ResponseEntity<>(userServiceInterface.updateUser(userDto),HttpStatus.OK);
    }

    /**
     * Exception handler for validation errors.
     *
     * @param ex the {@link MethodArgumentNotValidException} containing validation error details.
     * @return a map of field names and error messages.
     *
     * @author  Moaz
     * @version 1.0
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }

}
