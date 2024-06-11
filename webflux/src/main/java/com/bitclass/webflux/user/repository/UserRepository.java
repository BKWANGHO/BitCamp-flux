package com.bitclass.webflux.user.repository;


import com.bitclass.webflux.user.domain.UserDto;
import com.bitclass.webflux.user.domain.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  {

    Optional<UserModel> findByUsername(String username);

    List<UserDto> findByName(String name);


//    @Modifying
//    @Query("update users set token = :token where id = :id")
//    void modifyTokenById(@Param("id") Long id ,@Param("token") String token);

    Boolean existsByUsername(String param);

}
