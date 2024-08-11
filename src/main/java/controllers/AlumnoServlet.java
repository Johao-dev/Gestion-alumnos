package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.Alumn;
import models.AlumnDAO;

@WebServlet("/AlumnsServlet/*")
public class AlumnoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AlumnDAO alumnDao;

    @Override
    public void init() {
        alumnDao = new AlumnDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();
        
        if(action == null) {
            action = "/";
        }
        
        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertAlumn(req, resp);
                break;
            case "/delete":
                deleteAlumn(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/update":
                updateAlumn(req, resp);
                break;
            default:
                listAlumn(req, resp);
                break;
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher rs = req.getRequestDispatcher("/views/AlumnsFormView.jsp");
        
        try {
            rs.forward(req, resp);
        } catch (ServletException | IOException ex) {
            errorWhileProccesing(req, resp);
        }
    }

    private void insertAlumn(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        char gener = req.getParameter("gener").charAt(0);
        int age = Integer.parseInt(req.getParameter("age"));
        
        Alumn newAlumn = new Alumn(code, name, gener, age);
        alumnDao.createAlumn(newAlumn);
        
        try {
            resp.sendRedirect("list");
        } catch (IOException ex) {
            errorWhileProccesing(req, resp);
        }
        
    }

    private void deleteAlumn(HttpServletRequest req, HttpServletResponse resp) {
        int alumnId = Integer.parseInt(req.getParameter("alumnId"));
        alumnDao.deleteAlumn(alumnId);
        
        try {
            resp.sendRedirect("list");
        } catch (IOException ex) {
            errorWhileProccesing(req, resp);
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int alumnId = Integer.parseInt(req.getParameter("alumnId"));
        Alumn existingAlumn = alumnDao.getAlumnById(alumnId);
        req.setAttribute("alumn", existingAlumn);
        RequestDispatcher rs = req.getRequestDispatcher("/views/AlumnsFormView.jsp");
        
        try {
            rs.forward(req, resp);
        } catch (ServletException | IOException ex) {
            errorWhileProccesing(req, resp);
        }
    }

    private void updateAlumn(HttpServletRequest req, HttpServletResponse resp) {
        int alumnId = Integer.parseInt(req.getParameter("alumnId"));
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        char gener = req.getParameter("gener").charAt(0);
        int age = Integer.parseInt(req.getParameter("age"));
        
        Alumn updateAlumn = new Alumn(alumnId, code, name, gener, age);
        alumnDao.updateAlumn(updateAlumn);
        
        try {
            resp.sendRedirect("list");
        } catch (IOException ex) {
            errorWhileProccesing(req, resp);
        }
    }

    private void listAlumn(HttpServletRequest req, HttpServletResponse resp) {
        List<Alumn> alumns = alumnDao.getAllAlumns();
        req.setAttribute("alumns", alumns);
        RequestDispatcher rs = req.getRequestDispatcher("/views/AlumnsView.jsp");
        
        try {
            rs.forward(req, resp);
        } catch (ServletException | IOException ex) {
            errorWhileProccesing(req, resp);
        }
    }
    
    private void errorWhileProccesing(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("errorMessage", "Ocurrio un error al procesar la solicitud");
        try {
            req.getRequestDispatcher("/WEB-INF/Error.jsp").forward(req, resp);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}