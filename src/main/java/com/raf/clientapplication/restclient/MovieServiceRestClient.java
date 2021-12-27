package com.raf.clientapplication.restclient;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.dto.MovieListDto;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieServiceRestClient {

	public static final String URL = "http://localhost:8081/api";

	public static final MediaType JSON
		= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public MovieListDto getMovies() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
			.url(URL + "/movie")
			.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
			.get()
			.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, MovieListDto.class);
		}

		throw new RuntimeException();
	}
}
