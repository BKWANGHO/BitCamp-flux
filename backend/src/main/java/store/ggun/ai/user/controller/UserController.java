package store.ggun.ai.user.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ggun.ai.security.component.Messenger;
import store.ggun.ai.user.domain.UserDTO;
import store.ggun.ai.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService service;


    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String accessToken ){

        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO param){

        return ResponseEntity.ok("SUCCESS");
    }


}
