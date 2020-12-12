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
    private int itemsCount;
    private int itemsAmount;
    private static ArrayList<String> keys;
    private static ArrayList<String> values;
    private int valueCount;

    public ApplicationsAdapter(int size){
        keys = ApplicationsPageActivity.getKeys();
        values = ApplicationsPageActivity.getValues();

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

        TextView name;
        TextView time;

        public ApplicationsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvAppName);
            time = itemView.findViewById(R.id.tvAppTime);
        }

        void bind(int i)
        {
            for(int keyCount = 0; keyCount < keys.size(); keyCount++, valueCount++) {///////

                if (keys.get(keyCount).equals("name")) {
                    name.setText(values.get(valueCount));
                }
                if (keys.get(keyCount).equals("last_modify_time")) {
                    time.setText(values.get(valueCount));
                }

            }
        }

    }
}
