package com.example.my1206server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccess extends AppCompatActivity {
    private TextView tv_Id, tv_Pw, tv_Nick, tv_Phone;
    private Button btn_chatMove;
    private AndMemberVO info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        tv_Id = findViewById(R.id.tv_Id);
        tv_Pw = findViewById(R.id.tv_Pw);
        tv_Nick = findViewById(R.id.tv_Nick);
        tv_Phone = findViewById(R.id.tv_Phone);

        Intent intent = getIntent();
        info = (AndMemberVO) intent.getSerializableExtra("info");
        tv_Id.setText(info.getId());
        tv_Pw.setText(info.getPw());
        tv_Nick.setText(info.getNick());
        tv_Phone.setText(info.getPhone());

        btn_chatMove = findViewById(R.id.btn_chatMove);
        btn_chatMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginSuccess.this, Chat.class);

                intent1.putExtra("info",info);
                startActivity(intent1);
            }
        });

    }
}