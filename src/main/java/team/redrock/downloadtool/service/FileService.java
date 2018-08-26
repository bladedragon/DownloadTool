package team.redrock.downloadtool.service;

import team.redrock.downloadtool.utils.Response;

import javax.servlet.http.HttpServletRequest;


public interface FileService {

    Response fileUpload(String filepath, HttpServletRequest request);
    Response fileUpload(String filename,String filepath,String suffix, HttpServletRequest request);
    Response fileDelete(String filename);
    Response fileSelect(String filename);
    Response fileList();
    String fileExist(String filename,String filepath);
    Response AudioList();
    Response VedioList();
    Response TextList();
    Response TorrentList();
   void savePic(String filename,String suffix);
}
