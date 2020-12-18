package ru.sscc.ssd.hpccloud.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.sscc.ssd.hpccloud.JobsPageActivity;
import ru.sscc.ssd.hpccloud.R;

public class JobsAdapter  extends RecyclerView.Adapter<JobsAdapter.JobsHolder>{
    private static int itemsCount;
    private int itemsAmount;
    private static ArrayList<String> keys;
    private static ArrayList<String> values;

    private int valueCount;

    public JobsAdapter(int size){
        keys = JobsPageActivity.getKeys();
        values = JobsPageActivity.getValues();
        itemsAmount = size;
        itemsCount = 0;
        valueCount = 0;
    }
    @NonNull
    @Override
    public JobsAdapter.JobsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.jobs_item, parent, false);
        JobsAdapter.JobsHolder holder = new JobsAdapter.JobsHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobsHolder holder, int position) {
        holder.bind(position);

    }


    @Override
    public int getItemCount() {
        return itemsAmount;
    }
    class JobsHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;
        TextView state;

        public JobsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvJobsName);
            time = itemView.findViewById(R.id.tvJobsTime);
            state = itemView.findViewById(R.id.tvJobsState);
        }

        void bind(int i)
        {
            for(int keyCount = 0; keyCount < keys.size(); keyCount++, valueCount++) {
                if (keys.get(keyCount).equals("name")) {
                    name.setText(values.get(valueCount));
                }
                if (keys.get(keyCount).equals("last_modify_time")) {
                    time.setText(values.get(valueCount));
                }
                if (keys.get(keyCount).equals("state")) {
                    state.setText(values.get(valueCount));
                }

            }
        }

    }
}
