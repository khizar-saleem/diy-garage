package com.khizarms.diygarage.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

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


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getMake() {
    return make;
  }

  public void setMake(@NonNull String make) {
    this.make = make;
  }

  @NonNull
  public String getModel() {
    return model;
  }

  public void setModel(@NonNull String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return String.format("%1$s %2$s (%3$d)", make, model, year);
  }

}
