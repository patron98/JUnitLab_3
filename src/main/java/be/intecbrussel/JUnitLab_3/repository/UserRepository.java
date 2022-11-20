package be.intecbrussel.JUnitLab_3.repository;

import be.intecbrussel.JUnitLab_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
