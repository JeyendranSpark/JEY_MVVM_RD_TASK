package com.example.jey_mvvm_rd_task.View;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jey_mvvm_rd_task.Model.RegisterDetails;
import com.example.jey_mvvm_rd_task.R;
import com.example.jey_mvvm_rd_task.ViewModel.RegisterDetailsActivityViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Nullable
    @BindView(R.id.et_name)
    TextInputLayout et_name;
    @Nullable
    @BindView(R.id.et_email)
    TextInputLayout et_email;
    @Nullable
    @BindView(R.id.et_dob)
    TextInputLayout et_dob;
    @Nullable
    @BindView(R.id.et_Password)
    TextInputLayout et_Password;
    @Nullable
    @BindView(R.id.et_type)
    TextInputLayout et_type;
    @Nullable
    @BindView(R.id.btn_save)
    MaterialButton btn_save;
    @Nullable
    @BindView(R.id.img_developer_call)
    ImageView img_developer_call;

    private static final String TAG = "MainActivity";

    private RegisterDetailsActivityViewModel registerDetailsActivityViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    RegisterDetails registerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        onCreateRegisterListener = (OnCreateRegisterListener) this;

        //ViewModel
        registerDetailsActivityViewModel = ViewModelProviders.of(this).get(RegisterDetailsActivityViewModel.class);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Objects.requireNonNull(et_name).getEditText().getText().toString())) {
                    Toast.makeText(MainActivity.this, "Name is required", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(et_email).getEditText().getText().toString())) {
                    Toast.makeText(MainActivity.this, "Email is required", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(et_dob).getEditText().getText().toString())) {
                    Toast.makeText(MainActivity.this, "Date of birth is required", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(et_Password).getEditText().getText().toString())) {
                    Toast.makeText(MainActivity.this, "Password is required", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(et_type).getEditText().getText().toString())) {
                    Toast.makeText(MainActivity.this, "Type is required", Toast.LENGTH_LONG).show();
                } else {

                    String name = et_name.getEditText().getText().toString();
                    String email = et_email.getEditText().getText().toString();
                    String dob = et_dob.getEditText().getText().toString();
                    String password = et_Password.getEditText().getText().toString();
                    String type = et_type.getEditText().getText().toString();


                    registerDetails = new RegisterDetails(name, email, dob, password, type);
                    registerDetailsActivityViewModel.insert(registerDetails);

                    Intent intent = new Intent(MainActivity.this, RegisterDetailsActivity.class);
                    startActivity(intent);

                }

            }
        });


        @SuppressLint("ResourceType") Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anime_shake_effect);
        img_developer_call.startAnimation(shake);
        img_developer_call.startAnimation(shake);


        img_developer_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "7598831500";
                if (phoneNumber != null && phoneNumber.length() > 5) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.fromParts("tel", phoneNumber, null));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Phone number invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Remove Disposable
        compositeDisposable.dispose();
    }


}