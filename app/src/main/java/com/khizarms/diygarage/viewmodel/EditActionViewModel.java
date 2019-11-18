package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.model.dao.ActionDao;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

public class EditActionViewModel extends AndroidViewModel {

  private final DiyGarageDatabase database;
  private MutableLiveData<Long> serviceId;
  private LiveData<List<Action>> actions;


  public EditActionViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    serviceId = new MutableLiveData<>();
    actions = Transformations.switchMap(serviceId, (id) -> database.getActionDao().getServiceActions(id));
  }


  public MutableLiveData<Long> getServiceId() {
    return serviceId;
  }

  public LiveData<List<Action>> getActions() {
    return actions;
  }

  public void addAction(Action action) {
    ActionDao dao = database.getActionDao();
    new Thread(() -> {
      if (action.getServiceId() == 0 && action.getId() == 0) {
        action.setServiceId(getServiceId().getValue());
        dao.insert(action);
      } else {
        dao.update(action);
      }
    }).start();
  }
}
