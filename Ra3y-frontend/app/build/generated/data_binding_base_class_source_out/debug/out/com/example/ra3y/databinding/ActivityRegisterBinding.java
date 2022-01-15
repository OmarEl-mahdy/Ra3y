// Generated by view binder compiler. Do not edit!
package com.example.ra3y.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ra3y.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button6;

  @NonNull
  public final EditText editTextNumberPassword;

  @NonNull
  public final EditText editTextNumberPassword2;

  @NonNull
  public final EditText editTextPhone;

  @NonNull
  public final EditText editTextTextEmailAddress;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final EditText editTextTextPersonName4;

  @NonNull
  public final ImageView imageView;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull Button button6,
      @NonNull EditText editTextNumberPassword, @NonNull EditText editTextNumberPassword2,
      @NonNull EditText editTextPhone, @NonNull EditText editTextTextEmailAddress,
      @NonNull EditText editTextTextPersonName, @NonNull EditText editTextTextPersonName4,
      @NonNull ImageView imageView) {
    this.rootView = rootView;
    this.button6 = button6;
    this.editTextNumberPassword = editTextNumberPassword;
    this.editTextNumberPassword2 = editTextNumberPassword2;
    this.editTextPhone = editTextPhone;
    this.editTextTextEmailAddress = editTextTextEmailAddress;
    this.editTextTextPersonName = editTextTextPersonName;
    this.editTextTextPersonName4 = editTextTextPersonName4;
    this.imageView = imageView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button6;
      Button button6 = ViewBindings.findChildViewById(rootView, id);
      if (button6 == null) {
        break missingId;
      }

      id = R.id.editTextNumberPassword;
      EditText editTextNumberPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextNumberPassword == null) {
        break missingId;
      }

      id = R.id.editTextNumberPassword2;
      EditText editTextNumberPassword2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextNumberPassword2 == null) {
        break missingId;
      }

      id = R.id.editTextPhone;
      EditText editTextPhone = ViewBindings.findChildViewById(rootView, id);
      if (editTextPhone == null) {
        break missingId;
      }

      id = R.id.editTextTextEmailAddress;
      EditText editTextTextEmailAddress = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextEmailAddress == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName4;
      EditText editTextTextPersonName4 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName4 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, button6,
          editTextNumberPassword, editTextNumberPassword2, editTextPhone, editTextTextEmailAddress,
          editTextTextPersonName, editTextTextPersonName4, imageView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}