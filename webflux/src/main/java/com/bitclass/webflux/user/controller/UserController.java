package com.bitclass.webflux.user.controller;

import com.bitclass.webflux.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "200", description = "SUCCESS"),
})
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {
//    @DeleteMapping("/delete")
//    public ResponseEntity<Messenger> deleteById(@RequestParam Long id) {
//        log.info("입력받은 정보 : {}",id);
//        return ResponseEntity.ok(service.deleteById(id));
//    }
//
//    @GetMapping("/count")
//    public ResponseEntity<Long> count() {
//        return ResponseEntity.ok(service.count());
//    }
//
//    @GetMapping("/exists")
//    public ResponseEntity<Boolean> existsById(@RequestParam Long id) {
//        return ResponseEntity.ok(service.existsById(id));
//    }
//
//
////    ==============================================================
//
//    @PostMapping("/search")
//    public ResponseEntity<List<UserDto>> findByName(@RequestBody UserDto userDto) {
//        log.info("입력받은 정보 : {}",userDto);
//        return ResponseEntity.ok(service.findByName(userDto.getName()));
//    }
//
//    @GetMapping("/logout")
//    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String accessToken ){
//
//        return ResponseEntity.ok(service.logout(accessToken));
//    }
//


//    private final UserService service;
//
//    @PostMapping( "/save")
//    public ResponseEntity<Messenger> save(@RequestBody UserDto userDto) {
//        log.info("입력받은 정보 : {}",userDto);
//        return ResponseEntity.ok(service.save(userDto));
//    }
//
//    @GetMapping( "/list")
//    public ResponseEntity<List<UserDto>> findAll() {
//        log.info("입력받은 정보 : {}");
//        return ResponseEntity.ok(service.findAll());
//    }
//
//    @GetMapping(path="/detail")
//    public ResponseEntity<Optional<UserDto>> findById(@RequestParam Long id) {
//        log.info("입력받은 정보 : {}",id);
//        return ResponseEntity.ok(service.findById(id));
//    }
//
//    @PutMapping( "/modify")
//    public ResponseEntity<Optional<UserDto>> modify(@RequestBody UserDto userDto){
//        log.info("입력받은 정보 : {}",userDto);
//        return ResponseEntity.ok(service.modify(userDto));
//    }
}
