package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

public class ServiceListViewModel extends AndroidViewModel {

  private final DiyGarageDatabase database;


  public ServiceListViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
  }

  public LiveData<List<Car>> getCars() {
    return database.getCarDao().getAll();
  }

}
