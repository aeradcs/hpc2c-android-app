package ru.sscc.ssd.hpccloud.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import ru.sscc.ssd.hpccloud.R;

public class DirectoryAdapter extends BaseExpandableListAdapter {
    Context context;
    public DirectoryAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return 6;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View groupView, ViewGroup parent) {
        if(groupView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = inflater.inflate(R.layout.directory_item, null);
        }
        switch (groupPosition){
            case 0:{
                ((TextView)groupView.findViewById(R.id.dirItem)).setText("appStorage");
            } break;
            case 1:{
                ((TextView)groupView.findViewById(R.id.dirItem)).setText("apps");


            } break;
            case 2:{
                ((TextView)groupView.findViewById(R.id.dirItem)).setText("docs");

            } break;
            case 3:{
                ((TextView)groupView.findViewById(R.id.dirItem)).setText("frameworks");

            } break;
            case 4:{
                ((TextView)groupView.findViewById(R.id.dirItem)).setText("jobs");

            } break;
            case 5:{
                ((TextView)groupView.findViewById(R.id.dirItem)).setText("models");

            } break;

        }
        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View childView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childView = inflater.inflate(R.layout.sub_dir_item, null);

        switch (groupPosition){
            case 0:{
                switch (childPosition){
                    case 0:{
                        ((TextView)childView.findViewById(R.id.subDirItem)).setText("sub 0");
                    } break;
                }

            } break;
            case 1:{
                switch (childPosition){
                    case 0:{
                        LayoutInflater inflater1 = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        childView = inflater1.inflate(R.layout.directory_item, null);
                        ((TextView)childView.findViewById(R.id.subDirItem)).setText("sub 1");
                    } break;
                }
            } break;
            case 2:{
                switch (childPosition){
                    case 0:{
                        ((TextView)childView.findViewById(R.id.subDirItem)).setText("sub 2");
                    } break;
                }
            } break;
            case 3:{
                switch (childPosition){
                    case 0:{
                        ((TextView)childView.findViewById(R.id.subDirItem)).setText("sub 3");
                    } break;
                }
            } break;
            case 4:{
                switch (childPosition){
                    case 0:{
                        ((TextView)childView.findViewById(R.id.subDirItem)).setText("sub 4");
                    } break;
                }
            } break;
            case 5:{
                switch (childPosition){
                    case 0:{
                        ((TextView)childView.findViewById(R.id.subDirItem)).setText("sub 5");
                    } break;
                }
            } break;

        }
        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
