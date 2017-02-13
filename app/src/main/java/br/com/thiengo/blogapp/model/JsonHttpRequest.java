package br.com.thiengo.blogapp.model;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterMain;
import cz.msebera.android.httpclient.Header;


public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://seu_host/blog-app/ctrl/CtrlAdmin.php";
    public static final String METODO_KEY = "metodo";
    public static final String METODO_POSTS = "get-posts";
    public static final String EMAIL_KEY = "email";

    private PresenterMain presenter;


    public JsonHttpRequest( PresenterMain presenter ){
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        presenter.showProgressBar( true );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();
        ArrayList<Post> posts = new ArrayList<>();
        Post p;

        for( int i = 0; i < response.length(); i++ ){
            try{
                p = gson.fromJson( response.getJSONObject( i ).toString(), Post.class );
                posts.add( p );
            }
            catch(JSONException e){}
        }
        presenter.updateListaRecycler( posts );
    }

    @Override
    public void onFinish() {
        presenter.showProgressBar( false );
    }
}
