package store.ggun.ai.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import store.ggun.ai.user.domain.RoleModel;

import java.util.List;

@Builder
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String lastName;
    // private String password; 프론트로 보내는 값은 비번을 지운다
    private String firstName;
    private String email;
    // private List<RoleModel> roles;
}