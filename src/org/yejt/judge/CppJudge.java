package org.yejt.judge;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;

public class CppJudge extends Judge
{
    public CppJudge(String code, String username, int pid)
    {
        super(code, username, pid);
    }

    @Override
    public Status judge()
    {
        //Write file of code
        Status status = null;
        String path = null;
        String codePath = null;
        String testcasePath = null;
        String answerPath = null;
        String filename = username + new Date().getTime();
        String outputPath = "";
        String containerId = "";
        try
        {
            //path = new File("").getCanonicalPath();
            path = "E:\\Programming\\Java\\WebHelloWorld";
            codePath = path + "\\web\\submit\\" + filename + ".cpp";
            testcasePath = path + "\\web\\testcase\\" + pid + ".txt";
            answerPath = path + "\\web\\answers\\" + pid + ".txt";
            outputPath = path + "\\web\\outputs\\" + filename + ".txt";
            File file = new File(codePath);
            if(!file.exists())
                file.createNewFile();

            PrintWriter writer = new PrintWriter(file);
            writer.write(code);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            Process runProcess = Runtime.getRuntime().exec(new String[]{"docker", "run", "-itd", "-m", "64m", "yejt/oj"});
            runProcess.waitFor();
            byte[] content = new byte[128];
            InputStream in = runProcess.getInputStream();
            int len = in.read(content);
            containerId = new String(content, Charset.forName("UTF-8")).substring(0, 12);
            in.close();
            //Copy
            runProcess = Runtime.getRuntime().exec(new String[]{"docker", "cp", codePath, containerId + ":/" + filename + ".cpp"});
            runProcess.waitFor();
            runProcess = Runtime.getRuntime().exec(new String[]{"docker", "cp", answerPath, containerId + ":/" + pid + "a.txt"});
            runProcess.waitFor();
            runProcess = Runtime.getRuntime().exec(new String[]{"docker", "cp", testcasePath, containerId + ":/" + pid + "t.txt"});
            runProcess.waitFor();
//            runProcess = Runtime.getRuntime().exec(new String[]{"docker", "exec", containerId, "dos2unix", pid + "a.txt", pid + "t.txt"});
//            runProcess.waitFor();
            runProcess.destroy();
            //Compile
            Process compileProcess = Runtime.getRuntime().exec(new String[]{"docker", "exec", containerId,
                    "g++", "-std=c++11", filename + ".cpp", "-o" + filename});
            compileProcess.waitFor();
            if(compileProcess.exitValue() != 0)//Compile error
                status = new Status(false, 6);
            else
            {
                //Run
                Process execProcess = Runtime.getRuntime().exec("cmd /c docker exec -i" + containerId +
                        " ./" + filename + " <" + testcasePath + " >>" + outputPath);
                execProcess.waitFor();
                int exitVal = execProcess.exitValue();
//                byte[] info = new byte[1024];
//                InputStream inputStream = execProcess.getInputStream();
//                inputStream.read(info);
//                System.out.println(new String(info));
                //inputStream.close();
                if (exitVal == 124)
                    status = new Status(false, 2);
                else if (exitVal != 0)
                    status = new Status(false, 4);
                else
                {
                    Process p = Runtime.getRuntime().exec(new String[]{"dos2unix", answerPath});
                    p.waitFor();
                    Process diffProcess = Runtime.getRuntime().exec(new String[]{"fc", answerPath, outputPath});
                    diffProcess.waitFor();
                    if (diffProcess.exitValue() == 0)
                        status = new Status(true, 0);
                    else if(diffProcess.exitValue() == 1)
                        status = new Status(false, 1);
                    else
                        status = new Status(false, 7);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(status == null)
            status = new Status(false, 7);
        try
        {
            Process p = Runtime.getRuntime().exec(new String[]{"docker", "stop", containerId});
            p.waitFor();
            p = Runtime.getRuntime().exec("docker rm " + containerId);
            p.waitFor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args)
    {
        String code = "#include<iostream>\n int main(){std::cout<<\"Hello World.\\n\"; return 0;}";
        CppJudge judge = new CppJudge(code, "keys961", 1);
        Status status = judge.judge();
        System.out.println(status.getStatusCode());
    }
}
