package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import android.provider.MediaStore.Audio.Media;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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
  private MakeModelLiveData makeModel = new MakeModelLiveData(make, modelPattern);




  public EditCarViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    // Trigger refresh of models query whenever make or model pattern changes
    models = Transformations.switchMap(makeModel, (pair) -> {
      return database.getAvailableCarDao().getModels(pair.first, "%" + pair.second + "%");
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

  // Define LiveData based on a pair of LiveData elements
  private static class MakeModelLiveData extends MediatorLiveData<Pair<String, String>> {

    public MakeModelLiveData(LiveData<String> make, LiveData<String> modelPattern) {
      addSource(make, new Observer<String>() {
        @Override
        public void onChanged(String s) {
          setValue(Pair.create(s, modelPattern.getValue()));
        }
      });
      addSource(modelPattern, new Observer<String>() {
        @Override
        public void onChanged(String s) {
          setValue(Pair.create(make.getValue(), s));
        }
      });
    }
  }
}

