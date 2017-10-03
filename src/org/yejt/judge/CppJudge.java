package org.yejt.judge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CppJudge extends Judge
{
    public CppJudge(String code, String username)
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
            path += "\\web\\submit\\" + username + new Date().getTime() + ".cpp";
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
}
