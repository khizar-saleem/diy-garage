package com.khizarms.diygarage.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class encapsulating the basic properties of AvailableCar.
 */
@Entity
public class AvailableCar {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "available_car_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private String make;

  @ColumnInfo(index = true)
  @NonNull
  private String model;

  @ColumnInfo(index = true)
  private int year;


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

  @Override
  public String toString() {
    return String.format("%1$s %2$s (%3$d)", make, model, year);
  }

}
