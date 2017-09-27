package org.yejt.contract;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.sun.istack.internal.Nullable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yejt.contract.DatabaseContract;

public class Utils
{
    public static boolean isLoggedIn(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("username") == null || session.getAttribute("password") == null)
            return false;
        return true;
    }

    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] md5Array = md5.digest();
            return bytesToHex1(md5Array);
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
    @Nullable
    private static String bytesToHex1(byte[] md5Array)
    {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < md5Array.length; i++)
        {
            int temp = 0xff & md5Array[i];
            String hexString = Integer.toHexString(temp);
            if (hexString.length() == 1)
            {
                strBuilder.append("0").append(hexString);
            }
            else
            {
                strBuilder.append(hexString);
            }
        }
        return strBuilder.toString();
    }
}
