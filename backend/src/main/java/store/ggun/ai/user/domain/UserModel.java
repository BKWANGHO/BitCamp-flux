package store.ggun.ai.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import store.ggun.ai.user.domain.RoleModel;

import java.util.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class UserModel {

    @Id String userId;
    String firstName;
    String lastName;
    String email;
    String password ;

    List <RoleModel> roles ;



    @Override
    public String toString() {
        return "UserModel [userId=" + userId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", password=" + password +
                "]";
    }



}