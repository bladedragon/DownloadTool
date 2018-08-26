package team.redrock.downloadtool.service;

import team.redrock.downloadtool.utils.Response;

import javax.servlet.http.HttpServletRequest;


public interface UserService {

    Response userRegister(String username,String password);
    Response userLogin(String username , String password, HttpServletRequest request);

}
