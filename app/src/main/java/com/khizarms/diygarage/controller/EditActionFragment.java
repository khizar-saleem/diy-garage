package com.khizarms.diygarage.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.viewmodel.EditActionViewModel;

public class EditActionFragment extends DialogFragment {

  private Action action;
  private Spinner serviceTypeSpinner;
  private EditText actionSummary;
  private EditText actionDescription;
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
    return null;
  }
}
