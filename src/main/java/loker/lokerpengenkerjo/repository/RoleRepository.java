	
package loker.lokerpengenkerjo.repository;

import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import loker.lokerpengenkerjo.model.Role;
import loker.lokerpengenkerjo.model.RoleName;
 
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}