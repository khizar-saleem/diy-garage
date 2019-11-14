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

  private MutableLiveData<String> makePattern = new MutableLiveData<>();
  private MutableLiveData<String> modelPattern = new MutableLiveData<>();
  private LiveData<List<String>> makes;
  private LiveData<List<String>> models;
  private DiyGarageDatabase database;



  public EditCarViewModel(@NonNull Application application) {

    super(application);
    database = DiyGarageDatabase.getInstance();
    makes = Transformations.switchMap(makePattern, (pattern) -> {
      if (pattern.length() < 2) {
        return new MutableLiveData<>(Collections.EMPTY_LIST);
      } else {
        return database.getAvailableCarDao().getMakes("%" + pattern + "%");
      }
    });
  }

  public LiveData<List<String>> getMakes() {
    return makes;
  }

  public void setMakePattern(String makePattern) {
    this.makePattern.setValue(makePattern);
  }
}
