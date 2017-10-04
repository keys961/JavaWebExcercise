package org.yejt.judge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class JavaJudge extends Judge
{
    public JavaJudge(String code, String username, int pid)
    {
        super(code, username, pid);
    }

    @Override
    public Status judge()
    {
        //Write file of code
        String path = null;
        String codePath = null;
        String testcasePath = null;
        String answerPath = null;
        try
        {
            path = new File("").getCanonicalPath();
            codePath = path + "\\web\\submit\\" + username + ".java";
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

        Process process;

        return null;
    }
}
