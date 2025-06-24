package br.com.rgsystems.dscomerce.repositories;

import br.com.rgsystems.dscomerce.entities.User;
import br.com.rgsystems.dscomerce.projections.UserDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
                            SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
                            FROM tb_user
                            INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
                            INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
                            WHERE tb_user.email = :email
            """)
    List<UserDetailProjection> searchUserAndRoleByEmail(String email);

    Optional<User> findByEmail(String email);
}
