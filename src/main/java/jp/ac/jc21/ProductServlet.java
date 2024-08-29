package jp.ac.jc21;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = { "/item" })
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    final String dbServer = "192.168.54.231";
    final String dbPort = "3306";
    final String dbName = "test2023";
    final String user = "test2023";
    final String pass = "test2023";
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://"+dbServer+"/"+dbName;
        response.setContentType("text/html;charset=UTF-8");
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
 
            // メーカーの取得
            String sql = "SELECT MAKER_NAME FROM MAKER";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
 
            ArrayList<String> makers = new ArrayList<>();
            while(rs.next()) {
                makers.add(rs.getString("MAKER_NAME"));
            }
            request.setAttribute("makers", makers);
 
            // 全製品の取得または選択されたメーカーに基づく製品の取得
            String selectedMaker = request.getParameter("MAKER_NAME");
            if (selectedMaker == null || selectedMaker.isEmpty()) {
                sql = "SELECT PRODUCT_CODE, PRODUCT_NAME, MAKER_CODE FROM PRODUCT";
                statement = conn.prepareStatement(sql);
            } else {
                sql = "SELECT PRODUCT_CODE, PRODUCT_NAME, MAKER_CODE FROM PRODUCT WHERE MAKER_CODE IN (SELECT MAKER_CODE FROM MAKER WHERE MAKER_NAME = ?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1, selectedMaker);
            }
            rs = statement.executeQuery();
 
            ArrayList<String[]> products = new ArrayList<>();
            while(rs.next()) {
                String[] product = new String[3];
                product[0] = rs.getString("PRODUCT_CODE");
                product[1] = rs.getString("PRODUCT_NAME");
                product[2] = rs.getString("MAKER_CODE");
                products.add(product);
            }
            request.setAttribute("products", products);
 
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product.jsp");
            rd.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}