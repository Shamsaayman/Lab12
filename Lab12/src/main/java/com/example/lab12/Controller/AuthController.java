package com.example.lab12.Controller;
import com.example.lab12.Model.User;
import com.example.lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body("Register success");
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid User user){
        authService.login(user.getUsername(), user.getPassword());
        return ResponseEntity.status(200).body("Login successful");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        authService.logout();
        return ResponseEntity.status(200).body("Logout successful");
    }
    @GetMapping("/get/{username}")
    public ResponseEntity getAllUsers(@PathVariable String username){
        return ResponseEntity.status(200).body(authService.getAllUsers(username));
    }
    @PutMapping("/update/{username}")
    public ResponseEntity updateUser( @RequestBody @Valid User user , @PathVariable String username){
        authService.updateUser(username, user);
        return ResponseEntity.status(200).body("Update successful");
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser( @PathVariable Integer userId){
        authService.deleteUser(userId);
        return ResponseEntity.status(200).body("Delete successful");
    }
}
