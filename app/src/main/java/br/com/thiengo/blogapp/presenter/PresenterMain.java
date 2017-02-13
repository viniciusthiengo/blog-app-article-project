package br.com.thiengo.blogapp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.blogapp.model.Model;
import br.com.thiengo.blogapp.view.MainActivity;
import br.com.thiengo.blogapp.view.PostsActivity;


public class PresenterMain {
    private static PresenterMain instance;
    private Model model;
    private PostsActivity activity;
    private ArrayList<Post> posts = new ArrayList<>();


    private PresenterMain(){
        model = new Model( this );
    }

    public static PresenterMain getInstance(){
        if( instance == null ){
            instance = new PresenterMain();
        }
        return instance;
    }

    public void setActivity(PostsActivity activity){
        this.activity = activity;
    }

    public Activity getContext() {
        return activity;
    }

    public void retrievePosts(Bundle savedInstanceState) {
        if( savedInstanceState != null ){
            posts = savedInstanceState.getParcelableArrayList( Post.POSTS_KEY );
            return;
        }
        model.retrievePosts();
    }

    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        activity.showProgressBar( visibilidade );
    }

    public void updateListaRecycler(Object object) {
        List<Post> postsCarregados = (List<Post>) object;
        posts.clear();
        posts.addAll( postsCarregados );
        activity.updateListaRecycler();
        showProgressBar( !(posts.size() > 0) );
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
