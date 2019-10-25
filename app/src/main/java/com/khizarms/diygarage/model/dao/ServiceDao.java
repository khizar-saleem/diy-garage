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

@Dao
public interface ServiceDao {

  @Insert
  long insert(Service service);

  @Transaction
  @Query("SELECT * FROM Service WHERE service_id = :serviceId")
  LiveData<ServiceWithActions> getActions(long serviceId);

  @Query("SELECT * FROM Service WHERE car_id = :carId")
  LiveData<List<Service>> getServicesByCar(Long carId);

  @Update
  int update(Service... services);

  @Delete
  int delete(Service... services);

}
