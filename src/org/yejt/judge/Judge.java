package org.yejt.judge;

import java.util.concurrent.Callable;

public abstract class Judge
{
    public class Status
    {
        private boolean isPassed;

        private String msg;

        private int statusCode;

        Status(boolean isPassed, int statusCode)
        {
            this.isPassed = isPassed;
            this.statusCode = statusCode;
            this.msg = setMsg(statusCode);
        }

        public String getMsg()
        {
            return msg;
        }

        public boolean isPassed()
        {
            return isPassed;
        }

        public int getStatusCode()
        {
            return this.statusCode;
        }


    }

    protected String code;

    protected int pid;

    protected String username;

    protected Judge(String code, String username, int pid)
    {
        this.code = code;
        this.username = username;
        this.pid = pid;
    }

    public static String setMsg(int statusCode)
    {
        String msg = "";
        switch (statusCode)
        {
            case 0:
                msg = "AC"; break;
            case 1:
                msg = "WA"; break;
            case 2:
                msg = "TLE"; break;
            case 3:
                msg = "MLE"; break;
            case 4:
                msg = "RE"; break;
            case 5:
                msg = "OLE"; break;
            case 6:
                msg = "CE"; break;
            default:
                msg = "Unknown Error"; break;
        }
        return msg;
    }

    public abstract Status judge();
}
