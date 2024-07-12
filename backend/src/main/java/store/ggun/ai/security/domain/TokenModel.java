package store.ggun.ai.security.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;


@Builder
@Document(collection = "tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {

    @Id
    private String tokenId;
    private String refreshToken;
    private String email;
    private Date expiration;

}
