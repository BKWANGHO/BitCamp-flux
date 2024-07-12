package store.ggun.ai.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.common.domain.Messenger;
import store.ggun.ai.security.component.JwtToken;
import store.ggun.ai.security.service.TokenService;
import store.ggun.ai.user.domain.UserDTO;
import store.ggun.ai.user.domain.UserModel;
import store.ggun.ai.user.repository.UserRepository;

import java.util.Optional;

@Service@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final JwtToken jwtToken;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Value("${jwt.expiration.refresh}")
    private long refreshExpiration;

    @Value("${jwt.expiration.access}")
    private long accessExpiration;
    public Mono<Messenger> login(UserModel user) {
        log.info("로그인에 사용되는 이메일 : {}",user.getEmail());

        var accessToken = jwtToken.generateToken(null,user,false);
        var refreshToken = jwtToken.generateToken(null,user,true);

        log.info("Access token : {}",accessToken);
        log.info("Refresh token : {}",refreshToken);

        tokenService.saveRefreshToken(user,refreshToken,refreshExpiration);

        // Sync
        return userRepository.findByEmail(user.getEmail())
                .filter(i -> i.getPassword().equals(user.getPassword()))

                .map(i -> UserDTO.builder().email(i.getEmail()).firstName(i.getFirstName()).lastName(i.getLastName()).build())
                .log()
                .map(i -> Messenger.builder().message("SUCCESS").data(i)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessTokenExpire(accessExpiration)
                        .refreshTokenExpire(refreshExpiration)
                        .build());
    }
    public Mono<Messenger> login2(UserModel user) {
        log.info("로그인 2 에 사용되는 이메일 : {}",user.getEmail());
        // Async
        // attach 방식으로 사용
        return userRepository.findByEmail(user.getEmail())
                .filter(i -> i.getPassword().equals(user.getEmail()))
                .flatMap(i -> Mono.just(UserDTO.builder().email(i.getEmail()).firstName(i.getFirstName()).lastName(i.getLastName()).build()))
                .log()
                .flatMap(i -> Mono.just(Messenger.builder().data(i).build()));
    }

    public Flux<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<UserModel> getUserDetailByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<UserModel> getUserDetailById(String id) {
        return userRepository.findById(id);
    }

    public Mono<UserModel> addUser(UserModel user) {
        return userRepository.save(user);
    }

    public Mono<UserModel> updateUser(String id, UserModel user) {
        return userRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalUser -> {
                    if (optionalUser.isPresent()) {
                        user.setUserId(id);
                        return userRepository.save(user);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    public Mono<Void> deleteAllUsers() {
        return userRepository.deleteAll();
    }

    public Flux<UserModel> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }
}