package com.cg.hims.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hims.Entities.User;
@Repository
public interface ILoginRepository extends JpaRepository<User,String> {

//	@Modifying
//	@Query("delete FROM User u WHERE u.userId=:kl")
//	void deleteById(@Param("kl") int Uid);

}
