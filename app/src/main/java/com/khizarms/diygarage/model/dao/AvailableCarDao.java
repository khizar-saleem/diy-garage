package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.AvailableCar;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.pojo.CarWithServices;
import java.util.Collection;
import java.util.List;

@Dao
public interface AvailableCarDao {

  @Insert
  long insert(AvailableCar car);

  @Insert
  List<Long> insert(Collection<AvailableCar> cars);

  @Query("SELECT * FROM AvailableCar ORDER BY make, model, year")
  LiveData<List<AvailableCar>> getAll();


  @Update
  int update(AvailableCar... cars);

  @Delete
  int delete(AvailableCar... cars);

}
