package org.yejt.judge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class JavaJudge extends Judge
{
    public JavaJudge(String code, String username)
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
            path += "\\web\\submit\\" + username  + ".java";
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
