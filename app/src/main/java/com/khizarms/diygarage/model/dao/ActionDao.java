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

