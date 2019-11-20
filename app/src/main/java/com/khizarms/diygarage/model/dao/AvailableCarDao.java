/*
Copyright 2019 Khizar Saleem

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
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

/**
 * The interface Available car dao.
 */
@Dao
public interface AvailableCarDao {

  /**
   * Insert long.
   *
   * @param car the car
   * @return the long
   */
  @Insert
  long insert(AvailableCar car);

  /**
   * Insert list.
   *
   * @param cars the cars
   * @return the list
   */
  @Insert
  List<Long> insert(Collection<AvailableCar> cars);

  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM AvailableCar ORDER BY make, model, year")
  LiveData<List<AvailableCar>> getAll();

  /**
   * Gets makes.
   *
   * @return the makes
   */
  @Query("SELECT DISTINCT make FROM AvailableCar ORDER BY make")
  LiveData<List<String>> getMakes();

  /**
   * Gets models.
   *
   * @return the models
   */
  @Query("SELECT DISTINCT model FROM AvailableCar ORDER BY model")
  LiveData<List<String>> getModels();

  /**
   * Gets makes.
   *
   * @param pattern the pattern
   * @return the makes
   */
  @Query("SELECT DISTINCT make FROM AvailableCar WHERE make LIKE :pattern ORDER BY make")
  LiveData<List<String>> getMakes(String pattern);

  /**
   * Gets models.
   *
   * @param make    the make
   * @param pattern the pattern
   * @return the models
   */
  @Query("SELECT DISTINCT model FROM AvailableCar WHERE model LIKE :pattern AND make = :make ORDER BY model")
  LiveData<List<String>> getModels(String make, String pattern);

  /**
   * Update int.
   *
   * @param cars the cars
   * @return the int
   */
  @Update
  int update(AvailableCar... cars);

  /**
   * Delete int.
   *
   * @param cars the cars
   * @return the int
   */
  @Delete
  int delete(AvailableCar... cars);

}
