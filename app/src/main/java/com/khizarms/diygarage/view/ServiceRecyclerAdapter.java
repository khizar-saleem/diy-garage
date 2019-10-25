package com.khizarms.diygarage.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.model.entity.Service;
import com.khizarms.diygarage.view.ServiceRecyclerAdapter.ServiceHolder;
import java.util.List;

public class ServiceRecyclerAdapter extends RecyclerView.Adapter<ServiceHolder>{

  private final Context context;
  private final List<Service> services;
  private final OnClickListener listener;

  public ServiceRecyclerAdapter(Context context, View.OnClickListener listener, List<Service> services) {
    this.context = context;
    this.services = services;
    this.listener = listener;
  }

  @NonNull
  @Override
  public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.service_list_content, parent, false);
    return new ServiceHolder(view);
  }

  @Override
  public void onBindViewHolder(final ServiceHolder holder, int position) {
    holder.bind(services.get(position));
  }

  @Override
  public int getItemCount() {
    return services.size();
  }

  class ServiceHolder extends RecyclerView.ViewHolder {

    private final View view;
    private final TextView serviceDate;
    private final TextView serviceMileage;

    private ServiceHolder(View view) {
      super(view);
      this.view = view;
      view.setOnClickListener(listener);
      view.setTag(null);
      serviceDate = view.findViewById(R.id.service_date);
      serviceMileage = view.findViewById(R.id.service_mileage);
    }

    private void bind(Service service) {
      view.setTag(service);
      serviceDate.setText(service.getDate().toString());
      serviceMileage.setText(context.getString(R.string.mileage_format, service.getMileage()));
    }

  }


}
