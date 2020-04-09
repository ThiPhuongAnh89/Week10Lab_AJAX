<%-- 
    Document   : notes
    Created on : 29-Feb-2020, 8:16:08 PM
    Author     : 794458
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Home page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/autosave.js"></script>
    </head>
    <body>
      
        <h1>Notes!</h1>
    <div>
        
        <h2>${errorMessage}</h2><br/>
        <table>
            <thead>
                <tr>
                    <th>Date created</th>
                    <th>Title</th>
                    
                </tr>
                
            </thead>
            
            
            <c:forEach var="note" items="${notes}">
            <tbody>
                <tr>
                    <td>${note.datecreated}|</td>
                    <td>${note.title}|</td>
                   
                    <td>
                        <form action="note" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="selectedNote" value="${note.noteid}">
                        </form>
                    </td>

                </tr>
            </tbody>
            </c:forEach>
                    
        </table>
    </div>
    <br>
    <c:if test="${idToBeDeleted != null}">
    <form action="note" method="post">
        
        <input type="submit" value="Delete note">
        <input type="hidden" name="action" value="Delete">
        <input type="hidden" name="selectedNote" value="${idToBeDeleted}" id="selectedNote">
    </form>
    </c:if>
<h2>${addorsave} Note:</h2>
    
    <form method="POST" action="note">
        <table>
                <tr>
                    <td><b>Title:</b></td>
                    
                    <td><input type="text" id="title" ${readonly} name="title" value="${titleEdit}"></td>
                </tr>
                <tr>
                    <td><b>Contents:</b></td>
                    <td>
                        <textarea id="content" name="content">${contentEdit}</textarea> <span id="message"></span>
                        
                    </td> 
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="${addorsave}" id="submitBTN">
                        <input type="hidden" name="action" value="${addorsave}">
                    </td>
                </tr>
            </table>
        </form>
    
    </body>
    
</html>

