package team.redrock.downloadtool.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue
    @Column(name = "folderid")
    private Long  folderid;

    @Column(name = "parent")
    private String parent;

    @Column(name = "foldername")
    private String foldername;

    @Column(name = "foldertime")
    private String foldertime;


}
