package com.example.jey_mvvm_rd_task.Repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jey_mvvm_rd_task.Database.Dao.RegisterDetailsDao;
import com.example.jey_mvvm_rd_task.Database.RegisterDetailsDatabase;
import com.example.jey_mvvm_rd_task.Model.RegisterDetails;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RegisterDetailsRepository {

    private static final String TAG = "RegisterDetailsRepository";

    private RegisterDetailsDao registerDetailsDao;
    private Flowable<List<RegisterDetails>> allRegisterDetails;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public RegisterDetailsRepository(Application application){
        RegisterDetailsDatabase registerDetailsDatabase = RegisterDetailsDatabase.getInstance(application);
        registerDetailsDao = registerDetailsDatabase.registerDetailsDao();



    }

    //Get all RegisterDetails
    public Flowable<List<RegisterDetails>> getAllRegisterDetails(){
        return registerDetailsDao.getAllRegisterDetails();
    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }


    //Insert RegisterDetails
    public void insertRegisterDetails(final RegisterDetails registerDetails){
        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                registerDetailsDao.insert(registerDetails);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                        isLoading.setValue(false);
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }
                });
    }

    //Update RegisterDetails
    public void updateRegisterDetails(final RegisterDetails registerDetails){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                registerDetailsDao.update(registerDetails);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Called");
                    }
                });
    }

    //Delete RegisterDetails
    public void deleteRegisterDetails(final RegisterDetails registerDetails){
        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                registerDetailsDao.delete(registerDetails);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: Called");
                        isLoading.setValue(false);
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+ e.getMessage());
                    }
                });
    }

    //Delete all Genre
    public void deleteAllRegisterDetails(){

        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                registerDetailsDao.deleteAllRegisterDetails();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                        deleteAllRegisterDetails();
                        isLoading.setValue(false);
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Called"+e.getMessage());
                    }
                });
    }






}