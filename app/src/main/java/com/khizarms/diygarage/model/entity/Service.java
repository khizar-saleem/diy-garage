package com.khizarms.diygarage.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Date;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Date getDate() {
    return date;
  }

  public void setDate(@NonNull Date date) {
    this.date = date;
  }

  public int getMileage() {
    return mileage;
  }

  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  public long getCarId() {
    return carId;
  }

  public void setCarId(long carId) {
    this.carId = carId;
  }
}
