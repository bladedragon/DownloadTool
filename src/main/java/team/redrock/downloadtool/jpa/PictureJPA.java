package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import team.redrock.downloadtool.entity.Folder;
import team.redrock.downloadtool.entity.Picture;

import java.io.Serializable;

public interface PictureJPA extends
        JpaRepository<Picture,Long>,
        JpaSpecificationExecutor<Picture>,
        Serializable {

    @Query(value = "select * from picture where f_name = ?1",nativeQuery = true)
    public Picture getPic(String filename);



}
