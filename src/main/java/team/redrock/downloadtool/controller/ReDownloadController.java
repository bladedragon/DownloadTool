package team.redrock.downloadtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import team.redrock.downloadtool.bean.SiteInfBean;
import team.redrock.downloadtool.service.SplitterFtech;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

@Controller


public class ReDownloadController {

    SiteInfBean siteInfoBean = null; // 文件信息 Bean
    long[] nStartPos; // 开始位置
    long[] nEndPos; // 结束位置
    SplitterFtech[] fileSplitterFetch; // 子线程对象
    long nFileLength; // 文件长度
    boolean bFirst = true; // 是否第一次取文件
    boolean bStop = false; // 停止标志

    @RequestMapping("/duoxiannchen")
    public String getDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("方法执行");
        String name = "tomcat.png";
//        siteInfoBean.setNSplitter(5);
        String fullPath = "http://120.77.150.210:8080/" + name;
        System.out.println(fullPath);
//        String fullPath = "http://localhost:8080/" + name;
        File downloadFile = new File(fullPath);

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(fullPath);
        if(mimeType == null){
            mimeType = "applocation/octet-stream";
        }

        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; inlines; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        // 解析断点续传相关信息
        response.setHeader("Accept-Ranges", "bytes");
        long downloadSize = downloadFile.length();
        long fromPos = 0, toPos = 0;
        nStartPos = new long[5];
        nEndPos = new long[5];

        if (request.getHeader("Range") == null) {
            response.setHeader("Content-Length", downloadSize + "");
            response.setHeader("Content-Range", "bytes " + 0 + "-" + (downloadSize-1) + "/" + downloadSize);
        }else{
            // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            }else{
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader("Content-Length", size + "");
            downloadSize = size;
            response.setHeader("Content-Range", "bytes " + toPos + "-" + fromPos + "/" + downloadSize);
        }


        if (bFirst) {
            nFileLength = downloadSize;
            if (nFileLength == -1) {
                System.err.println("File Length is not known!");
            } else if (nFileLength == -2) {
                System.err.println("File is not access!");
            } else {
                for (int i = 0; i < nStartPos.length; i++) {
                    nStartPos[i] = (i * (nFileLength / nStartPos.length));
                }
                for (int i = 0; i < nEndPos.length - 1; i++) {
                    nEndPos[i] = nStartPos[i + 1];
                }
                nEndPos[nEndPos.length - 1] = nFileLength;
            }
        }
        // 启动子线程
        fileSplitterFetch = new SplitterFtech[nStartPos.length];
        for (int i = 0; i < nStartPos.length; i++) {
            fileSplitterFetch[i] = new SplitterFtech(fullPath,
                    nStartPos[i], nEndPos[i], i,response.getOutputStream());
            Utility.log("Thread " + i + " , nStartPos = " + nStartPos[i] + ", nEndPos = "
                    + nEndPos[i]);
            fileSplitterFetch[i].start();
        }

        boolean breakWhile = false;
        while(!bStop)
        {

            Utility.sleep(500);
            breakWhile = true;
            for(int i=0;i<nStartPos.length;i++)
            {
                if(!fileSplitterFetch[i].bDownOver)
                {
                    breakWhile = false;
                    break;
                }
            }
            if(breakWhile)
                break;

        }
        System.err.println("文件下载完成！");

        return "downloading";
    }
}
