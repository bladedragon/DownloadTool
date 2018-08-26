package team.redrock.downloadtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.redrock.downloadtool.jpa.FileInfJPA;
import team.redrock.downloadtool.service.FileService;
import team.redrock.downloadtool.utils.CutPic;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @Autowired
    FileService fileService;

    @PostMapping(value = "/upload")

    public String  upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
//            return new Response("-1","文件为空");
        return "uploaderror";
        }


        String name = request.getParameter("filename");
        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 文件上传后的路径
        String filePath = "D://temp/";
//        String filePath ="/usr/java/yunpan/";


        if(!fileService.fileExist(name,filePath+name).equals("0"))
        {
            System.out.println(fileService.fileExist(name,filePath+name));
//            return new Response("-1","服务端已存在该文件");
            return "uploaderror";
        }
        // 解决中文问题，liunx下中文路径，图片显示问题
         fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
          fileService.fileUpload(name,filePath+fileName,suffixName,request);
            if(Utility.isVedio(suffixName).equals("vedio"))
            {
                CutPic.getfirstPic(fileName,name);
                fileService.savePic(fileName,suffixName);
            }
          return "uploadwell";

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return new Response("-1","上传失败");

        return "uploaderror";





    }

}
