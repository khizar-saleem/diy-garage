package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.pojo.CarWithServices;

@Dao
public interface CarDao {

  @Insert
  long insert(Car car);

  @Query("SELECT * FROM Car WHERE car_id = :carId")
  LiveData<CarWithServices> getDetails(long carId);

  @Update
  int update(Car... cars);

  @Delete
  int delete(Car... cars);

}
