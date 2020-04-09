/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.NoteDB;
import dataaccess.NotesDBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author 794458
 */
@WebServlet(name = "NoteServlet", urlPatterns = {"/note"})
public class NoteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet NoteServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet NoteServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String action = request.getParameter("action");
        NoteDB nb = new NoteDB();
        List<Note> notes = null;
        
        if(action == null)
        {
            request.setAttribute("addorsave", "Add");
        }
        else
        if(action.equals("edit"))
        {
            try{
            int selectedNote = Integer.parseInt(request.getParameter("selectedNote"));
            Note note = nb.get(selectedNote);
            request.setAttribute("titleEdit", note.getTitle());
            request.setAttribute("contentEdit", note.getContents());
            request.setAttribute("idToBeDeleted", note.getNoteid());
            request.setAttribute("idToBeSaved", note.getNoteid());
            request.setAttribute("readonly", "readonly"); 
            request.setAttribute("disabled", "disabled");
        }
            catch(NumberFormatException e)
            {
                request.setAttribute("errorMessage", "Error on parsing selected note's id");
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("addorsave", "Return to add note");
            
            
            
        }
        else
        {
//            Note newNote=null;
//            String selectNote = request.getParameter("selectedNote");
//            String title = request.getParameter("title");
//            String content = request.getParameter("content");
//            int selectedNote = Integer.parseInt(selectNote);
//            try {
//                Note oldNote = nb.get(selectedNote);
//                newNote = new Note(oldNote.getNoteid(), oldNote.getDatecreated(), title, content);
//                nb.update(newNote);
//                request.setAttribute("errorMessage", "Update successfully!");
//                request.setAttribute("readonly", ""); 
//            } catch (NotesDBException ex) {
//                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //response.getWriter().write("Updated");
            return;
        }
            
        
        
    try 
        {
            notes = nb.getAll();
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        List<Note> notes = null;
        NoteDB nb = new NoteDB();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Note newNote;
        int selectedNote=0;
        String selectNote;
        String changedContent="";
        if(action.equals("Add"))
        {
            newNote = new Note(0, new Date(), title, content);
            try {
                nb.insert(newNote);
                request.setAttribute("errorMessage", "Add successfully!");
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        if(action.equals("save")){
            selectNote = request.getParameter("id");
            selectedNote = Integer.parseInt(selectNote);
            try {
                Note oldNote = nb.get(selectedNote);
                newNote = new Note(oldNote.getNoteid(), oldNote.getDatecreated(), oldNote.getTitle(), content);
                nb.update(newNote);
                request.setAttribute("errorMessage", "Update successfully!");
                request.setAttribute("readonly", ""); 
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.getWriter().write("Updated");
            
                        
            return;
        }
        else
            if(action.equals("Delete"))
        {
            selectNote = request.getParameter("selectedNote");
            selectedNote = Integer.parseInt(selectNote);
            try {
                newNote = nb.get(selectedNote);
                nb.delete(newNote);
                request.setAttribute("errorMessage", "Delete successfully!");
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        else
                if(action.equals("Return to add note"))
            {
                //getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
                doGet(request,response);
            }
        else
                    if(action.equals("reset"))
                    {
        
            try {
            //processRequest(request, response);
            selectNote = request.getParameter("id");
            changedContent = nb.get(selectNote).getContents();
            if(!content.equals(changedContent))
            {
                request.setAttribute("content", nb.get(selectNote).getContents());
                getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
            }
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                    }
    
     try 
        {
            request.setAttribute("addorsave", "Add");
            notes = nb.getAll();
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
