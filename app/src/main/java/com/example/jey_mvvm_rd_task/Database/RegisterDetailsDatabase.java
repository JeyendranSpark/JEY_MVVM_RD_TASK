package com.example.jey_mvvm_rd_task.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jey_mvvm_rd_task.Database.Dao.RegisterDetailsDao;
import com.example.jey_mvvm_rd_task.Model.RegisterDetails;

@Database(entities = {RegisterDetails.class}, version = 1,exportSchema = false)
public abstract class RegisterDetailsDatabase extends RoomDatabase {

    private static RegisterDetailsDatabase instance;

    public abstract RegisterDetailsDao registerDetailsDao();

    public static synchronized RegisterDetailsDatabase getInstance(Context context){
        if(instance==null){
            //If instance is null that's mean database is not created and create new database
            instance = Room.databaseBuilder(context.getApplicationContext(),RegisterDetailsDatabase.class,"register_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
