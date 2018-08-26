package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import team.redrock.downloadtool.entity.Folder;

import java.io.Serializable;
import java.util.List;

public interface FolderJPA extends
        JpaRepository<Folder,Long>,
        JpaSpecificationExecutor<Folder>,
        Serializable {

    @Query(value = "select * from folder where parent is null order by folderid ",nativeQuery = true)
    public List<Folder> SelectRoot();


    @Query(value = "select * from folder where parent = ?1 ",nativeQuery = true)
    public List<Folder> getfolderchildren(String foldername);

    @Query(value = "select * from folder where foldername = ?1 ",nativeQuery = true)
    public Folder getfolder(String foldername);

}
