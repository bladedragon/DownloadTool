package team.redrock.downloadtool.service;

import org.springframework.stereotype.Service;
import team.redrock.downloadtool.utils.Utility;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplitterFtech extends Thread{
    String sURL; //File URL
    long nStartPos; //File Snippet Start Position
    long nEndPos; //File Snippet End Position
    int nThreadID; //Thread's ID
    public boolean bDownOver = false; //Downing is over
    boolean bStop = false; //Stop identical
    OutputStream out;
    FileAccess fileAccessI = null; //File Access interface

    int nowprogress ;

    public SplitterFtech(String sName, long nStart, long nEnd, int id, OutputStream out)
            throws IOException
    {
        this.sURL = sURL;
        this.nStartPos = nStart;
        this.nEndPos = nEnd;
        this.out = new BufferedOutputStream(out);
        nThreadID = id;
        fileAccessI = new FileAccess(sName,nStartPos);
    }

    public void run()
    {
        while(nStartPos < nEndPos && !bStop)
        {
            try{
//                URL url = new URL(sURL);
//                HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection ();
//                httpConnection.setRequestProperty("User-Agent","NetFox");
//                httpConnection.setRequestProperty("Charset", "UTF-8");
//
//                String sProperty = "bytes="+nStartPos+"-";
//                httpConnection.setRequestProperty("RANGE",sProperty);
//
//                Utility.log(sProperty);
//                InputStream input = httpConnection.getInputStream();
                //logResponseHead(httpConnection);
                byte[] b = new byte[4096];
                int nRead=4096;
                int len;
                while(  (nEndPos-nStartPos+1)>=4096 && nStartPos < nEndPos
                        && !bStop)

                        out.write(b,0,nRead);                                    /**这里有问题*/
                    nStartPos += fileAccessI.write(b,0,nRead);

                    if (nEndPos - nStartPos+1 < 4096) {
                        nRead = (int) (nEndPos-nStartPos+1);
                        if(nRead==0){
                            System.out.println("nRead==0");
                            break;
                        }
//                    nowprogress += nRead;  //实时下载进度
//                    System.out.println(String.valueOf(nowprogress));
                }
                Utility.log("Thread " + nThreadID + " is over!");
                bDownOver = true;
//nPos = fileAccessI.write (b,0,nRead);
            }
            catch(Exception e){e.printStackTrace ();}
        }
    }
    // 打印回应的头信息
    public void logResponseHead(HttpURLConnection con)
    {
        for(int i=1;;i++)
        {
            String header=con.getHeaderFieldKey(i);
            if(header!=null)
//responseHeaders.put(header,httpConnection.getHeaderField(header));
                Utility.log(header+" : "+con.getHeaderField(header));
            else
                break;
        }
    }
}
