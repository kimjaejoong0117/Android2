package com.example.my1206server;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

private ArrayList<ChatVO> items = new ArrayList<ChatVO>();
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chatlist,parent,false);
        }

        ChatVO item = items.get(position);
        TextView tv_chat_nick=convertView.findViewById(R.id.tv_chat_nick);
        TextView tv_chat_content=convertView.findViewById(R.id.tv_chat_content);

        tv_chat_nick.setText(item.getNick());
                tv_chat_content.setText(item.getContent());

        return convertView;
    }

    public void addItem(String nick,String content,String day){
        ChatVO item = new ChatVO(nick,content,day);
        items.add(item);
    }
}
