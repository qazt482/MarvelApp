package com.example.rakesh.marvelapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.rakesh.marvelapp.MarvelApplication;
import com.example.rakesh.marvelapp.R;
import com.example.rakesh.marvelapp.data.ComicsRepository;
import com.example.rakesh.marvelapp.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ComicListActivity extends AppCompatActivity implements ComicListView{

    private ComicListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ComicsRepository comicsRepository = ((MarvelApplication) getApplication()).comicsRepository;
        presenter = new ComicListPresenter(this, comicsRepository, Schedulers.io(), AndroidSchedulers.mainThread());
        presenter.getComics();
    }

    @Override
    public void addComics(List<Comic> comics) {
        View recyclerView = findViewById(R.id.comic_list);
        assert recyclerView != null;
        ((RecyclerView) recyclerView).setAdapter(new SimpleItemRecyclerViewAdapter(comics, this));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Comic> mValues;
        private ComicListActivity context;

        public SimpleItemRecyclerViewAdapter(List<Comic> items, ComicListActivity comicListActivity) {
            mValues = items;
            context = comicListActivity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comic_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mTitleView.setText(mValues.get(position).getTitle());
            Picasso.with(context).load(mValues.get(position).getImageUrl()).into(holder.mImageView);

            holder.mView.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context, ComicDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ComicDetailFragment.ARG_ITEM_ID, holder.mItem);
                intent.putExtras(bundle);

                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mTitleView;
            public final ImageView mImageView;
            public Comic mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = view.findViewById(R.id.title);
                mImageView = view.findViewById(R.id.thumbnail);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTitleView.getText() + "'";
            }
        }
    }
}
