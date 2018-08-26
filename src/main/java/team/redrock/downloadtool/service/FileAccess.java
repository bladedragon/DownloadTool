package team.redrock.downloadtool.service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class FileAccess implements Serializable {
    RandomAccessFile oSavedFile;
    long nPos;
    public  FileAccess() throws IOException
    {
        this("",0);
    }
    public  FileAccess(String sName,long nPos) throws IOException
    {
        oSavedFile = new RandomAccessFile(sName,"r");
        this.nPos = nPos;
        oSavedFile.seek(nPos);
    }
    public synchronized int write(byte[] b,int nStart,int nLen)
    {
        int n = -1;
        try{
            n= oSavedFile.read(b,nStart,nLen);
            System.out.println("FileAccess的n是："+n);
        }
        catch(IOException e)
        {
            e.printStackTrace ();
        }
        return n;
    }
}
