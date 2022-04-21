package com.example.getapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult=findViewById(R.id.text_view_result);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();
        JasonPlaceHolderApi jasonPlaceHolderApi=retrofit.create(JasonPlaceHolderApi.class);
        Call<List<Post>>call=jasonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
List<Post> list=response.body();
                for (Post po: list){
                    String content ="";
                    content+="Id:"+po.getId()+"\n";
                    content+="Userid:"+po.getUserId()+"\n";
                    content+="Title:"+po.getTitle()+"\n";
                    content+="Text:"+po.getText()+"\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
textViewResult.setText(t.getMessage());
            }
        });
    }
}