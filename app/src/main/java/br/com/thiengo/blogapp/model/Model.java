package br.com.thiengo.blogapp.model;

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
        RequestParams requestParams = new RequestParams(
                JsonHttpRequest.METODO_KEY,
                JsonHttpRequest.METODO_POSTS);

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }
}
