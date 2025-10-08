package org.team1.team1shoppingcart.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.team1.team1shoppingcart.model.entity.User;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:11
 * @Param
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
