package com.khizarms.diygarage.service;

import com.khizarms.diygarage.model.pojo.MakesResponse;
import com.khizarms.diygarage.model.pojo.ModelsRespnose;
import com.khizarms.diygarage.model.pojo.YearsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuelEconomyService {

  @GET("year")
  Single<YearsResponse> getAllYears();

  @GET("make")
  Single<MakesResponse> getAllMakesForYear(@Query("year") int year);

  @GET("model")
  Single<ModelsRespnose> getAllModelsForYearAndMake(@Query("year") int year, @Query("make") String make);

}
