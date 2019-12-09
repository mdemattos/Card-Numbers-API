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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mattos.matheus.bloomtest.R;
import mattos.matheus.bloomtest.ui.adapter.CardAdapter;

public class MainFragment extends Fragment {

    MainViewModel viewModel;
    RecyclerView recyclerView;
    CardAdapter cardAdapter;

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
        // TODO: Use the ViewModel
        recyclerView = getView().findViewById(R.id.recycler_view_cards);
        viewModel.getUserMutableLiveData().observe(this, userListUpdateObserver);
    }

    Observer<ArrayList<String>> userListUpdateObserver = new Observer<ArrayList<String>>() {
        @Override
        public void onChanged(ArrayList<String> userArrayList) {
            cardAdapter = new CardAdapter(getContext(), userArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(cardAdapter);
        }
    };

}
