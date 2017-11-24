package com.braincorp.orkut2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.listeners.OnItemClickListener;
import com.braincorp.orkut2.model.User;
import com.braincorp.orkut2.utils.DateUtils;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private List<User> data;
    private OnItemClickListener onItemClickListener;

    public UserAdapter(Context context, List<User> data, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean attachToRoot = false;
        View view = inflater.inflate(R.layout.card_user, parent, attachToRoot);
        return new UserHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = data.get(position);
        holder.textViewName.setText(user.getFullName());
        if (user.getDateOfBirth() != null) {
            String ageText = String.format(context.getString(R.string.age), DateUtils.getAge(user.getDateOfBirth()));
            holder.textViewAge.setText(ageText);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener onItemClickListener;

        TextView textViewName;
        TextView textViewAge;

        UserHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getLayoutPosition());
        }

    }

}
