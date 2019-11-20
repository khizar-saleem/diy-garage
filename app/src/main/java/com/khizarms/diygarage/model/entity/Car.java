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
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity class encapsulating the basic properties of a car.
 *
 * @author Khizar Saleem
 */
@Entity
public class Car implements Serializable {

  private static final long serialVersionUID = -5331911484426555529L;

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "car_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private String make;

  @ColumnInfo(index = true)
  @NonNull
  private String model;

  @ColumnInfo(index = true)
  private int year;

  @ColumnInfo(index = true)
  @NonNull
  private Date acquisition = new Date();

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
   * Gets make.
   *
   * @return the make
   */
  @NonNull
  public String getMake() {
    return make;
  }

  /**
   * Sets make.
   *
   * @param make the make
   */
  public void setMake(@NonNull String make) {
    this.make = make;
  }

  /**
   * Gets model.
   *
   * @return the model
   */
  @NonNull
  public String getModel() {
    return model;
  }

  /**
   * Sets model.
   *
   * @param model the model
   */
  public void setModel(@NonNull String model) {
    this.model = model;
  }

  /**
   * Gets year.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets year.
   *
   * @param year the year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Gets acquisition.
   *
   * @return the acquisition
   */
  @NonNull
  public Date getAcquisition() {
    return acquisition;
  }

  /**
   * Sets acquisition.
   *
   * @param acquisition the acquisition
   */
  public void setAcquisition(@NonNull Date acquisition) {
    this.acquisition = acquisition;
  }

  @Override
  public String toString() {
    return String.format("%1$s %2$s (%3$d)", make, model, year);
  }
}
