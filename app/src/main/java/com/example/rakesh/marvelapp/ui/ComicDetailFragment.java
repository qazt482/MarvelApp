package com.example.rakesh.marvelapp.ui;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rakesh.marvelapp.R;
import com.example.rakesh.marvelapp.model.Comic;

public class ComicDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    public static Comic mComic;

    public ComicDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getActivity().getIntent().getExtras();

        mComic = (Comic) extras.getSerializable(ARG_ITEM_ID);
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mComic.getTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_detail, container, false);

        if (mComic != null) {
            ((TextView) rootView.findViewById(R.id.comic_detail)).setText(mComic.getDescription());
        }

        return rootView;
    }
}
