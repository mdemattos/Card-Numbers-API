package mattos.matheus.bloomtest.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mattos.matheus.bloomtest.R;
import mattos.matheus.bloomtest.model.Card;
import mattos.matheus.bloomtest.ui.adapter.CardAdapter;
import mattos.matheus.bloomtest.viewmodel.MainViewModel;

public class CardsFragment extends Fragment {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<Card> triviaList = new ArrayList<>();

    public static CardsFragment newInstance() {
        return new CardsFragment();
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
        viewModel.fetchNumbersTriviaData();
        recyclerView = getView().findViewById(R.id.recycler_view_cards);

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Fetching facts...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        viewModel.getTriviaLiveData().observe(this, cards -> {
            dialog.hide();
            if (cards != null && !cards.isEmpty()) {
                triviaList.addAll(cards);
                setupRecyclerView();
            } else {
                Toast.makeText(getContext(), "Error fetching the data from Numbers API",
                        Toast.LENGTH_LONG).show();
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
