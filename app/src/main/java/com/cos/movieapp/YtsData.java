package com.cos.movieapp;

import java.util.List;

import lombok.Data;

@Data
public class YtsData {
    private String status;
    private String status_message;
    private MyData data;

    @Data
    public class MyData {
        private int movie_count;
        private int limit;
        private int page_number;
        private List<Movie> movies;

        @Data  // lombok 추가
        public class Movie{
            private String title;
            private float rating;  // 타입 수정
            private String medium_cover_image;
        }

        @Override
        public String toString() {
            return "MyData{" +
                    "movie_count=" + movie_count +
                    ", limit=" + limit +
                    ", page_number=" + page_number +
                    ", movies=" + movies +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "YtsData{" +
                "status='" + status + '\'' +
                ", status_message='" + status_message + '\'' +
                ", data=" + data +
                '}';
    }
}