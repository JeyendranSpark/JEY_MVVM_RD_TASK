package com.example.jey_mvvm_rd_task.View;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jey_mvvm_rd_task.Adapter.RegisterListAdapter;
import com.example.jey_mvvm_rd_task.Model.RegisterDetails;
import com.example.jey_mvvm_rd_task.R;
import com.example.jey_mvvm_rd_task.ViewModel.RegisterDetailsActivityViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterDetailsActivity extends AppCompatActivity implements RegisterListAdapter.OnRegisterClickListener {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @Nullable
    @BindView(R.id.progress)
    ProgressBar mProgressbar;

    private RegisterDetailsActivityViewModel registerDetailsActivityViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RegisterListAdapter registerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.setHasFixedSize(true);

        //ViewModel
        registerDetailsActivityViewModel = ViewModelProviders.of(this).get(RegisterDetailsActivityViewModel.class);

        //Disposable for avoid memory leak
        Disposable disposable = registerDetailsActivityViewModel.getAllRegisterDetails().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RegisterDetails>>() {
                    @Override
                    public void accept(List<RegisterDetails> registerDetails) throws Exception {
                        Log.d(TAG, "accept: Called");
                        setDataToRecyclerView(registerDetails);
                    }
                });


        //Add Disposable
        compositeDisposable.add(disposable);

        //Check Loading State
        registerDetailsActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d(TAG, "onChanged: " + aBoolean);
                if (aBoolean != null) {
                    if (aBoolean) {
                        mProgressbar.setVisibility(View.VISIBLE);
                    } else {
                        mProgressbar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void setDataToRecyclerView(List<RegisterDetails> registerDetails) {
        registerListAdapter = new RegisterListAdapter(registerDetails);
        registerListAdapter.setItemClickListener(this);
        recycler_view.setAdapter(registerListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            deleteRegisterDetails();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(RegisterDetailsActivity.this, MainActivity.class));
    }

    private void deleteRegisterDetails() {
        registerDetailsActivityViewModel.deleteAllRegisterDetails();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Remove Disposable
        compositeDisposable.dispose();
    }


    @Override
    public void onRegisterClick(RegisterDetails registerDetails) {
        registerDetailsActivityViewModel.deleteRegisterDetails(registerDetails);

    }
}