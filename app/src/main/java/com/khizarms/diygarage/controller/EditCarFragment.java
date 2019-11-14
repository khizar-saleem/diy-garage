package com.khizarms.diygarage.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.viewmodel.EditCarViewModel;

public class EditCarFragment extends DialogFragment {

  private Car car;
  private EditText carYear;
  private SearchView carMake;
  private SearchView carModel;
  private EditCarViewModel viewModel;
  private View dialogView;

  public static EditCarFragment newInstance(Car car) {
    EditCarFragment fragment = new EditCarFragment();
    Bundle args = new Bundle();
    if (car != null) {
      args.putSerializable("car", car);
    }
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return dialogView;
  }

  @NonNull
  @Override
  public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_newcar, null);
    carYear = dialogView.findViewById(R.id.car_year);
    carMake = dialogView.findViewById(R.id.make_search);
    carModel = dialogView.findViewById(R.id.model_search);
    carMake.setOnQueryTextListener(new OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        viewModel.setMakePattern(query);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        viewModel.setMakePattern(newText);
        return false;
      }
    });
    car = (Car) getArguments().getSerializable("car");
    if (car == null) {
      car = new Car();
    } else {
      carYear.setText(Integer.toString(car.getYear()));
      carMake.setQuery(car.getMake(), false);
      carModel.setQuery(car.getModel(), false);
    }
    return new Builder(getContext())
        .setTitle("New Car")
        .setView(dialogView)
        .setNegativeButton("Cancel", (dialog, button) -> {})
        .setPositiveButton("Save", (dialog, button) -> saveCar())
        .create();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(EditCarViewModel.class);
    viewModel.getMakes().observe(this, (makes) -> {
      Log.d(getClass().getSimpleName(), makes.toString());
    });
  }

  private void saveCar() {
//    availableCar.setYear(carYear.getInputType());
    int year = Integer.parseInt(carYear.getText().toString());
    String make = carMake.getQuery().toString();
    String model = carModel.getQuery().toString();
    car.setYear(year);
    car.setMake(make);
    car.setModel(model);
    ((CarSaver) getActivity()).save(car);
  }

  public interface CarSaver {

    void save(Car car);

  }

}
