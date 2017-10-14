package org.yejt.admin;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.yejt.contract.DatabaseContract;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminHome/AddProblem")
public class AddProblem extends HttpServlet
{
    private static final String PATH = "E:\\Programming\\Java\\WebHelloWorld\\web"; //May change

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<FileItem> items = getPostedItem(req);
        Map<String, String> reqMap = getInfo(items);
        String testPath = PATH + File.separator + "testcase" + File.separator + reqMap.get("id") + ".txt";
        String ansPath = PATH + File.separator + "answers" + File.separator + reqMap.get("id") + ".txt";
        if(!uploadFile(req, reqMap, items) || ! generateXml(req, reqMap))
        {
            File file = new File(testPath);
            if(file.exists())
                file.delete();
            file = new File(ansPath);
            if(file.exists())
                file.delete();
            resp.getWriter().print("<script>alert(\"Error occurred!\"); windows.location.href=\"/AdminHome\";</script>");
            return;
        }
        try
        {
            //Map<String, String> reqMap = getInfo(req);
            Connection connection = DatabaseContract.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO problem VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, Integer.parseInt(reqMap.get("id")));
            preparedStatement.setString(2, reqMap.get("title"));
            preparedStatement.setString(3, "/problems/" + reqMap.get("id") + ".xml");
            preparedStatement.setString(4, "/answers/" + reqMap.get("id") + ".txt");
            preparedStatement.setString(5, "/testcase/" + reqMap.get("id") + ".txt");
            preparedStatement.executeUpdate();

            resp.getWriter().print("<script>alert(\"Add problem success!\"); windows.location.href=\"/AdminHome\";</script>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            File file = new File(testPath);
            if(file.exists())
                file.delete();
            file = new File(ansPath);
            if(file.exists())
                file.delete();
            resp.getWriter().print("<script>alert(\"Error occurred!\"); windows.location.href=\"/AdminHome\";</script>");
        }
    }

    private boolean generateXml(HttpServletRequest req, Map<String, String> reqMap)
    {

        int pid = Integer.parseInt(reqMap.get("id"));
        String title = reqMap.get("title");
        String[] descriptions = reqMap.get("description").split("\r\n");
        String inputSpecification = reqMap.get("input");
        String outputSpecification = reqMap.get("output");


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element element = document.createElement("problem");
            document.appendChild(element);

            Element titleNode = document.createElement("title");
            titleNode.setTextContent(title);

            Element inputNode = document.createElement("input");
            inputNode.setTextContent(inputSpecification);

            Element outputNode = document.createElement("output");
            outputNode.setTextContent(outputSpecification);

            List<Element> descriptionList = new ArrayList<>();
            for(String d : descriptions)
            {
                Element node = document.createElement("body");
                node.setTextContent(d);
                descriptionList.add(node);
            }

            element.appendChild(titleNode);
            element.appendChild(inputNode);
            element.appendChild(outputNode);
            for(Element e: descriptionList)
                element.appendChild(e);

            TransformerFactory factory1 = TransformerFactory.newInstance();
            Transformer transformer = factory1.newTransformer();
//            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "system");
//            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "public");
            transformer.setOutputProperty(OutputKeys.INDENT, "true");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(new DOMSource(document), new StreamResult(
                    new FileOutputStream(new File(PATH + File.separator + "problems" +
                        File.separator + pid + ".xml"))
            ));

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private List<FileItem> getPostedItem(HttpServletRequest req)
    {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024 * 3);
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024 * 1024 * 30);
        servletFileUpload.setSizeMax(1024 * 1024 * 30);
        servletFileUpload.setHeaderEncoding("UTF-8");

        try
        {
            List<FileItem> list = servletFileUpload.parseRequest(req);
            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getInfo(List<FileItem> items)
    {
        Map<String, String> map = new HashMap<>();

        try
        {
            for(FileItem item : items)
                if(item.isFormField())
                    map.put(item.getFieldName(), item.getString("UTF-8"));

            return map;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private boolean uploadFile(HttpServletRequest req, Map<String, String> reqMap, List<FileItem> list)
    {
        int fileId = Integer.parseInt(reqMap.get("id"));
//        if(!ServletFileUpload.isMultipartContent(req))
//            return false;

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024 * 3);
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024 * 1024 * 30);
        servletFileUpload.setSizeMax(1024 * 1024 * 30);
        servletFileUpload.setHeaderEncoding("UTF-8");

        try
        {
            //List<FileItem> items = servletFileUpload.parseRequest(req);
            if(list != null && list.size() > 0)
            {
                File answerFile = new File(PATH + File.separator + "answers"
                        + File.separator + fileId + ".txt");//2
                File testFile = new File(PATH + File.separator + "testcase"
                        + File.separator + fileId + ".txt");//1

                int cnt = 0;
                for(FileItem item : list)
                {
                    if(!item.isFormField())
                    {
                        if(cnt == 0)
                        {
                            item.write(testFile);
                            cnt++;
                        }
                        else
                        {
                            item.write(answerFile);
                        }
                    }
                }
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
