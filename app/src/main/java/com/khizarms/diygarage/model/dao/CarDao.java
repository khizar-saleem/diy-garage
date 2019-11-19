package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.pojo.CarWithServices;
import java.util.Collection;
import java.util.List;

/**
 * The interface Car dao.
 */
@Dao
public interface CarDao {

  /**
   * Insert long.
   *
   * @param car the car
   * @return the long
   */
  @Insert
  long insert(Car car);

  /**
   * Insert list.
   *
   * @param cars the cars
   * @return the list
   */
  @Insert
  List<Long> insert(Collection<Car> cars);

  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM Car ORDER BY make, model, year")
  LiveData<List<Car>> getAll();

  /**
   * Gets details.
   *
   * @param carId the car id
   * @return the details
   */
  @Transaction
  @Query("SELECT * FROM Car WHERE car_id = :carId")
  LiveData<CarWithServices> getDetails(long carId);

  /**
   * Update int.
   *
   * @param cars the cars
   * @return the int
   */
  @Update
  int update(Car... cars);

  /**
   * Delete int.
   *
   * @param cars the cars
   * @return the int
   */
  @Delete
  int delete(Car... cars);

}
