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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

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
        String ageText = String.format(context.getString(R.string.age),
                calculateAge(user.getDateOfBirth()));
        holder.textViewAge.setText(ageText);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private int calculateAge(Date dateOfBirth) {
        Calendar now = Calendar.getInstance();
        Calendar target = getCalendar(dateOfBirth);
        int age = now.get(YEAR) - target.get(YEAR);
        if (target.get(MONTH) > now.get(MONTH) ||
                (target.get(MONTH) == now.get(MONTH) && target.get(DATE) > now.get(DATE))) {
            age--;
        }
        return age;
    }

    private Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
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
