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
<<<<<<< HEAD
  public final Button button6;

  @NonNull
  public final EditText editTextNumberPassword;
=======
  public final EditText FirstName;
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5

  @NonNull
  public final EditText LastName;

  @NonNull
  public final EditText confirmpass;

  @NonNull
  public final EditText email;

  @NonNull
  public final ImageView imageView;

<<<<<<< HEAD
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
=======
  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final EditText pass;

  @NonNull
  public final EditText phonenumber;

  @NonNull
  public final ProgressBar progressBar2;

  @NonNull
  public final Button submitReg;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull EditText FirstName,
      @NonNull EditText LastName, @NonNull EditText confirmpass, @NonNull EditText email,
      @NonNull ImageView imageView, @NonNull ImageView imageView4, @NonNull EditText pass,
      @NonNull EditText phonenumber, @NonNull ProgressBar progressBar2, @NonNull Button submitReg) {
    this.rootView = rootView;
    this.FirstName = FirstName;
    this.LastName = LastName;
    this.confirmpass = confirmpass;
    this.email = email;
    this.imageView = imageView;
    this.imageView4 = imageView4;
    this.pass = pass;
    this.phonenumber = phonenumber;
    this.progressBar2 = progressBar2;
    this.submitReg = submitReg;
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5
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
<<<<<<< HEAD
      id = R.id.button6;
      Button button6 = ViewBindings.findChildViewById(rootView, id);
      if (button6 == null) {
        break missingId;
      }

      id = R.id.editTextNumberPassword;
      EditText editTextNumberPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextNumberPassword == null) {
=======
      id = R.id.FirstName;
      EditText FirstName = ViewBindings.findChildViewById(rootView, id);
      if (FirstName == null) {
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5
        break missingId;
      }

      id = R.id.LastName;
      EditText LastName = ViewBindings.findChildViewById(rootView, id);
      if (LastName == null) {
        break missingId;
      }

      id = R.id.confirmpass;
      EditText confirmpass = ViewBindings.findChildViewById(rootView, id);
      if (confirmpass == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

<<<<<<< HEAD
      return new ActivityRegisterBinding((ConstraintLayout) rootView, button6,
          editTextNumberPassword, editTextNumberPassword2, editTextPhone, editTextTextEmailAddress,
          editTextTextPersonName, editTextTextPersonName4, imageView);
=======
      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.pass;
      EditText pass = ViewBindings.findChildViewById(rootView, id);
      if (pass == null) {
        break missingId;
      }

      id = R.id.phonenumber;
      EditText phonenumber = ViewBindings.findChildViewById(rootView, id);
      if (phonenumber == null) {
        break missingId;
      }

      id = R.id.progressBar2;
      ProgressBar progressBar2 = ViewBindings.findChildViewById(rootView, id);
      if (progressBar2 == null) {
        break missingId;
      }

      id = R.id.submitReg;
      Button submitReg = ViewBindings.findChildViewById(rootView, id);
      if (submitReg == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, FirstName, LastName,
          confirmpass, email, imageView, imageView4, pass, phonenumber, progressBar2, submitReg);
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
