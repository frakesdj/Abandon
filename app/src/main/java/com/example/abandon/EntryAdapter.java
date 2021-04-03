package com.example.abandon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {
    Context context;
    ArrayList<entry> entries = new ArrayList<entry>();
    RecyclerView rvProgram;
    View.OnClickListener onClickListener = new MyOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dashId;
        TextView dashMood;
        TextView dashActiv;
        TextView dashTime;
        TextView dashDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dashId = itemView.findViewById(R.id.dashId);
            dashMood = itemView.findViewById(R.id.dashMood);
            dashActiv = itemView.findViewById(R.id.dashActiv);
            dashTime = itemView.findViewById(R.id.dashTime);
            dashDate = itemView.findViewById(R.id.dashDate);
        }
    }

    public EntryAdapter(Context context, ArrayList<entry> entries, RecyclerView rvProgram)
    {
        this.context = context;
        this.entries = entries;
        this.rvProgram = rvProgram;

    }
    @NonNull
    @Override
    public EntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dash_list_adapater, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntryAdapter.ViewHolder holder, int position) {
        entry en = entries.get(position);
        holder.dashId.setText(en.getId());
        holder.dashTime.setText(en.getTime());
        holder.dashDate.setText(en.getDate());
        holder.dashActiv.setText(en.getActivty());
        holder.dashMood.setText(en.getMood());

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPos = rvProgram.getChildLayoutPosition(v);
            String item = entries.get(itemPos).getMood();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }
}
