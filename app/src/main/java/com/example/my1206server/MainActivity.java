package com.example.my1206server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private Button btn_joinMove,btn_Login,btn_Cancel,btn_listMove;
EditText text_Id,text_Pw;

    //Server로 요청하는 객체
    private RequestQueue queue;
    //server로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //client를 판별하는 값
    private String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_joinMove=findViewById(R.id.btn_joinMove);
        btn_Login=findViewById(R.id.btn_Login);
        btn_Cancel=findViewById(R.id.btn_Cancel);
        text_Id=findViewById(R.id.text_Id);
        text_Pw=findViewById(R.id.text_Pw);
        btn_listMove=findViewById(R.id.btn_listMove);

        btn_listMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemberList.class);
                startActivity(intent);

            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        btn_Login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sendRequest();
            }
        }));
        btn_joinMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Join.class);
                startActivity(intent);
            }
        });
    }


    public void sendRequest() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        // 서버에 요청할 주소
        String url = "http://220.80.203.45:8091/AndroidServer/LoginService";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                if(response.equals("fail")){
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                }else{
                    //json 타입 문자열을 json 객체로 변환해주는 코드
                   try {
                       JSONObject jsonObject= new JSONObject(response);
                       String id = jsonObject.getString("id");
                       String pw = jsonObject.getString("pw");
                       String nick = jsonObject.getString("nick");
                       String phone = jsonObject.getString("phone");
                       Log.v("resultValue",id+"/"+pw+""+nick+"/"+phone);
                       AndMemberVO info= new AndMemberVO(id,pw,nick,phone);
                       Intent intent= new Intent(MainActivity.this,LoginSuccess.class);
                       intent.putExtra("info",info);
                       startActivity(intent);

                   }catch(JSONException e){
                       e.printStackTrace();
                   }

                }

            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",text_Id.getText().toString());
                params.put("pw",text_Pw.getText().toString());

                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
}