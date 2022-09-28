package first.com.wd;
import java.io.*;
import  javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class hello extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    public void service (HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        PrintWriter pw=res.getWriter();
        pw.println("<h1></h1>");
    }
}
