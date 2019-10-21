package com.khizarms.diygarage.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.khizarms.diygarage.model.entity.Action;

@Dao
public interface ActionDao {

//  @Insert
//  String insert(Action... action);

  @Delete
  int delete(Action... actions);

//  @Insert
//  String[] insert(Action... actions);
//
//  @Query("")

}

