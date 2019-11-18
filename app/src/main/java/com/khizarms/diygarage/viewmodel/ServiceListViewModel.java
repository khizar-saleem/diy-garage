package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.model.dao.CarDao;
import com.khizarms.diygarage.model.dao.ServiceDao;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

public class ServiceListViewModel extends AndroidViewModel {

  private final DiyGarageDatabase database;
  private MutableLiveData<Long> carId;
  private LiveData<List<Service>> services;


  public ServiceListViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    carId = new MutableLiveData<>();
    services = Transformations.switchMap(carId, (id) -> database.getServiceDao().getServicesByCar(id));
  }

  public LiveData<List<Car>> getCars() {
    return database.getCarDao().getAll();
  }

  public MutableLiveData<Long> getCarId() {
    return carId;
  }

  public LiveData<List<Service>> getServices() {
    return services;
  }

  public void addService(Service service) {
    ServiceDao dao = database.getServiceDao();
    new Thread(() -> {
      if (service.getCarId() == 0 && service.getId() == 0) {
        service.setCarId(getCarId().getValue());
        dao.insert(service);
      } else {
        dao.update(service);
      }
    }).start();
  }

  public void saveCar(Car car) {
    CarDao dao = database.getCarDao();
    new Thread(() -> {
      if (car.getId() == 0) {
        dao.insert(car);
      } else {
        dao.update(car);
      }
    }).start();

  }

  public void deleteCar(Car car) {
    CarDao dao = database.getCarDao();
    new Thread(() -> {
      dao.delete(car);
    }).start();
  }
}
