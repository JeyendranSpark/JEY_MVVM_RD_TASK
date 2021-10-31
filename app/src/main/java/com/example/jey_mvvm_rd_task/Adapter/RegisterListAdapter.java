package com.example.jey_mvvm_rd_task.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jey_mvvm_rd_task.Model.RegisterDetails;
import com.example.jey_mvvm_rd_task.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RegisterListAdapter extends RecyclerView.Adapter<RegisterListAdapter.AdapterViewHolder> {

    private List<RegisterDetails> registerList;
    private OnRegisterClickListener onRegisterClickListener;

    public RegisterListAdapter(List<RegisterDetails> registerList) {
        this.registerList = registerList;
    }

    public void setItemClickListener(OnRegisterClickListener onRegisterClickListener){
        this.onRegisterClickListener = onRegisterClickListener;

    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.register_item_list,null);
        AdapterViewHolder adapterViewHolder = new AdapterViewHolder(view,onRegisterClickListener);
        return adapterViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        RegisterDetails singleRegister = registerList.get(position);
        holder.mName.setText("Name: "+singleRegister.getName());
        holder.mEmail.setText("Email: "+singleRegister.getEmail());
        holder.mDob.setText("Dob: "+singleRegister.getDob());
        holder.mPassword.setText("Password: "+singleRegister.getPassword());
        holder.mType.setText("Type: "+singleRegister.getType());
    }

    public RegisterDetails getRegisterDetailAt(int position){
        RegisterDetails registerDetails = registerList.get(position);
        registerDetails.setUid(registerList.get(position).getUid());
        return registerDetails;
    }

    @Override
    public int getItemCount() {
        return registerList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mName, mEmail, mDob, mPassword, mType;
        private MaterialCardView mCardView;
        private ImageView iv_delete_register;
        private OnRegisterClickListener onRegisterClickListener;

        public AdapterViewHolder(@NonNull View itemView, OnRegisterClickListener onRegisterClickListener) {
            super(itemView);
            this.onRegisterClickListener = onRegisterClickListener;
            mCardView = itemView.findViewById(R.id.card_view);
            iv_delete_register = itemView.findViewById(R.id.iv_delete_register);
            mName = itemView.findViewById(R.id.tv_name);
            mEmail = itemView.findViewById(R.id.tv_email);
            mDob = itemView.findViewById(R.id.tv_dob);
            mPassword = itemView.findViewById(R.id.tv_Password);
            mType = itemView.findViewById(R.id.tv_Type);
            iv_delete_register.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            RegisterDetails registerDetails = registerList.get(position);
            onRegisterClickListener.onRegisterClick(registerDetails);

        }
    }

    public interface OnRegisterClickListener{
        void onRegisterClick(RegisterDetails registerDetails);
    }
}
