package com.gzeic.smartcity01.x_yy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ApiUrl;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyPlActivity extends BaseActivity {

    private RatingBar pf;
    private EditText edit;
    private Button btn;
    private ImageView back;
    int id;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_pl);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        id = getIntent().getIntExtra("id", -1);
        initView();
    }

    private void initView() {
        pf = findViewById(R.id.pf);
        edit = findViewById(R.id.edit);

        btn = findViewById(R.id.btn);
        ApiUrl.setServerApiToken("Bearer "+getToken());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                if (s!=null){
                    float numStars = pf.getRating();
                    HashMap hashMap = new HashMap();
                    hashMap.put("content",s);
                    hashMap.put("movieId",id);
                    hashMap.put("score",numStars);
                    getTools().sendPostJsonToken("http://"+add+"/prod-api/api/movie/film/comment",hashMap).enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Toast.makeText(YyPlActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        @Override
                        public void onFailure(Call call, Throwable throwable) {
                        }
                    });
                }else{
                    Toast.makeText(YyPlActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}