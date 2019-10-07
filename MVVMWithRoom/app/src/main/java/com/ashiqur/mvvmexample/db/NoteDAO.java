package com.ashiqur.mvvmexample.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// "@Dao" => tell room this interface is a Dao(Data Access Object). Generally, we should create a Dao for each entity
@Dao
public interface NoteDAO {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    // We use "@Query" Annotation when we want Room to do our custom queries(this allows room to do so much more than the insert,delete,update)
    @Query("DELETE FROM "+NoteConstants.TABLE_NAME)
    void deleteAllNotes();

    @Query("SELECT * FROM "+NoteConstants.TABLE_NAME+" ORDER BY "+NoteConstants.ATTR_PRIORITY+ " DESC")
    LiveData<List<Note>> getAllNotes();
}
