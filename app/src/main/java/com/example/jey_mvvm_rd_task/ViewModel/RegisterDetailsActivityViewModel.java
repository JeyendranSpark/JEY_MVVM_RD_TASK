package com.example.jey_mvvm_rd_task.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.jey_mvvm_rd_task.Model.RegisterDetails;
import com.example.jey_mvvm_rd_task.Repository.RegisterDetailsRepository;

import java.util.List;

import io.reactivex.Flowable;

public class RegisterDetailsActivityViewModel extends AndroidViewModel {

    private RegisterDetailsRepository registerDetailsRepository;

    public RegisterDetailsActivityViewModel(@NonNull Application application) {
        super(application);

        registerDetailsRepository = new RegisterDetailsRepository(application);
    }

    //Get all RegisterDetails
    public Flowable<List<RegisterDetails>> getAllRegisterDetails(){
        return registerDetailsRepository.getAllRegisterDetails();
    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return registerDetailsRepository.getIsLoading();
    }

    //Insert RegisterDetails
    public void insert(RegisterDetails registerDetails){
        registerDetailsRepository.insertRegisterDetails(registerDetails);
    }

    //Update RegisterDetails
    public void updateRegisterDeatils(RegisterDetails registerDetails){
        registerDetailsRepository.updateRegisterDetails(registerDetails);
    }

    //Delete RegisterDetails
    public void deleteRegisterDetails(RegisterDetails registerDetails){
        registerDetailsRepository.deleteRegisterDetails(registerDetails);
    }

    //Delete All RegisterDetails
    public void deleteAllRegisterDetails()
    {
        registerDetailsRepository.deleteAllRegisterDetails();
    }
}
