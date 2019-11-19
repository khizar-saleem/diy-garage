package com.khizarms.diygarage.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Action.ServiceType;
import com.khizarms.diygarage.viewmodel.EditActionViewModel;

public class EditActionFragment extends DialogFragment {

  private Action action;
  private Spinner serviceTypeSpinner;
  private EditText actionSummary;
  private EditText actionDescription;
  private TextView serviceType;
  private EditActionViewModel viewModel;
  private View dialogView;

  public static EditActionFragment newInstance(Action action) {
    EditActionFragment fragment = new EditActionFragment();
    Bundle args = new Bundle();
    if (action != null) {
      args.putSerializable("action", action);
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
    dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_editaction, null);
    serviceTypeSpinner = dialogView.findViewById(R.id.service_type_spinner);
    actionSummary = dialogView.findViewById(R.id.action_edit_summary);
    actionDescription = dialogView.findViewById(R.id.action_edit_description);
    ArrayAdapter<ServiceType> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ServiceType.values());
    serviceTypeSpinner.setAdapter(adapter);
    serviceTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         ServiceType selection = (ServiceType) adapterView.getItemAtPosition(i);
         viewModel.setServiceType(selection);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    action = (Action) getArguments().getSerializable("action");
    if (action == null) {
      action = new Action();
    } else {
      actionSummary.setText(action.getSummary());
      actionDescription.setText(action.getDescription());
    }
    return new Builder(getContext())
        .setTitle("Add Action")
        .setView(dialogView)
        .setNegativeButton("Cancel", (dialog, button) -> {})
        .setPositiveButton("Save", (dialog, button) -> saveAction())
        .create();
  }

  private void saveAction() {
    String summary = actionSummary.toString();
    String description = actionDescription.toString();
    action.setSummary(summary);
    action.setDescription(description);
    action.setServiceType((ServiceType) viewModel.getServiceType().getValue());
    ((ActionSaver) getActivity()).save(action);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(EditActionViewModel.class);
    viewModel.getServiceType().observe(this, (type) -> serviceTypeSpinner.setSelection(type.ordinal()));
  }

  public interface ActionSaver {
    void save(Action action);
  }
}
