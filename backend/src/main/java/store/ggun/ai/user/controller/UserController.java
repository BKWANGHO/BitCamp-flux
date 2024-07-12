package store.ggun.ai.user.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.common.domain.Messenger;
import store.ggun.ai.security.domain.TokenModel;
import store.ggun.ai.user.domain.UserDTO;
import store.ggun.ai.user.domain.UserModel;
import store.ggun.ai.user.service.UserService;
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {


    private final UserService userService;

    @PostMapping("/login")
    public Mono<Messenger> login(@RequestBody UserModel param) {
        log.info("::: 로그인 컨트롤러 파라미터 : {}",param.toString());

        // Messenger m = Messenger.builder().message("SUCCESS").build();
        // Mono<ResponseEntity<Messenger>> helloWorld = Mono.just(ResponseEntity.ok(m));

        //switchIfEmpty 는 전달받은 값이 null인 경우 새로운 Mono/Flux 로 변환
        //defaultIfEmpty 는  defaultIfEmpty는 null을 대체하는 값을 지정한다

        return userService.login(param).defaultIfEmpty(Messenger.builder().message("FAILURE").build());
    }


    @GetMapping("/logout")
    public Mono<Messenger> logout(@RequestHeader("Authorization") String accessToken) {
        log.info("1- logout request : {}", accessToken);
        Messenger m = Messenger.builder().message("SUCCESS").build();
        Mono<Messenger> logout = Mono.just(m);
        return logout;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserModel> getUserById(@PathVariable("userId") String userId) {
        return userService.getUserDetailById(userId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserModel> createUser(@RequestBody UserModel user) {
        return userService.addUser(new UserModel(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        ));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserModel> updateUser(@PathVariable("id") String id, @RequestBody UserModel user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }



}