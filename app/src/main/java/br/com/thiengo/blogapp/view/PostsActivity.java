package br.com.thiengo.blogapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterMain;

public class PostsActivity extends AppCompatActivity {
    private PresenterMain presenter;
    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = PresenterMain.getInstance();
        presenter.setActivity( this );
        intiViews();

        presenter.retrievePosts( savedInstanceState );
    }

    private void intiViews() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_posts);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration( divider );

        adapter = new PostsAdapter( this, presenter.getPosts() );
        recyclerView.setAdapter( adapter );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Post.POSTS_KEY, presenter.getPosts());
        super.onSaveInstanceState(outState);
    }

    public void updateListaRecycler(){
        adapter.notifyDataSetChanged();
    }

    public void showProgressBar( int visibilidade ){
        findViewById(R.id.pb_loading).setVisibility( visibilidade );
    }
}
