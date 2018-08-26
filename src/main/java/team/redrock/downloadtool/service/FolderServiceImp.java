package team.redrock.downloadtool.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.redrock.downloadtool.entity.FileInf;
import team.redrock.downloadtool.entity.Folder;
import team.redrock.downloadtool.jpa.FileInfJPA;
import team.redrock.downloadtool.jpa.FolderJPA;
import team.redrock.downloadtool.utils.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FolderServiceImp implements FolderService{

    @Autowired
    FolderJPA folderJPA;
    @Autowired
    FileInfJPA fileInfJPA;

    Folder folder =new Folder();

    @Override
    public Response RootList() {
        List<Folder> root = new ArrayList<>();
        root =folderJPA.SelectRoot();
        return new Response("0", JSON.toJSONString(root));
    }

    @Override
    public Response SelectFolderchilden(String filename) {

        List<Folder> folders = new ArrayList<>();

        folders = folderJPA.getfolderchildren(filename);
        if (folders != null){
            return new Response("0", JSON.toJSONString(folders));
        }else{

            return new Response("-1","获取信息失败");
        }


//        return folders;
    }

    @Override
    public Response SelectFilechildren(String filename) {
        List<FileInf> files = new ArrayList<>();
        files = fileInfJPA.getfilechildren(filename);
//        return files;
        if (files != null){
            return new Response("0", JSON.toJSONString(files));
        }else{

            return new Response("-1","获取信息失败");
        }
    }

    @Override
    public Response AddDir(String name,String currname){
        SimpleDateFormat f_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = f_date.format(new Date());

        folder.setFoldername(name);
        folder.setParent(currname);
        folder.setFoldertime(date);
        folderJPA.save(folder);

        return new Response("0", JSON.toJSONString("文件夹添加成功"));
    }
}
