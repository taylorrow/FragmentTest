package com.midigame.fragmenttest.fragment_2;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.midigame.fragmenttest.MainActivity;
import com.midigame.fragmenttest.R;
import com.midigame.fragmenttest.retrofit.App;
import com.midigame.fragmenttest.retrofit.Data;
import com.midigame.fragmenttest.retrofit.DataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTwo extends Fragment {

    private RecyclerView recyclerView;
    List<DataModel> models;
    private static Bundle bundle;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, null);
        getAllWidgets(rootView);
        getData();
        return rootView;
    }

    private void getAllWidgets(View view) {
        models = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFragmentTwo);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.instance));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(MainActivity.instance, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void setAdapter(List<DataModel> list) {
        recyclerView.setAdapter(new DataAdapterFragmentTwo(MainActivity.instance, list));
    }

    private void getData() {

        App.getApi().getData("dog").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                models = data.getData();
                setAdapter(models);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(MainActivity.instance, "Connection problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        bundle = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        bundle.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (bundle != null) {
            Parcelable listState = bundle.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }
}
