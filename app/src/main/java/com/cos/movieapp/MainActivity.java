package com.cos.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private YtsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화
        init();

        // 다운로드
        initDownload();

        listener();
    }

    private void init(){
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new YtsAdapter();
    }

    // 레트로핏이 알아서 스레드 관리
    private void initDownload(){
        YtsService ytsService = YtsService.retrofit.create(YtsService.class);
        Call<YtsData> call = ytsService.영화목록가져오기("rating", 10, 1); // 미리 널값 받음(퓨쳐)
        call.enqueue(new Callback<YtsData>() { // 다운로드 완료되면 담아 줌
            @Override
            public void onResponse(Call<YtsData> call, Response<YtsData> response) {
                if (response.isSuccessful() == true){
                    YtsData ytsData = response.body();
                    // 리사이클러뷰에 연결(리턴 못받기 때문에 바로 구현해야 타이밍 맞음)
                    adapter.addItems(ytsData.getData().getMovies());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<YtsData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "다운로드 실패", Toast.LENGTH_SHORT).show();
            }
        }); // 콜백 받아주는
    }

    private void listener() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(mContext, "안녕", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

}