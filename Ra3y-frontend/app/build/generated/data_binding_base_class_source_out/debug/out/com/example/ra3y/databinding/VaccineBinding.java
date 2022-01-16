// Generated by view binder compiler. Do not edit!
package com.example.ra3y.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

public final class VaccineBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CheckBox cbVaccine1;

  @NonNull
  public final CheckBox cbVaccine2;

  @NonNull
  public final CheckBox cbVaccine3;

  @NonNull
  public final CheckBox cbVaccine4;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView3;

  private VaccineBinding(@NonNull LinearLayout rootView, @NonNull CheckBox cbVaccine1,
      @NonNull CheckBox cbVaccine2, @NonNull CheckBox cbVaccine3, @NonNull CheckBox cbVaccine4,
      @NonNull LinearLayout linearLayout2, @NonNull LinearLayout linearLayout4,
      @NonNull TextView textView, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.cbVaccine1 = cbVaccine1;
    this.cbVaccine2 = cbVaccine2;
    this.cbVaccine3 = cbVaccine3;
    this.cbVaccine4 = cbVaccine4;
    this.linearLayout2 = linearLayout2;
    this.linearLayout4 = linearLayout4;
    this.textView = textView;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static VaccineBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static VaccineBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.vaccine, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static VaccineBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cb_vaccine_1;
      CheckBox cbVaccine1 = ViewBindings.findChildViewById(rootView, id);
      if (cbVaccine1 == null) {
        break missingId;
      }

      id = R.id.cb_vaccine_2;
      CheckBox cbVaccine2 = ViewBindings.findChildViewById(rootView, id);
      if (cbVaccine2 == null) {
        break missingId;
      }

      id = R.id.cb_vaccine_3;
      CheckBox cbVaccine3 = ViewBindings.findChildViewById(rootView, id);
      if (cbVaccine3 == null) {
        break missingId;
      }

      id = R.id.cb_vaccine_4;
      CheckBox cbVaccine4 = ViewBindings.findChildViewById(rootView, id);
      if (cbVaccine4 == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new VaccineBinding((LinearLayout) rootView, cbVaccine1, cbVaccine2, cbVaccine3,
          cbVaccine4, linearLayout2, linearLayout4, textView, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
