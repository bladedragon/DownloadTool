package team.redrock.downloadtool.service;

import team.redrock.downloadtool.utils.Response;


public interface FolderService {

   Response RootList();

   Response SelectFolderchilden(String filename);

   Response SelectFilechildren(String filename);

   Response AddDir(String name,String currname);



}
