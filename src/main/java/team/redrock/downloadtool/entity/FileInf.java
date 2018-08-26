package team.redrock.downloadtool.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fileinf")
public class FileInf {

    @Id
    @GeneratedValue
    @Column(name = "f_id")
    private Long fileid;

    @Column(name = "f_name")
    private String filename;

    @Column(name = "f_path")
    private String filepath;

    @Column(name = "f_suffix")
    private String suffix;

    @Column(name = "f_time")
    private String time;

    @Column(name = "username")
    private String username;

    @Column(name = "foldername")
    private String foldername;


}
