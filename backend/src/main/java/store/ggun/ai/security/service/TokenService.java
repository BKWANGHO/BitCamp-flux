package store.ggun.ai.security.service;


import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import store.ggun.ai.security.component.JwtToken;
import store.ggun.ai.security.domain.TokenModel;
import store.ggun.ai.security.repository.TokenRepository;
import store.ggun.ai.user.domain.UserModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;



    public void saveRefreshToken(UserModel user,String refreshToken,Long refreshExpiration){

        TokenModel token = TokenModel.builder()
                .email(user.getEmail())
                .refreshToken(refreshToken)
                .expiration(Date.from(Instant.now().plusSeconds(refreshExpiration)))
                .build();


        tokenRepository.save(token)
                .flatMap(i->Mono.just(i.getRefreshToken()))
                .subscribe();
    }




}
