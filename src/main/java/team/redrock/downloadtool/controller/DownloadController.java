package team.redrock.downloadtool.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class DownloadController {
    @RequestMapping(value = "/download/{name}", method = RequestMethod.GET)
    public void getDownload( @PathVariable(value = "name") String name,HttpServletRequest request, HttpServletResponse response) {
        // Get your file stream from wherever.
//        String name = "复仇者联盟3.mp4";
        if (name == null)
            System.out.println("name no found");
        else {
            String fullPath = "D://temp/" + name;                  //注意要是本地的
//            String fullPath = "/usr/java/yunpan/"+name;

            File downloadFile = new File(fullPath);

            ServletContext context = request.getServletContext();
            // get MIME type of the file
            String mimeType = context.getMimeType(fullPath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }

            // set content attributes for the response
            response.setContentType(mimeType);
            // response.setContentLength((int) downloadFile.length());
            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            // 解析断点续传相关信息
            response.setHeader("Accept-Ranges", "bytes");
            long downloadSize = downloadFile.length();
            long fromPos = 0, toPos = 0;
            if (request.getHeader("Range") == null) {
                response.setHeader("Content-Length", downloadSize + "");
            } else {
                System.out.println("收到range");
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
                } else {
                    size = (int) (downloadSize - fromPos);
                }
                response.setHeader("Content-Length", size + "");
                downloadSize = size;
            }
            // Copy the stream to the response's output stream.
            RandomAccessFile in = null;
            OutputStream out = null;
            try {
                in = new RandomAccessFile(downloadFile, "rw");
                // 设置下载起始位置
                if (fromPos > 0) {
                    in.seek(fromPos);
                }
                // 缓冲区大小
                int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
                byte[] buffer = new byte[bufLen];
                int num;
                int count = 0; // 当前写到客户端的大小
                out = response.getOutputStream();
                while ((num = in.read(buffer)) != -1) {
                    out.write(buffer, 0, num);
                    count += num;
                    //处理最后一段，计算不满缓冲区的大小
                    if (downloadSize - count < bufLen) {
                        bufLen = (int) (downloadSize - count);
                        if (bufLen == 0) {
                            break;
                        }
                        buffer = new byte[bufLen];
                    }
                }
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != in) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
