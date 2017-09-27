package org.yejt;

import java.util.concurrent.Callable;

public class Judge implements Callable<Judge.Status>
{
    public class Status
    {
        private boolean isPassed;

        private String msg;

        Status(boolean isPassed, String msg)
        {
            this.isPassed = isPassed;
            this.msg = msg;
        }

        public String getMsg()
        {
            return msg;
        }

        public boolean isPassed()
        {
            return isPassed;
        }
    }

    private String code;

    private int compiler;

    public Judge(String code, int compiler)
    {
        this.code = code;
        this.compiler = compiler;
    }

    @Override
    public Judge.Status call() throws Exception
    {
        //TODO
        return null;//Do test
    }
}
