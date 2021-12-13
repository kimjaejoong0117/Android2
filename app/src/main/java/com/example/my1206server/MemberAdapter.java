package com.example.my1206server;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter {
    //리스트뷰에 넣어줄 데이터를 보관하는 변수
    private ArrayList<AndMemberVO> items = new ArrayList<AndMemberVO>();

    //ArrayList에 데이터를 저장하는 메소드


    //현대 어댑터 안에 데이터 개수를 알아내는 메소드
    @Override
    public int getCount() {
        return items.size();
    }

    //요천한 위치의 데이터를 돌려주는 메소드
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    //해당 데이터의 아이디 값을 돌려주는 메소드(거의 사용안함)
    @Override
    public long getItemId(int position) {
        return position;//0
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.memberlayout, parent, false);
        }

        TextView tv_list_id = convertView.findViewById(R.id.tv_list_id);
        TextView tv_list_pw = convertView.findViewById(R.id.tv_list_pw);
        TextView tv_list_nick = convertView.findViewById(R.id.tv_list_nick);
        TextView tv_list_phone = convertView.findViewById(R.id.tv_list_phone);
        AndMemberVO item = items.get(position);
        tv_list_id.setText(item.getId());
        tv_list_pw.setText(item.getPw());
        tv_list_nick.setText(item.getNick());
        tv_list_phone.setText(item.getPhone());


        return convertView;
    }

    public void addItem(String id, String pw, String nick, String phone) {
        AndMemberVO item = new AndMemberVO(id, pw, nick, phone);
        items.add(item);
    }
}
