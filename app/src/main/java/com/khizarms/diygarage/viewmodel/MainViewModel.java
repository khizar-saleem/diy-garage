package com.khizarms.diygarage.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.khizarms.diygarage.R;
import com.khizarms.diygarage.service.GoogleSignInService;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<Throwable> throwable;


  public MainViewModel(@NonNull Application application) {
    super(application);
    account = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setAccount(GoogleSignInAccount account) {
    this.account.setValue(account);
  }

  private String getAuthorizationHeader() {
    String token = getApplication().getString(R.string.oauth_header,
        GoogleSignInService.getInstance().getAccount().getValue().getIdToken());
    Log.d("OAuth2.0 token", token);
    return token;
  }


}
