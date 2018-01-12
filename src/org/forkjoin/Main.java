package org.forkjoin;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main
{
    public static void main(String[] args)
    {
        ForkJoinPool pool = new ForkJoinPool();
        String path = "";
        Scanner in = new Scanner(System.in);
        path = in.nextLine();
        CountLinesTask task = new CountLinesTask(new File(path));
        Future<Integer> result = pool.submit(task);

        try
        {
            System.out.println("Lines = " + result.get());
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}
