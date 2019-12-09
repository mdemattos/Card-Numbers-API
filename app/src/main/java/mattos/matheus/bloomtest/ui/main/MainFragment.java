package mattos.matheus.bloomtest.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mattos.matheus.bloomtest.R;
import mattos.matheus.bloomtest.model.Card;
import mattos.matheus.bloomtest.ui.adapter.CardAdapter;

public class MainFragment extends Fragment {

    MainViewModel viewModel;
    RecyclerView recyclerView;
    CardAdapter cardAdapter;
    List<Card> triviaList = new ArrayList<>();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.fetchData();
        recyclerView = getView().findViewById(R.id.recycler_view_cards);
        //viewModel.getTriviaLiveData().observe(this, response -> triviaList.add(response));

        viewModel.getTriviaLiveData().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                triviaList.addAll(cards);
                setupRecyclerView();
            }
        });
    }

    private void setupRecyclerView() {
        if (cardAdapter == null) {
            cardAdapter = new CardAdapter(getContext(), triviaList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(cardAdapter);
        } else {
            cardAdapter.notifyDataSetChanged();
        }
    }
}
