package com.ashiqur.mvvmexample.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


/**
 * The Database Singleton for this APP
 **/
/* comma separate the entity classes(pojo) inside the entities = {} this will convert each of the pojos in to a table inside this database */
@Database(entities = {Note.class}, version = 1) /* We need to have specify room the migration strategies if we need to increment the version of the database */
@TypeConverters(DateConverter.class)
public abstract class NoteDatabase extends RoomDatabase {

    private static String DB_NAME = "note_database";
    private static NoteDatabase instance;

    public abstract NoteDAO getNoteDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration() /* Here we are using destructive migration strategy i.e, whenever we make changes to the db and increment the version number,room will destroy the previous db and start over with the new version number */
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.getNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //TODO: might need to remove these
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}