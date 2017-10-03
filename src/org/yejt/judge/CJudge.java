package org.yejt.judge;

import java.io.*;
import java.util.Date;

public class CJudge extends Judge
{
    public CJudge(String code, String username)
    {
        super(code, username);
    }

    @Override
    public Status judge()
    {
        String path = null;
        try
        {
            path = new File("").getCanonicalPath();
            path += "\\web\\submit\\" + username + new Date().getTime() + ".c";
            File file = new File(path);
            if(!file.exists())
                file.createNewFile();

            PrintWriter writer = new PrintWriter(file);
            writer.write(code);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Process process;

        return null;

    }

    public static void main(String[] args)
    {
        try
        {
            Process p = Runtime.getRuntime().exec("docker ps");
            p.waitFor();
            System.out.println(p.exitValue());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        File directory = new File("");// 参数为空
//        try
//        {
//            String courseFile = directory.getCanonicalPath();
//            System.out.println(courseFile);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

//        String path;
//        try
//        {
//            path = new File("").getCanonicalPath();
//            path += "\\web\\submit\\";
//            System.out.println(path);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }
}
