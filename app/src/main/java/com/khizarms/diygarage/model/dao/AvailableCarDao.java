package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.AvailableCar;
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

  @Query("SELECT DISTINCT make FROM AvailableCar ORDER BY make")
  LiveData<List<String>> getMakes();

  @Query("SELECT DISTINCT model FROM AvailableCar ORDER BY model")
  LiveData<List<String>> getModels();

  @Query("SELECT DISTINCT make FROM AvailableCar WHERE make LIKE :pattern ORDER BY make")
  LiveData<List<String>> getMakes(String pattern);

  @Query("SELECT DISTINCT model FROM AvailableCar WHERE model LIKE :pattern AND make = :make ORDER BY model")
  LiveData<List<String>> getModels(String make, String pattern);

  @Update
  int update(AvailableCar... cars);

  @Delete
  int delete(AvailableCar... cars);

}
