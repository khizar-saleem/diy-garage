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
import androidx.room.Transaction;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.model.pojo.ServiceWithActions;
import java.util.List;

/**
 * The interface Service dao.
 */
@Dao
public interface ServiceDao {

  /**
   * Insert long.
   *
   * @param service the service
   * @return the long
   */
  @Insert
  long insert(Service service);

  /**
   * Gets actions.
   *
   * @param serviceId the service id
   * @return the actions
   */
  @Transaction
  @Query("SELECT * FROM Service WHERE service_id = :serviceId")
  LiveData<ServiceWithActions> getActions(long serviceId);

  /**
   * Gets services by car.
   *
   * @param carId the car id
   * @return the services by car
   */
  @Query("SELECT * FROM Service WHERE car_id = :carId")
  LiveData<List<Service>> getServicesByCar(Long carId);

  /**
   * Update int.
   *
   * @param services the services
   * @return the int
   */
  @Update
  int update(Service... services);

  /**
   * Delete int.
   *
   * @param services the services
   * @return the int
   */
  @Delete
  int delete(Service... services);

}
