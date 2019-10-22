package com.khizarms.diygarage.model.pojo;

import androidx.room.Relation;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import java.util.List;

public class CarWithServices extends Car {

  @Relation(entity = Service.class, entityColumn = "car_id", parentColumn = "car_id")
  private List<ServiceWithActions> services;

  public List<ServiceWithActions> getServices() {
    return services;
  }

  public void setServices(List<ServiceWithActions> services) {
    this.services = services;
  }
}
