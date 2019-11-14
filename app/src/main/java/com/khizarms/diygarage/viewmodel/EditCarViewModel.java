package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EditCarViewModel extends AndroidViewModel {

  private MutableLiveData<String> make = new MutableLiveData<>();
  private MutableLiveData<String> modelPattern = new MutableLiveData<>();
  private LiveData<List<String>> makes;
  private LiveData<List<String>> models;
  private DiyGarageDatabase database;



  public EditCarViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    models = Transformations.switchMap(modelPattern, (pattern) -> {
      if (pattern.length() < 2) {
        return new MutableLiveData<>(Collections.EMPTY_LIST);
      } else {
        return database.getAvailableCarDao().getModels();
      }
    });
  }

  public LiveData<List<String>> getMakes() {
    return database.getAvailableCarDao().getMakes();
  }

  public void setMake(String make) {
    this.make.setValue(make);
  }

  public LiveData<List<String>> getModels() {
    return models;
  }

  public void setModelPattern(String modelPattern) {
    this.modelPattern.setValue(modelPattern);
  }

  // TODO Define a custom mediator live data
}
