// Generated by view binder compiler. Do not edit!
package com.example.ra3y.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
<<<<<<< HEAD
import android.widget.ProgressBar;
import android.widget.TextView;
||||||| 9ff5421
import android.widget.RelativeLayout;
=======
import android.widget.RelativeLayout;
import android.widget.TextView;
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ra3y.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText email;

  @NonNull
<<<<<<< HEAD
  public final TextView forget;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;
||||||| 9ff5421
  public final ImageView icon;
=======
  public final TextView forget;

  @NonNull
  public final ImageView icon;
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25

  @NonNull
  public final Button loginButton;

  @NonNull
  public final TextView or;

  @NonNull
  public final TextView or;

  @NonNull
  public final EditText pass;

  @NonNull
  public final ProgressBar progressBar;

<<<<<<< HEAD
  @NonNull
  public final Button registerButton;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull EditText email,
      @NonNull TextView forget, @NonNull ImageView imageView3, @NonNull ImageView imageView4,
      @NonNull Button loginButton, @NonNull TextView or, @NonNull EditText pass,
      @NonNull ProgressBar progressBar, @NonNull Button registerButton) {
||||||| 9ff5421
  private ActivityLoginBinding(@NonNull RelativeLayout rootView, @NonNull EditText email,
      @NonNull ImageView icon, @NonNull Button loginButton, @NonNull Button newAccountButton,
      @NonNull EditText pass, @NonNull ImageView usernameIcon) {
=======
  private ActivityLoginBinding(@NonNull RelativeLayout rootView, @NonNull EditText email,
      @NonNull TextView forget, @NonNull ImageView icon, @NonNull Button loginButton,
      @NonNull Button newAccountButton, @NonNull TextView or, @NonNull EditText pass,
      @NonNull ImageView usernameIcon) {
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25
    this.rootView = rootView;
    this.email = email;
<<<<<<< HEAD
    this.forget = forget;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
||||||| 9ff5421
    this.icon = icon;
=======
    this.forget = forget;
    this.icon = icon;
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25
    this.loginButton = loginButton;
<<<<<<< HEAD
    this.or = or;
||||||| 9ff5421
    this.newAccountButton = newAccountButton;
=======
    this.newAccountButton = newAccountButton;
    this.or = or;
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25
    this.pass = pass;
    this.progressBar = progressBar;
    this.registerButton = registerButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

<<<<<<< HEAD
      id = R.id.forget;
      TextView forget = ViewBindings.findChildViewById(rootView, id);
      if (forget == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
||||||| 9ff5421
      id = R.id.icon;
      ImageView icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
=======
      id = R.id.forget;
      TextView forget = ViewBindings.findChildViewById(rootView, id);
      if (forget == null) {
        break missingId;
      }

      id = R.id.icon;
      ImageView icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25
        break missingId;
      }

      id = R.id.loginButton;
      Button loginButton = ViewBindings.findChildViewById(rootView, id);
      if (loginButton == null) {
        break missingId;
      }

      id = R.id.or;
      TextView or = ViewBindings.findChildViewById(rootView, id);
      if (or == null) {
        break missingId;
      }

      id = R.id.or;
      TextView or = ViewBindings.findChildViewById(rootView, id);
      if (or == null) {
        break missingId;
      }

      id = R.id.pass;
      EditText pass = ViewBindings.findChildViewById(rootView, id);
      if (pass == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.registerButton;
      Button registerButton = ViewBindings.findChildViewById(rootView, id);
      if (registerButton == null) {
        break missingId;
      }

<<<<<<< HEAD
      return new ActivityLoginBinding((ConstraintLayout) rootView, email, forget, imageView3,
          imageView4, loginButton, or, pass, progressBar, registerButton);
||||||| 9ff5421
      return new ActivityLoginBinding((RelativeLayout) rootView, email, icon, loginButton,
          newAccountButton, pass, usernameIcon);
=======
      return new ActivityLoginBinding((RelativeLayout) rootView, email, forget, icon, loginButton,
          newAccountButton, or, pass, usernameIcon);
>>>>>>> 180dc3c2fa4cc4d9e22e171fb5d81ad26a92ee25
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
