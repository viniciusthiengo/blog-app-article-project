package br.com.thiengo.blogapp.model;

import android.util.Log;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.blogapp.presenter.PresenterMain;


public class Model {
    private AsyncHttpClient asyncHttpClient;
    private PresenterMain presenter;

    public Model(PresenterMain presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    public void retrievePosts() {

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                Log.i("Log", "getId(): " + account.getId());
                // 1315034131886868
                Log.i("Log", "getPhoneNumber(): " + account.getPhoneNumber());
                Log.i("Log", "getEmail(): " + account.getEmail());

                RequestParams requestParams = new RequestParams();
                requestParams.put( JsonHttpRequest.METODO_KEY, JsonHttpRequest.METODO_POSTS );
                requestParams.put( JsonHttpRequest.EMAIL_KEY, account.getEmail() );

                asyncHttpClient.post( presenter.getContext(),
                        JsonHttpRequest.URI,
                        requestParams,
                        new JsonHttpRequest( presenter ));
            }

            @Override
            public void onError(final AccountKitError error) {}
        });
    }
}
