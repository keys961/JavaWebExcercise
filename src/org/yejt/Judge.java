package org.yejt;

import java.util.concurrent.Callable;

public class Judge
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
            setMsg(statusCode);
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

    private String code;

    private int compiler;

    public Judge(String code, int compiler)
    {
        this.code = code;
        this.compiler = compiler;
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


}
