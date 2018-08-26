package team.redrock.downloadtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.redrock.downloadtool.service.FolderService;
import team.redrock.downloadtool.utils.Response;


@RestController
public class FolderController {

    @Autowired
    FolderService folderService;

//    @RequestMapping("ROOT{(.*?)}/{filename}")
    @RequestMapping("ROOT/{filename}")
    public Response folders(@PathVariable(name = "filename") String filename){

//        System.out.println("q启动！");
//        model.addAttribute("folders",folderService.SelectFolderchilden(filename));
//        model.addAttribute("files",folderService.SelectFilechildren(filename));
          return folderService.SelectFolderchilden(filename);
    }

    @RequestMapping("root/{filename}")
    public Response files(@PathVariable(name = "filename") String filename){

        return folderService.SelectFilechildren(filename);
    }

    @RequestMapping("/createdir")
    public Response creatDir(@RequestParam("name") String dirname,
                             @RequestParam("currname") String currname){

        return folderService.AddDir(dirname,currname);

    }
}
