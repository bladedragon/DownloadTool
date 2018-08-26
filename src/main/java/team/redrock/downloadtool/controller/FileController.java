package team.redrock.downloadtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.redrock.downloadtool.entity.FileInf;
import team.redrock.downloadtool.entity.Folder;
import team.redrock.downloadtool.service.FileService;
import team.redrock.downloadtool.service.FolderService;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    FolderService folderService;

    @GetMapping("/list")
    @ResponseBody
    public Response fileList(){
        return  fileService.fileList();
    }

//    @GetMapping("/downloading")
//    public String filedownload(@RequestParam("filename") String filename){
//        return "download";
//    }

    @GetMapping("/rootlist")
    @ResponseBody
    public Response rootlist(){
        return folderService.RootList();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Response deletefile(@RequestParam("deletename") String filename){

        System.out.println("更改前："+filename);
          String  fname = Utility.getfn(filename);
        System.out.println("更改后:"+fname);
        return fileService.fileDelete(fname);

    }
    @GetMapping("/audio")
    @ResponseBody
    public Response AudioList(){
        return  fileService.AudioList();
    }

    @GetMapping("/vedio")
    @ResponseBody
    public Response VedioList(){
        return  fileService.VedioList();
    }

    @GetMapping("/text")
    @ResponseBody
    public Response TextList(){
        return  fileService.TextList();
    }

    @GetMapping("/torrent")
    @ResponseBody
    public Response TorrentList(){
        return  fileService.TorrentList();
    }



}
