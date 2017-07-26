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
import com.example.rakesh.marvelapp.model.DummyContent;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * An activity representing a list of Comics. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ComicDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ComicListActivity extends AppCompatActivity implements ComicListView{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ComicListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ComicsRepository comicsRepository = ((MarvelApplication) getApplication()).comicsRepository;
        presenter = new ComicListPresenter(this, comicsRepository);
        presenter.getComics();

//        View recyclerView = findViewById(R.id.comic_list);
//        assert recyclerView != null;
//        ((RecyclerView) recyclerView).setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS, this));

        if (findViewById(R.id.comic_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public void addComics(List<Comic> comics) {
        View recyclerView = findViewById(R.id.comic_list);
        assert recyclerView != null;
        ((RecyclerView) recyclerView).setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS, this));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;
        private ComicListActivity context;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items, ComicListActivity comicListActivity) {
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
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);
            Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.mImageView);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ComicDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ComicDetailFragment fragment = new ComicDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.comic_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ComicDetailActivity.class);
                        intent.putExtra(ComicDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final ImageView mImageView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mImageView = view.findViewById(R.id.thumbnail);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
