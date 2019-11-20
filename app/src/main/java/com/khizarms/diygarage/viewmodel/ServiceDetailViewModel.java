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
package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.model.dao.ActionDao;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.pojo.ServiceWithActions;
import com.khizarms.diygarage.service.DiyGarageDatabase;

/**
 * Supplier of {@link LiveData} intended to be consumed by an instance of {@link
 * com.khizarms.diygarage.controller.ServiceDetailActivity} (and any hosted fragments within).
 */
public class ServiceDetailViewModel extends AndroidViewModel {

  private DiyGarageDatabase database;
  private MutableLiveData<Long> serviceId = new MutableLiveData<>();
  private LiveData<ServiceWithActions> service;

  /**
   * Instantiates a new Service detail view model.
   *
   * @param application the application
   */
  public ServiceDetailViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    service = Transformations.switchMap(serviceId, (id) -> database.getServiceDao().getActions(id));
  }

  /**
   * Sets service id.
   *
   * @param serviceId the service id
   */
  public void setServiceId(Long serviceId) {
    this.serviceId.setValue(serviceId);
  }

  /**
   * Gets service.
   *
   * @return the service
   */
  public LiveData<ServiceWithActions> getService() {
    return service;
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
}
