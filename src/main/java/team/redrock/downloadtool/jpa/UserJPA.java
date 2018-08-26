package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import team.redrock.downloadtool.entity.User;

import java.io.Serializable;


public interface UserJPA extends
            JpaRepository<User, Long>,
            JpaSpecificationExecutor<User>,
            Serializable {


    @Query(value = "select * from user where username = ?1",nativeQuery = true)
    public User findByUserName(String username);
}
