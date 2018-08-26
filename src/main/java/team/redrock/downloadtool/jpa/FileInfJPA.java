package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import team.redrock.downloadtool.entity.FileInf;




import java.io.Serializable;
import java.util.List;

public interface FileInfJPA extends JpaRepository<FileInf, Long>,
        JpaSpecificationExecutor<FileInf>,
        Serializable {

    @Query(value = "select * from fileinf order by f_id desc ",nativeQuery = true)
    public List<FileInf> FindAllById();

    @Query(value = "select * from fileinf order by conert(f_name using GBK) ",nativeQuery = true)
    public List<FileInf> FindAllByName();

    @Query(value = "select * from fileinf  where f_name = ?1",nativeQuery = true)
    public FileInf SelectByFName(String fname);

    @Query(value = "select * from fileinf  where f_path = ?1",nativeQuery = true)
    public FileInf SelectByFPath(String fpath);


    @Modifying
    @Query(value = "delete  from fileinf where f_name = ?1",nativeQuery = true)
    public void DeleteFile(String fname);

    @Query(value = "select * from fileinf where foldername = ?1 order by f_id desc",nativeQuery = true)
    public List<FileInf> getfilechildren(String foldername);

    @Query(value = "select * from fileinf where lower(f_suffix) in('.mp4','.avi','.mov','.wmv','.flv','.rmvb') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectVideo();

    @Query(value = "select * from fileinf where lower(f_suffix) in('.mp3','.wav','.flac','.aac') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectAudio();

    @Query(value = "select * from fileinf where lower(f_suffix) in('.txt','.doc','.md','.pdf','equb') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectText();

    @Query(value = "select * from fileinf where lower(f_suffix) in('.torrent') order by f_id desc ",nativeQuery = true)
    public List<FileInf> SelectTorrent();


}
