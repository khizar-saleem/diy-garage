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
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Action.ServiceType;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

/**
 * Supplier of {@link LiveData} intended to be consumed by an instance of {@link
 * com.khizarms.diygarage.controller.EditActionFragment}
 */
public class EditActionViewModel extends AndroidViewModel {

  private final DiyGarageDatabase database;
  private MutableLiveData<Long> serviceId;
  private MutableLiveData<ServiceType> serviceType;
  private LiveData<List<Action>> actions;


  /**
   * Instantiates a new Edit action view model.
   *
   * @param application the application
   */
  public EditActionViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    serviceId = new MutableLiveData<>();
    serviceType = new MutableLiveData<>();
    actions = Transformations.switchMap(serviceId, (id) -> database.getActionDao().getServiceActions(id));
  }


  /**
   * Gets service id.
   *
   * @return the service id
   */
  public MutableLiveData<Long> getServiceId() {
    return serviceId;
  }

  /**
   * Gets actions.
   *
   * @return the actions
   */
  public LiveData<List<Action>> getActions() {
    return actions;
  }

  /**
   * Gets service type.
   *
   * @return the service type
   */
  public LiveData<ServiceType> getServiceType() {
    return serviceType;
  }

  /**
   * Sets service type.
   *
   * @param serviceType the service type
   */
  public void setServiceType(ServiceType serviceType) {
    this.serviceType.setValue(serviceType);
  }

}
