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
package com.khizarms.diygarage.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity class encapsulating the basic properties of a service.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Car.class,
            childColumns = "car_id",
            parentColumns = "car_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Service implements Serializable {

  private static final long serialVersionUID = 2883582909279887901L;

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "service_id")
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Date date = new Date();


  private int mileage;

  @ColumnInfo(name = "car_id", index = true)
  private long carId;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  @NonNull
  public Date getDate() {
    return date;
  }

  /**
   * Sets date.
   *
   * @param date the date
   */
  public void setDate(@NonNull Date date) {
    this.date = date;
  }

  /**
   * Gets mileage.
   *
   * @return the mileage
   */
  public int getMileage() {
    return mileage;
  }

  /**
   * Sets mileage.
   *
   * @param mileage the mileage
   */
  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  /**
   * Gets car id.
   *
   * @return the car id
   */
  public long getCarId() {
    return carId;
  }

  /**
   * Sets car id.
   *
   * @param carId the car id
   */
  public void setCarId(long carId) {
    this.carId = carId;
  }
}
