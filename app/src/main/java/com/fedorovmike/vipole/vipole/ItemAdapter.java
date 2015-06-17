package com.fedorovmike.vipole.vipole;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Mikhail on 17.06.2015.
 */
public class ItemAdapter extends ArrayAdapter<ItemInfo> {
    private static final String LOG_TAG = ItemAdapter.class.getSimpleName();
    private final Context mContext;

    public ItemAdapter(Context context, int resource, List<ItemInfo> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemInfo item = getItem(position);

        View v = convertView;
        ViewHolder holder = null;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, parent, false);
            // cache view fields into the holder
            holder = new ViewHolder();
            holder.tvItemID = (TextView) v.findViewById(R.id.tvItemID);
            holder.tvItemTitle = (TextView) v.findViewById(R.id.tvItemTitle);
            holder.tvItemType = (TextView) v.findViewById(R.id.tvItemType);
            holder.pbProcess = (ProgressBar) v.findViewById(R.id.pbProcess);
            holder.bnStart = (Button) v.findViewById(R.id.bnStart);
            if (((item.getId()/4)%4) == 0) {
               holder.color = mContext.getResources().getColor(R.color.item_type0_color);
            } else if (((item.getId()/4)%4) == 1) {
                holder.color = mContext.getResources().getColor(R.color.item_type1_color);
            } else if (((item.getId()/4)%4) == 2) {
                holder.color = mContext.getResources().getColor(R.color.item_type2_color);
            } else if (((item.getId()/4)%4) == 3) {
                holder.color = mContext.getResources().getColor(R.color.item_type3_color);
            } else {
                holder.color = mContext.getResources().getColor(R.color.item_color);
            }
            holder.info = item;

            v.setTag(holder);
        }
        else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
            holder.info.setProgressBar(null);
            holder.info = item;
            holder.info.setProgressBar(holder.pbProcess);

            if (((item.getId()/4)%4) == 0) {
                holder.color = mContext.getResources().getColor(R.color.item_type0_color);
            } else if (((item.getId()/4)%4) == 1) {
                holder.color = mContext.getResources().getColor(R.color.item_type1_color);
            } else if (((item.getId()/4)%4) == 2) {
                holder.color = mContext.getResources().getColor(R.color.item_type2_color);
            } else if (((item.getId()/4)%4) == 3) {
                holder.color = mContext.getResources().getColor(R.color.item_type3_color);
            } else {
                holder.color = mContext.getResources().getColor(R.color.item_color);
            }
        }
        holder.tvItemID.setText(Integer.toString(item.getId()));
        holder.tvItemTitle.setText(item.getTitle());
        holder.tvItemType.setText(item.getType());
        holder.pbProcess.setProgress(item.getProgress());
        holder.pbProcess.setMax(100);
        v.setBackgroundColor(holder.color);
        item.setProgressBar(holder.pbProcess);

        holder.bnStart.setEnabled((item.getProcessState() == ItemInfo.ProcessState.NOT_STARTED) ||
                (item.getProcessState() == ItemInfo.ProcessState.COMPLETE));
        final Button button = holder.bnStart;
        holder.bnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setProcessState(ItemInfo.ProcessState.QUEUED);
                button.setEnabled(false);
                button.invalidate();
                ProcessRunTask task = new ProcessRunTask(item);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        return v;
    }

    public static class ViewHolder {
        TextView tvItemID;
        TextView tvItemTitle;
        TextView tvItemType;
        ProgressBar pbProcess;
        Button bnStart;
        ItemInfo info;
        Integer color;
    }
}
