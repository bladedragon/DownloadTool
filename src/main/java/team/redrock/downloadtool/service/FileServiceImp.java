package team.redrock.downloadtool.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.redrock.downloadtool.entity.FileInf;
import team.redrock.downloadtool.entity.Picture;
import team.redrock.downloadtool.jpa.FileInfJPA;
import team.redrock.downloadtool.jpa.PictureJPA;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    FileInfJPA fileInfJPA;

    @Autowired
    PictureJPA pictureJPA;

   @Override
    public Response fileUpload(String filepath,HttpServletRequest request) {
        SimpleDateFormat f_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = f_date.format(new Date());
        HttpSession session = request.getSession();
        String filename = Utility.getFileName(filepath);
        String suffix = Utility.getsuffix(filename);

        FileInf fileInf = new FileInf();
        fileInf.setFilename(filename);
        fileInf.setFilepath(filepath);
        fileInf.setSuffix(suffix);
        fileInf.setTime(date);
        fileInf.setUsername((String) session.getAttribute("user_session"));
        fileInfJPA.save(fileInf);


        return new Response("0", JSON.toJSONString(fileInf));
    }

    public Response fileUpload(String filename,String filepath,String suffix,HttpServletRequest request){
        SimpleDateFormat f_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = f_date.format(new Date());
        HttpSession session = request.getSession();
        FileInf fileInf = new FileInf();
        fileInf.setFilename(filename);
        fileInf.setFilepath(filepath);
        fileInf.setSuffix(suffix);
        fileInf.setTime(date);
        fileInf.setUsername((String) session.getAttribute("user"));
        fileInf.setFoldername("ROOT");
        fileInfJPA.save(fileInf);
        System.out.println("session为"+session.getAttribute("user"));
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            switch(cookie.getName()){
                case "token":
                    token = cookie.getValue();
                    break;
                default:
                    break;
            }
        }
        System.out.println("tiken-"+token);
        return new Response("0", JSON.toJSONString(fileInf));


    }

    @Override
    public Response fileDelete(String filename) {

        fileInfJPA.DeleteFile(filename);
        return new Response("0", JSON.toJSONString("文件删除成功"));
    }

    @Override
    public Response fileSelect(String filename) {
        FileInf fileInf = new FileInf();
        fileInf = fileInfJPA.SelectByFName(filename);
        if(fileInf==null) {
            return new Response("-1","文件不存在");
        }
        return new Response("0",JSON.toJSONString(fileInf));
    }

    @Override
    public Response fileList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.FindAllById();
        return new Response("0", JSON.toJSONString(fileInfs));

    }

    public String fileExist(String filename,String filepath){

       if(fileInfJPA.SelectByFName(filename)!=null){
           return "1";
       }
       if(fileInfJPA.SelectByFPath(filepath)!=null){
           return "1";
       }
       return "0";

    }

    @Override
    public Response AudioList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectAudio();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    @Override
    public Response VedioList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectVideo();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    @Override
    public Response TextList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectText();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    @Override
    public Response TorrentList() {
        List<FileInf> fileInfs = new ArrayList<>();
        fileInfs = fileInfJPA.SelectTorrent();
        return new Response("0",JSON.toJSONString(fileInfs));
    }

    public void savePic(String filename,String suffix){
       String path = "D:\\temp\\";
        Picture picture = new Picture();
        picture.setPicname(filename);
        if(Utility.isVedio(suffix).equals("vedio")){
            picture.setPicpath(path+filename+".jpg");
        }
        if(Utility.isVedio(suffix).equals("audio")){
            picture.setPicpath(path+"audio.jpg");
        }
        if(Utility.isVedio(suffix).equals("text")){
            picture.setPicpath(path+"text.jpg");
        }
        if(Utility.isVedio(suffix).equals("torrent")){
            picture.setPicpath(path+"torrent.jpg");
        }
        if(Utility.isVedio(suffix).equals("other")){
            picture.setPicpath(path+"other.jpg");
        }
    }
}

