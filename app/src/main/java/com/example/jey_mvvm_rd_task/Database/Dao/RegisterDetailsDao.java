package com.example.jey_mvvm_rd_task.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jey_mvvm_rd_task.Model.RegisterDetails;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RegisterDetailsDao {

    //Insert new RegisterDetails
    @Insert
    void insert(RegisterDetails registerDetails);

    //Update existing RegisterDetails
    @Update
    void update(RegisterDetails registerDetails);

    //Delete Specific RegisterDetails and also delete RegisterDetails
    @Delete
    void delete(RegisterDetails registerDetails);

    //Delete all Genre from table
    @Query("DELETE FROM register_table")
    void deleteAllRegisterDetails();

    //Get all Genre from table
    @Query("SELECT * FROM register_table")
    Flowable<List<RegisterDetails>> getAllRegisterDetails();
}
