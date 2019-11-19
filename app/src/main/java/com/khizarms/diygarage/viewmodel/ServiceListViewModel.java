package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.model.dao.ActionDao;
import com.khizarms.diygarage.model.dao.CarDao;
import com.khizarms.diygarage.model.dao.ServiceDao;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

/**
 * Supplier of {@link LiveData} intended to be consumed by an instance of {@link
 * com.khizarms.diygarage.controller.ServiceListActivity} (and any hosted fragments within).
 */
public class ServiceListViewModel extends AndroidViewModel {

  private final DiyGarageDatabase database;
  private MutableLiveData<Long> carId;
  private LiveData<List<Service>> services;
  private MutableLiveData<Long> serviceId = new MutableLiveData<>();


  /**
   * Instantiates a new Service list view model.
   *
   * @param application the application
   */
  public ServiceListViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    carId = new MutableLiveData<>();
    services = Transformations.switchMap(carId, (id) -> database.getServiceDao().getServicesByCar(id));
  }

  /**
   * Gets cars.
   *
   * @return the cars
   */
  public LiveData<List<Car>> getCars() {
    return database.getCarDao().getAll();
  }

  /**
   * Gets car id.
   *
   * @return the car id
   */
  public MutableLiveData<Long> getCarId() {
    return carId;
  }

  /**
   * Gets services.
   *
   * @return the services
   */
  public LiveData<List<Service>> getServices() {
    return services;
  }

  /**
   * Add service.
   *
   * @param service the service
   */
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

  /**
   * Save car.
   *
   * @param car the car
   */
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

  /**
   * Delete car.
   *
   * @param car the car
   */
  public void deleteCar(Car car) {
    CarDao dao = database.getCarDao();
    new Thread(() -> {
      dao.delete(car);
    }).start();
  }

  /**
   * Save action.
   *
   * @param action the action
   */
  public void saveAction(Action action) {
    ActionDao dao = database.getActionDao();
    new Thread(() -> {
      if (action.getServiceId() == 0 && action.getId() == 0) {
        action.setServiceId(serviceId.getValue());
        dao.insert(action);
      } else {
        dao.update(action);
      }
    }).start();
  }

  /**
   * Sets service id.
   *
   * @param serviceId the service id
   */
  public void setServiceId(long serviceId) {
    this.serviceId.setValue(serviceId);
  }
}
