package com.khizarms.diygarage.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.controller.EditCarFragment.CarSaver;
import com.khizarms.diygarage.controller.EditServiceFragment.ServiceSaver;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.service.GoogleSignInService;
import com.khizarms.diygarage.view.ServiceRecyclerAdapter;
import com.khizarms.diygarage.viewmodel.EditActionViewModel;
import com.khizarms.diygarage.viewmodel.ServiceListViewModel;

/**
 * An activity representing a list of Services. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link ServiceDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two vertical panes.
 */
public class ServiceListActivity extends AppCompatActivity implements CarSaver, ServiceSaver {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
   */
  private boolean twoPane;
  private ServiceListViewModel viewModel;
  private GoogleSignInService signInService = GoogleSignInService.getInstance();
  private MaterialButton addCarBtn;
  private MaterialButton deleteCarBtn;
  private FloatingActionButton addServiceBtn;
  private Car car;
  private Context context = this;
  private EditActionViewModel actionListViewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_service_list);

    Spinner spinner = findViewById(R.id.car_selector);
    addServiceBtn = findViewById(R.id.fab);
    addServiceBtn.setOnClickListener(view -> newService(null));
    addCarBtn = findViewById(R.id.add_car);
    addCarBtn.setOnClickListener(view -> newCar(null));
    viewModel = ViewModelProviders.of(this).get(ServiceListViewModel.class);
    viewModel.getCars().observe(this, (cars) -> {
      ArrayAdapter<Car> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cars);
      spinner.setAdapter(adapter);
    });
    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        long carId = ((Car) spinner.getItemAtPosition(position)).getId();
        viewModel.getCarId().setValue(carId);

      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

        viewModel.getCarId().setValue(null);
      }


    });


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//        .setAction("Action", null).show());

    if (findViewById(R.id.service_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      twoPane = true;
    }

    RecyclerView recyclerView = findViewById(R.id.service_list);
    setupRecyclerView(recyclerView);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }


  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    viewModel.getServices().observe(this, (services) -> {
      ServiceRecyclerAdapter adapter = new ServiceRecyclerAdapter(this, new OnClickListener() {
        @Override
        public void onClick(View v) {
          /* TODO Populate recycler view with actions */

        }
      },
          services);
      recyclerView.setAdapter(adapter);
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.action_settings:
        break;
      case R.id.sign_out:
        signOut();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }


  private void signOut() {
    signInService.signOut()
        .addOnCompleteListener((task) -> {
          Intent intent = new Intent(this, LoginActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }

  private void newCar(Car car) {
    EditCarFragment fragment = EditCarFragment.newInstance(car);
    fragment.show(getSupportFragmentManager(), fragment.getClass().getSimpleName());
  }

  private void newService(Service service) {
    EditServiceFragment fragment = EditServiceFragment.newInstance(service);
    fragment.show(getSupportFragmentManager(), fragment.getClass().getSimpleName());
  }

  @Override
  public void save(Car car) {
    viewModel.saveCar(car);
  }

  @Override
  public void delete(Car car) {
    viewModel.deleteCar(car);
  }

  @Override
  public void save(Service service) {
    viewModel.addService(service);
  }
}
