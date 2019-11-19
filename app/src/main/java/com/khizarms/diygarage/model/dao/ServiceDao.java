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
