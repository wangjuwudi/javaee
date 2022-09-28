//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import first.com.wd.student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

//@WebServlet(value = {"/first/find"},name="finder")
public class FinderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;// contact table
    private ArrayList<Map<String, Object>> contacts = new ArrayList();
   /* public student[] st=new student[100];
    public int num=0;*/
   @Override
    //实现建立Excel表连接
    public void init() throws ServletException {
        try {
            String files = getInitParameter("contacts");

            files = files.trim();
            files = files.replace('，', ',');
            String[] file_name_array = files.split(",");

            for (int i = 0; i < file_name_array.length; i++) {
                String file_name = file_name_array[i];
                file_name = file_name.trim();
                if (file_name.length() == 0) {
                    continue;
                }

                File file = new File(getServletContext().getRealPath("/WEB-INF/contacts/" + file_name));

                FileInputStream fis = new FileInputStream(file);

                Workbook book = null;
                try {
                    book = new XSSFWorkbook(fis);
                } catch (Exception ex) {
                    book = new HSSFWorkbook(fis);
                }

                Sheet sheetAt = book.getSheetAt(0);

                for (Row row : sheetAt) {
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    if (row == null) {
                        break;
                    }

                    Cell cell = row.getCell(0);

                    if (cell == null) {
                        break;
                    }

                    double no = row.getCell(0).getNumericCellValue();
                    String id = row.getCell(1).getStringCellValue();
                    String name = row.getCell(2).getStringCellValue();
                    String strClass = "";
                    String mobile = "";
                    String email = "";

                    cell = row.getCell(3);
                    if (cell != null) {
                        strClass = cell.getStringCellValue();
                    }

                    cell = row.getCell(4);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        mobile = cell.getStringCellValue();
                    }

                    cell = row.getCell(5);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        email = cell.getStringCellValue();
                    }

                    Map<String, Object> record = new HashMap<String, Object>();
                    record.put("id", id);
                    record.put("name", name);
                    record.put("gender", null);
                    record.put("class", strClass);
                    record.put("mobile", mobile);
                    record.put("email", email);

                    contacts.add(record);

                }

                book.close();
                fis.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();


        request.setCharacterEncoding("utf-8");
        response.setContentType("text/htm;charset=utf-8");
        String input=request.getParameter("information");
        HttpSession session=request.getSession();
        if(input==null){
            response.getWriter().println("input is null please try again!");
        }
        else {
            int y = 0;
            Iterator<Map<String, Object>> it = contacts.iterator();
            while (it.hasNext()) {

                Map<String, Object> x = it.next();
                String id=(String) x.get("id");
                String name=(String) x.get("name");
                String sex=sex(name);
                String Class=(String) x.get("class");
                if(sex.equals("woman")){
                    name=name.substring(0,name.length()-1);
                }
                String tel=(String) x.get("mobile");
                String email=(String) x.get("email");
                int t = 0;
                if (id.startsWith(input)) t=1;
                if(name.startsWith(input)) t=1;
                if(tel.startsWith(input)) t=1;
                if(email.startsWith(input)) t=1;
                if(Class.startsWith(input)) t=1;
                if(t==1) {
                  /*  st[num].setId(id);
                    st[num].setName(name);
                    st[num].setSex(sex);
                    st[num].setClasses(Class);
                    st[num].setTel(tel);
                    st[num].setEmail(email);
                    num++;*/
                    response.getWriter().println(id);
                    response.getWriter().println(name);
                    response.getWriter().println(sex);
                    response.getWriter().println(x.get("class"));
                    response.getWriter().println(tel);
                    response.getWriter().println(email);
                    y++;
                }
            }
            response.getWriter().println("<a href=\"first\" target=\"/first/\">");
            //session.setAttribute("sts",st);
            if (y == 0) {
                response.getWriter().println("no this person");
            }
        }
    }

    //判断性别
    public String sex (String name){
        String last=name.substring(name.length()-1);
        if("*".equals(last)){
            return "woman";
        }
        return "man";
    }

    //@Override
    /*public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
       // super.service(req, res);
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        PrintWriter pw=res.getWriter();
        pw.println("hi");
        String page=null;
        page=req.getParameter("page");
        pw.println("第 "+page+"页");
        int x= Integer.parseInt(page) ;
        x++;
        page=""+x;
        pw.println("<a href=\"/first/find?page="+page+"\">");
        //doGet(req,res);
    }*/
}
