package org.yejt.judge;

import java.io.*;
import java.util.Date;

public class CJudge extends Judge
{
    public CJudge(String code, String username, int pid)
    {
        super(code, username, pid);
    }

    @Override
    public Status judge()
    {
        //TODO: Back-end Judgement
        //Write file of code
        String path = null;
        String codePath = null;
        String testcasePath = null;
        String answerPath = null;
        try
        {
            path = new File("").getCanonicalPath();
            codePath = path + "\\web\\submit\\" + username + new Date().getTime() + ".c";
            testcasePath = path + "\\web\\submit\\" + pid + ".txt";
            answerPath = path + "\\web\\submit\\" + pid + ".txt";
            File file = new File(codePath);
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

        try
        {

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args)
    {
        try
        {
            Process p = Runtime.getRuntime().exec("docker version");
            p.waitFor();
//            Process process = Runtime.getRuntime().exec("docker images");
//            process.waitFor();
            System.out.println(p.exitValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        String path = null;
//        try
//        {
//            path = new File("").getCanonicalPath();
//            path += "\\web\\submit\\" + "keys961" + new Date().getTime() + ".c";
//            File file = new File(path);
//            if(!file.exists())
//                file.createNewFile();
//
//            PrintWriter writer = new PrintWriter(file);
//            writer.write("#include <stdio.h> int main(){return 0;}");
//            writer.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        try
//        {
//            Process p = Runtime.getRuntime().exec("docker ps");
//            p.waitFor();
//            System.out.println(p.exitValue());
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
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
