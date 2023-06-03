package edu.itstep.a08recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private Context context;
    private int resource;
    private List<Contact> listData;
    private LayoutInflater inflater;
    private int count = 0;
    OnClickListener onClickListener;

    public NumberAdapter(Context context, int resource, List<Contact> listData, OnClickListener onClickListener) {
        this.context = context;
        this.resource = resource;
        this.listData = listData;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View container = inflater.inflate(resource, parent, false);
        NumberViewHolder numberViewHolder = new NumberViewHolder(container);
        return numberViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        Contact data = listData.get(position);
        holder.tvFirstName.setText(data.getFirstName());
        holder.tvLastName.setText(data.getLastName());
        holder.tvPhone.setText(data.getPhone());

        holder.itemView.setOnClickListener(view -> {
            onClickListener.onClick(position, data);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public interface OnClickListener {
        void onClick(int position, Contact model);
    }
    class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvPhone;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.etLastName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
