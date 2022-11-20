package be.intecbrussel.JUnitLab_3.repository;

import be.intecbrussel.JUnitLab_3.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

}
