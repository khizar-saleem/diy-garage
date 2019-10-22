package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.khizarms.diygarage.model.pojo.CarWithServices;

@Dao
public interface CarDao {


  @Query("SELECT * FROM Car WHERE car_id = :carId")
  LiveData<CarWithServices> getDetails(long carId);


}
