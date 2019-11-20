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
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import java.util.List;

/**
 * Supplier of {@link LiveData} intended to be consumed by an instance of {@link
 * com.khizarms.diygarage.controller.EditCarFragment}
 */
public class EditCarViewModel extends AndroidViewModel {

  private MutableLiveData<String> make = new MutableLiveData<>();
  private MutableLiveData<String> modelPattern = new MutableLiveData<>();
  private LiveData<List<String>> makes;
  private LiveData<List<String>> models;
  private DiyGarageDatabase database;
  private MakeModelLiveData makeModel = new MakeModelLiveData(make, modelPattern);


  /**
   * Instantiates a new Edit car view model.
   *
   * @param application the application
   */
  public EditCarViewModel(@NonNull Application application) {
    super(application);
    database = DiyGarageDatabase.getInstance();
    // Trigger refresh of models query whenever make or model pattern changes
    models = Transformations.switchMap(makeModel, (pair) -> {
      return database.getAvailableCarDao().getModels(pair.first, "%" + pair.second + "%");
    });
  }

  /**
   * Gets makes.
   *
   * @return the makes
   */
  public LiveData<List<String>> getMakes() {
    return database.getAvailableCarDao().getMakes();
  }

  /**
   * Sets make.
   *
   * @param make the make
   */
  public void setMake(String make) {
    this.make.setValue(make);
  }

  /**
   * Gets models.
   *
   * @return the models
   */
  public LiveData<List<String>> getModels() {
    return models;
  }

  /**
   * Sets model pattern.
   *
   * @param modelPattern the model pattern
   */
  public void setModelPattern(String modelPattern) {
    this.modelPattern.setValue(modelPattern);
  }

  // Define LiveData based on a pair of LiveData elements
  private static class MakeModelLiveData extends MediatorLiveData<Pair<String, String>> {

    /**
     * Instantiates a new Make model live data.
     *
     * @param make         the make
     * @param modelPattern the model pattern
     */
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

