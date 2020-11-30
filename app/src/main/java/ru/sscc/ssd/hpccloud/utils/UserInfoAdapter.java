package ru.sscc.ssd.hpccloud.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;

import ru.sscc.ssd.hpccloud.R;
import ru.sscc.ssd.hpccloud.UserInfoPageActivity;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoHolder> {

    private static int count;
    private int numberitems;
    private static LinkedHashMap<String , String> userInfo = UserInfoPageActivity.getUserInfo();

    public UserInfoAdapter(int size){
        numberitems = size;
        count = 0;
    }
    @NonNull
    @Override
    public UserInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_info_item, parent, false);

        UserInfoHolder holder = new UserInfoHolder(view);
        holder.value.setText("aao " + count);//
        count++;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return numberitems;
    }

    class UserInfoHolder extends RecyclerView.ViewHolder{

        TextView key;
        TextView value;

        public UserInfoHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.textViewKey);
           value = itemView.findViewById(R.id.textViewValue);

        }

        void bind(int i)
        {
            key.setText(String.valueOf(i));
           // value.setText(String.);
        }

    }

}
