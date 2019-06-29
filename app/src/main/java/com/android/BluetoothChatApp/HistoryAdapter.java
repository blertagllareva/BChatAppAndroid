package com.android.BluetoothChatApp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class HistoryAdapter extends Adapter<HistoryAdapter.HistoryAdapterViewHolder> {

    private List<HistoryData> historyData;
    final private HistoryAdapterOnClickHandler mClickHandler;
    private Context mContext;

    public HistoryAdapter(Context context, HistoryAdapterOnClickHandler clickHandler, List<HistoryData> list) {
        mClickHandler = clickHandler;
        historyData = list;
        mContext = context;
    }

    @Override
    public HistoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId;
        layoutId = R.layout.history;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately);
        return new HistoryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapterViewHolder holder, int position) {
        HistoryData currentHistoryData = historyData.get(position);
        holder.mMessageTv.setText(currentHistoryData.getMessage());
        holder.mDeviceNameTv.setText( currentHistoryData.getDevice());
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(currentHistoryData.getDate() * 1000L);
        String date = DateFormat.format("dd.MM.yyyy hh:mm", cal).toString();
        holder.mDateTv.setText("Data: " + date);
    }

    @Override
    public int getItemCount() {
        if (null == historyData) return 0;
        return historyData.size();
    }

    public HistoryData getMessageAt(int position)
    {
        return historyData.get(position);
    }


    public class HistoryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mMessageTv;
        private final TextView mDeviceNameTv;
        private final TextView mDateTv;

        public HistoryAdapterViewHolder(View view) {
            super(view);

            mMessageTv = (TextView) view.findViewById(R.id.message_tv);
            mDeviceNameTv = (TextView) view.findViewById(R.id.device_name_tv);
            mDateTv = (TextView) view.findViewById(R.id.date_tv);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            HistoryData data = historyData.get(getAdapterPosition());
            mClickHandler.onClick(data);
        }
    }
  /*  public void setHistoryData(List<HistoryData> _historyData) {
        historyData = _historyData;

        notifyDataSetChanged();
    }*/
}
