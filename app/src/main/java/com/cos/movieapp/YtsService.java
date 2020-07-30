package com.cos.movieapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

// 스프링의 서비스만큼 생성
public interface YtsService {

    // 스프링 RestController 갯수만큼 생성
    @GET("list_movies.json")
    Call<YtsData> 영화목록가져오기(
            @Query("sort_by") String sort_by,
            @Query("limit") int limit,
            @Query("page") int page
    );

    // 인터페이스지만 실행시 메모리에 바로 뜰 수 있도록 객체 생성
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://yts.mx/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
