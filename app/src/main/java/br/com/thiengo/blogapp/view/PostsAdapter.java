package br.com.thiengo.blogapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.Post;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private PostsActivity context;
    private List<Post> posts;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivThumb;
        ImageView ivFavorito;
        TextView tvTitulo;
        TextView tvSumario;

        ViewHolder(View itemView) {
            super(itemView);

            ivThumb = (ImageView) itemView.findViewById(R.id.iv_thumb);
            ivFavorito = (ImageView) itemView.findViewById(R.id.iv_favorito);
            tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            tvSumario = (TextView) itemView.findViewById(R.id.tv_sumario);
        }

        private void setData( Post post ){
            Picasso.with( context )
                .load( post.getUriImagem() )
                .into( ivThumb );

            tvTitulo.setText( post.getTitulo() );
            tvSumario.setText( post.getSumario() );
            ivFavorito.setImageResource( post.getEhFavoritoIcone() );
        }
    }

    public PostsAdapter(PostsActivity context, List<Post> posts ){
        this.context = context;
        this.posts = posts;
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from( context )
                    .inflate(R.layout.item_post, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( posts.get( position ) );
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
