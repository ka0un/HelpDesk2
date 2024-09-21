package org.example.helpdesk2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DBConnectionTestServlet")
public class DBConnectionTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Step 1: Set the content type for the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;

        try {
            // Step 2: Initialize the DataSource using JNDI (if configured in context.xml)
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/helpdeskDB");

            // Step 3: Get a connection from the DataSource
            conn = ds.getConnection();

            // Step 4: Check if connection is valid
            if (conn != null && !conn.isClosed()) {
                out.println("<h3>Database connected successfully!</h3>");
            } else {
                out.println("<h3>Failed to connect to the database.</h3>");
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            // Step 5: Close the connection if it is open
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
