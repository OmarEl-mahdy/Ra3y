// Generated by view binder compiler. Do not edit!
package com.example.ra3y.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ra3y.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySittersListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView rName;

  @NonNull
  public final TextView remail;

  @NonNull
  public final TextView rinfo;

  @NonNull
  public final TextView rphone;

  @NonNull
  public final TextView title;

  private ActivitySittersListBinding(@NonNull LinearLayout rootView, @NonNull TextView rName,
      @NonNull TextView remail, @NonNull TextView rinfo, @NonNull TextView rphone,
      @NonNull TextView title) {
    this.rootView = rootView;
    this.rName = rName;
    this.remail = remail;
    this.rinfo = rinfo;
    this.rphone = rphone;
    this.title = title;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySittersListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySittersListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sitters_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySittersListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rName;
      TextView rName = ViewBindings.findChildViewById(rootView, id);
      if (rName == null) {
        break missingId;
      }

      id = R.id.remail;
      TextView remail = ViewBindings.findChildViewById(rootView, id);
      if (remail == null) {
        break missingId;
      }

      id = R.id.rinfo;
      TextView rinfo = ViewBindings.findChildViewById(rootView, id);
      if (rinfo == null) {
        break missingId;
      }

      id = R.id.rphone;
      TextView rphone = ViewBindings.findChildViewById(rootView, id);
      if (rphone == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ActivitySittersListBinding((LinearLayout) rootView, rName, remail, rinfo, rphone,
          title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
