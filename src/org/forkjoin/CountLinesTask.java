package org.forkjoin;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;

public class CountLinesTask extends RecursiveTask<Integer>
{
    private File file;

    private List<CountLinesTask> subTasks = new LinkedList<>();

    private static final Set<String> PRIFIXES = new ConcurrentSkipListSet<>();

    static
    {
        PRIFIXES.add("java");
        PRIFIXES.add("cpp");
        PRIFIXES.add("xml");
        PRIFIXES.add("html");
        PRIFIXES.add("css");
        PRIFIXES.add("js");
        PRIFIXES.add("c");
        PRIFIXES.add("py");
        PRIFIXES.add("h");
        PRIFIXES.add("sql");
        PRIFIXES.add("jsp");
    }

    public CountLinesTask(File file)
    {
        this.file = file;
    }

    @Override
    protected Integer compute()
    {
        int sum = 0;
        String line = "";
        if(!file.isDirectory())
            return getLines(file);

        File[] filesInDirectory = file.listFiles();
        if(filesInDirectory == null)
            return 0;
        for(File f : filesInDirectory)
        {
            if(f.isDirectory())
            {
                if(f.getName().equals("out") || f.getName().equals("target"))
                    continue;
                CountLinesTask subTask = new CountLinesTask(f);
                subTasks.add(subTask);
                subTask.fork();
            }
            else
                if(isValidFile(f))
                    sum += getLines(f);
        }

        for(CountLinesTask task : subTasks)
            sum += task.join();
        return sum;
    }

    private int getLines(File file)
    {
        int sum = 0;
        String line = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            while((line = reader.readLine()) != null)
            {
                line = line.trim();
                sum++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sum;
    }

    private boolean isValidFile(File file)
    {
        String suffix = file.getName();
        suffix = suffix.substring(suffix.lastIndexOf('.') + 1);
        return PRIFIXES.contains(suffix);
    }
}
