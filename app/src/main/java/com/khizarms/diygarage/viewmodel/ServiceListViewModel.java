package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
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
}
