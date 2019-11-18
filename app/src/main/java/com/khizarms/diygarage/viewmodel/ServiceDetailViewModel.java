package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.model.pojo.ServiceWithActions;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

public class ServiceDetailViewModel extends AndroidViewModel {

  private DiyGarageDatabase database;
  private MutableLiveData<Long> serviceId = new MutableLiveData<>();
  private LiveData<ServiceWithActions> service;

  public ServiceDetailViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    service = Transformations.switchMap(serviceId, (id) -> database.getServiceDao().getActions(id));
  }

  public void setServiceId(Long serviceId) {
    this.serviceId.setValue(serviceId);
  }

  public LiveData<ServiceWithActions> getService() {
    return service;
  }
}
