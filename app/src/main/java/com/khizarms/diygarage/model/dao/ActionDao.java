package com.khizarms.diygarage.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.Action;
import java.util.Collection;
import java.util.List;

/**
 * The interface Action dao.
 */
@Dao
public interface ActionDao {

  /**
   * Insert long.
   *
   * @param action the action
   * @return the long
   */
  @Insert
  long insert(Action action);

  /**
   * Insert list.
   *
   * @param actions the actions
   * @return the list
   */
  @Insert
  List<Long> insert(Collection<Action> actions);

  /**
   * Gets service actions.
   *
   * @param serviceId the service id
   * @return the service actions
   */
  @Query("SELECT * FROM `Action` WHERE service_id = :serviceId ORDER BY action_id")
  LiveData<List<Action>> getServiceActions(long serviceId);


  /**
   * Update int.
   *
   * @param actions the actions
   * @return the int
   */
  @Update
  int update(Action... actions);

  /**
   * Delete int.
   *
   * @param actions the actions
   * @return the int
   */
  @Delete
  int delete(Action... actions);


}

