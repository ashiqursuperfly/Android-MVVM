package com.ashiqur.mvvmexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ashiqur.mvvmexample.db.Note;
import com.ashiqur.mvvmexample.db.NoteDAO;
import com.ashiqur.mvvmexample.db.NoteDatabase;

import java.util.List;

/** Even though we can use Room with the DAO abstract methods we created in the Database class
 *  Its better to use a intermediary class in which we keep the instances of the DAO(s) and provide the wrappers for calling the dao methods
 *  e.g: doing db operations in background using AsyncTasks
 *  Also, we can cache the data and keep them in this class as well.
 * **/
public class NoteRepository {
    private NoteDAO noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.getNoteDao();
        allNotes = noteDao.getAllNotes(); // NOTE: this are all abstract methods, but how are we calling them ? Room has provided implementations for them that we don't need to bother about.
        // TODO: why calling noteDao method getAllNotes() without an asynctask? We called all other noteDao methods using asyntTask e.g: noteDao.update(),noteDao.delete() etc

    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private UpdateNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }

    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private DeleteNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private DeleteAllNotesAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
