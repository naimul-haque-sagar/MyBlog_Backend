package myBrain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myBrain.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	Optional<AppUser> findByUserName(String username);

}
