package servlet;

import com.opentok.exception.OpenTokException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import serverLogic.Server;


@WebServlet(
        name = "ConnectServlet",
        urlPatterns = {"/connect"}
    )
public class ConnectServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
    
        try {
            ServletOutputStream out = resp.getOutputStream();
            out.write(Server.getInstance().onClientConnecting().toJSONString().getBytes());
            out.flush();
            out.close();
        } catch (OpenTokException ex) {
            Logger.getLogger(ConnectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}