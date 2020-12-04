package ru.sscc.ssd.hpccloud.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.sscc.ssd.hpccloud.ApplicationsPageActivity;
import ru.sscc.ssd.hpccloud.R;
import ru.sscc.ssd.hpccloud.UserInfoPageActivity;

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.ApplicationsHolder>{
    private static int itemsCount;
    private int itemsAmount;
    private static ArrayList<String> keys = ApplicationsPageActivity.getKeys();
    private static ArrayList<String> values = ApplicationsPageActivity.getValues();
    //private static int size = ApplicationsPageActivity.getVSize();

    private int valueCount;

    public ApplicationsAdapter(int size){
        itemsAmount = size;
        itemsCount = 0;
        valueCount = 0;
    }
    @NonNull
    @Override
    public ApplicationsAdapter.ApplicationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.applications_item, parent, false);
        ApplicationsAdapter.ApplicationsHolder holder = new ApplicationsAdapter.ApplicationsHolder(view);

        for(int keyCount = 0; keyCount < keys.size(); keyCount++, valueCount++) {
            if (keys.get(keyCount).equals("id")) {
                holder.id.setText(values.get(valueCount));
            }
            if (keys.get(keyCount).equals("name")) {
                holder.name.setText(values.get(valueCount));
            }
            if (keys.get(keyCount).equals("last_modify_time")) {
                holder.time.setText(values.get(valueCount));
            }

        }
        itemsCount++;

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationsAdapter.ApplicationsHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return itemsAmount;
    }

    class ApplicationsHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView name;
        TextView time;

        public ApplicationsHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id);
            name = itemView.findViewById(R.id.tv_name);
            time = itemView.findViewById(R.id.tv_time);
        }

        void bind(int i)
        {
            for(int keyCount = 0; keyCount < keys.size(); keyCount++, valueCount++) {
                if(values.size() > valueCount) {
                    if (keys.get(keyCount).equals("id")) {
                        id.setText(values.get(valueCount));//
                    }
                    if (keys.get(keyCount).equals("name")) {
                        name.setText(values.get(valueCount));
                    }
                    if (keys.get(keyCount).equals("last_modify_time")) {
                        time.setText(values.get(valueCount));
                    }
                }
            }
            //id.setText(values.get(i));
            //name.setText(values.get(i));
            //time.setText(values.get(i));
        }

    }
}
