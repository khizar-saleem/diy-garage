package com.khizarms.diygarage.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.khizarms.diygarage.model.entity.Action;
import java.util.Collection;
import java.util.List;

@Dao
public interface ActionDao {

  @Insert
  long insert(Action action);

  @Insert
  List<Long> insert(Collection<Action> actions);

//  @Query("SELECT * FROM ")

  @Update
  int update(Action action);

  @Delete
  int delete(Action... actions);




}

