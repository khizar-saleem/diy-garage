package com.khizarms.diygarage.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
  private Spinner carMake;
  private SearchView carModel;
  private EditCarViewModel viewModel;
  private View dialogView;
  private TextView make;
  private ListView modelList;

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
    make = dialogView.findViewById(R.id.make);
    carMake = dialogView.findViewById(R.id.make_search);
    carMake.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selection = (String) adapterView.getItemAtPosition(i);
        make.setText(selection);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    carModel = dialogView.findViewById(R.id.model_search);
    carModel.setOnQueryTextListener(new OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        viewModel.setModelPattern(query);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        viewModel.setModelPattern(newText);
        return false;
      }
    });
    car = (Car) getArguments().getSerializable("car");
    if (car == null) {
      car = new Car();
    } else {
      carYear.setText(Integer.toString(car.getYear()));
      make.setText(car.getMake());
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
      ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, makes);
      carMake.setAdapter(adapter);
      int position = makes.indexOf(make.getText().toString());
      carMake.setSelection(position);
    });
    viewModel.getModels().observe(this, (models) -> {
      Log.d(getClass().getSimpleName(), models.toString());
    });

  }

  private void saveCar() {
    int year = Integer.parseInt(carYear.getText().toString());
    String model = carModel.getQuery().toString();
    car.setYear(year);
    car.setMake(make.getText().toString());
    car.setModel(model);
    ((CarSaver) getActivity()).save(car);
  }

  public interface CarSaver {

    void save(Car car);

  }

}