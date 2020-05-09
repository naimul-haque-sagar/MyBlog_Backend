package myBrain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myBrain.model.AppUserGroup;

public interface AppUserGroupRepository extends JpaRepository<AppUserGroup, Long>{

	List<AppUserGroup> findByUsername(String username);

}
