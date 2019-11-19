package com.khizarms.diygarage.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.view.ActionRecyclerAdapter;
import com.khizarms.diygarage.viewmodel.ServiceDetailViewModel;

/**
 * The type Service detail fragment.
 */
public class ServiceDetailFragment extends Fragment implements View.OnClickListener {


  /**
   * The constant SERVICE_ID_KEY.
   */
  public static final String SERVICE_ID_KEY = "service_id";

  private ServiceDetailViewModel viewModel;
  private RecyclerView actionList;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.action_list, container, false);
    actionList = rootView.findViewById(R.id.action_list);
    return rootView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(ServiceDetailViewModel.class);
    viewModel.getService().observe(this, (service) -> {
      if (service != null) {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity
            .findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
          appBarLayout.setTitle(service.getDate().toString());
        }
        ActionRecyclerAdapter adapter = new ActionRecyclerAdapter(getContext(), this, service.getActions());
        actionList.setAdapter(adapter);
      }
    });
    if (getArguments().containsKey(SERVICE_ID_KEY)) {
      viewModel.setServiceId(getArguments().getLong(SERVICE_ID_KEY));
    }
  }

  @Override
  public void onClick(View view) {

  }
}
