package com.project.FlipKart.controller;

import com.project.FlipKart.entities.User;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.UsersRepo;
import com.project.FlipKart.security.AuthRequest;
import com.project.FlipKart.service.JwtService;
import com.project.FlipKart.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers(){
        return userServiceImpl.getUsers();
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) throws UserDefinedException{
        return  userServiceImpl.addUser(user);
    }
    
    @DeleteMapping("/users/{user-id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String removeUser(@PathVariable("user-id") int userId) throws UserDefinedException {
        userServiceImpl.removeUser(userId);
        return "User "+userId+" deleted";
    }
    
    @PutMapping("/address")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User updateAddress(  @RequestParam("address") String address) throws UserDefinedException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
            return  userServiceImpl.updateAddress(user1.getUserId(), address);
    }

    @GetMapping("/wallets")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Double checkWallet()throws UserDefinedException{
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
        return userServiceImpl.getWallet(user1.getUserId());
    }

    @PutMapping("/password")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User updatePassword(@RequestParam("password")String password){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
        return userServiceImpl.updatePassword(user1.getUserId(),password);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        //authentication for verify the user before generating the token
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw new UsernameNotFoundException("Invalid user request !");
        }

    }

}
