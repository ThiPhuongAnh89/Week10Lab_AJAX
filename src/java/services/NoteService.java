/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.NoteDB;
import java.util.Date;
import java.util.List;
import models.Note;

/**
 *
 * @author 794458
 */
public class NoteService {
private NoteDB noteDB;  
public NoteService()
{
    noteDB = new NoteDB();
}
public Note get(int noteid) throws Exception {
        return noteDB.get(noteid);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteid, String title, String contents) throws Exception {
        Note note = noteDB.get(noteid);
        note.setNoteid(noteid);
        note.setTitle(title);
        note.setContents(contents);
        return noteDB.update(note);
    }

    public int delete(int noteid) throws Exception {
        Note deletedNote = noteDB.get(noteid);
        // do not allow the admin to be deleted
//        if (deletedUser.getRole().getRoleid() == 1) {
//            return 0;
//        }
        return noteDB.delete(deletedNote);
    }

    public int insert(String title, String contents) throws Exception {
        Note note = new Note(0, new Date(), title, contents);
        return noteDB.insert(note);
    }
}
