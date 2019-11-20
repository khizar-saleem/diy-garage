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
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.view.ActionRecyclerAdapter.ActionHolder;
import java.util.List;

/**
 * Subclass of {@link RecyclerView.Adapter} that displays {@link Action} values.
 */
public class ActionRecyclerAdapter extends RecyclerView.Adapter<ActionHolder> {

  private final Context context;
  private final List<Action> actions;
  private final OnClickListener listener;

  /**
   * Instantiates a new Action recycler adapter.
   *
   * @param context  the context
   * @param listener the listener
   * @param actions  the actions
   */
  public ActionRecyclerAdapter(Context context, View.OnClickListener listener, List<Action> actions) {
    this.context = context;
    this.actions = actions;
    this.listener = listener;
  }

  @NonNull
  @Override
  public ActionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.action_list_content, parent, false);
    return new ActionHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ActionHolder holder, int position) {
    holder.bind(actions.get(position));
  }


  @Override
  public int getItemCount() {
    return actions.size();
  }

  /**
   * The type Action holder.
   */
  class ActionHolder extends RecyclerView.ViewHolder {

    private final View view;
    private final TextView serviceType;
    private final TextView actionSummary;
    private final TextView actionDescription;

    private ActionHolder(View view) {
      super(view);
      this.view = view;
      view.setOnClickListener(listener);
      view.setTag(null);
      serviceType = view.findViewById(R.id.service_type);
      actionSummary = view.findViewById(R.id.action_summary);
      actionDescription = view.findViewById(R.id.action_description);
    }

    private void bind(Action action) {
      view.setTag(action);
      serviceType.setText(action.getServiceType().toString());
      actionSummary.setText(action.getSummary());
      actionDescription.setText(action.getDescription());
    }
  }

}
