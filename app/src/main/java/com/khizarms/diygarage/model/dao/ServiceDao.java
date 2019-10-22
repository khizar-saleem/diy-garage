package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.model.pojo.ServiceWithActions;

@Dao
public interface ServiceDao {

  @Insert
  long insert(Service service);


  @Query("SELECT * FROM Service WHERE service_id = :serviceId")
  LiveData<ServiceWithActions> getActions(long serviceId);

  @Update
  int update(Service... services);

  @Delete
  int delete(Service... services);

}
