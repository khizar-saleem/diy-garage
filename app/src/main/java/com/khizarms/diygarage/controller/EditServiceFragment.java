package com.khizarms.diygarage.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import java.util.Date;

/**
 * Alert dialog user interface component presenting the mileage property of a service for editing.
 *
 * @author Khizar Saleem
 */
public class EditServiceFragment extends DialogFragment {

  private Car car;
  private Service service;
  private EditText editMileage;
  private View dialogView;


  /**
   * Creates and returns an instance of {@link EditServiceFragment} for editing a new passphrase.
   *
   * @param service the service
   * @return {@link EditServiceFragment} for display.
   */
  public static EditServiceFragment newInstance(Service service) {
    EditServiceFragment fragment = new EditServiceFragment();
    Bundle args = new Bundle();
    if (service != null) {
      args.putSerializable("service", service);
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
    dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_editservice, null);
    editMileage = dialogView.findViewById(R.id.edit_mileage);
    service = (Service) getArguments().getSerializable("service");
    if (service == null) {
      service = new Service();
    } else {
      editMileage.setText(Integer.toString(service.getMileage()));

    }
    return new Builder(getContext())
        .setTitle("Add Service")
        .setView(dialogView)
        .setNegativeButton("Cancel", (dialog, button) -> {})
        .setPositiveButton("Save", (dialog, button) -> saveService())
        .create();
  }

  private void saveService() {
    Date date = new Date();
    int mileage = Integer.parseInt(editMileage.getText().toString());
    service.setMileage(mileage);
    service.setDate(date);
    ((ServiceSaver) getActivity()).save(service);
  }

  /**
   * The interface Service saver.
   */
  public interface ServiceSaver {

    /**
     * Save.
     *
     * @param service the service
     */
    void save(Service service);
  }
}
