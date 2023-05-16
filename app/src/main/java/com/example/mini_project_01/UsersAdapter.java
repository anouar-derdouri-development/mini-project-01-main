package com.example.mini_project_01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {
    final int DOUBLE_CLICK_TIMEOUT = 250;

    Context context;
    LayoutInflater inflater;
    ArrayList<User> users;

    public UsersAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = users.get(position);
        convertView = inflater.inflate(R.layout.item_user, null);

        TextView tvUsersItmFullName = convertView.findViewById(R.id.tvUsersItmFullName);
        TextView tvUsersItmCity = convertView.findViewById(R.id.tvUsersItmCity);
        ImageView ivUserItmChecked = convertView.findViewById(R.id.ivUserItmChecked);

        tvUsersItmFullName.setText(user.fullName());
        tvUsersItmCity.setText(user.getCity());

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle(String.format("Details of user %d", position + 1))
                        .setMessage(user.toString())
                        .show();

                return false;
            }
        });

        convertView.setOnTouchListener(new View.OnTouchListener() {
            long lastClickTime = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Toast.makeText(context, "down", Toast.LENGTH_SHORT).show();
//                        break;
                    case MotionEvent.ACTION_UP:
                        long clickTime = System.currentTimeMillis();

                        if ((clickTime - lastClickTime) <= DOUBLE_CLICK_TIMEOUT)
                            ivUserItmChecked.setVisibility(ivUserItmChecked.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);
                        else
                            lastClickTime = clickTime;

                        break;
                }

                return true;
            }
        });

        return convertView;
    }
}
