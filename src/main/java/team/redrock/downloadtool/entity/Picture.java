package team.redrock.downloadtool.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name")
    private String picname;

//    @Column(name = "f_name")
//    private String filename;

    @Column(name = "p_path")
    private String picpath;

}
