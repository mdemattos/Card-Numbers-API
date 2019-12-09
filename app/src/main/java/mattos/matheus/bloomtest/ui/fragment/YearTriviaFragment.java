package mattos.matheus.bloomtest.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import mattos.matheus.bloomtest.R;
import mattos.matheus.bloomtest.viewmodel.MainViewModel;

public class YearTriviaFragment extends Fragment {

    private MainViewModel viewModel;

    public static YearTriviaFragment newInstance(int year) {
        YearTriviaFragment fragment = new YearTriviaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", year);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.year_trivia_fragment, container, false);
        TextView triviaDescription = fragmentView.findViewById(R.id.text_description);
        TextView textTitle = fragmentView.findViewById(R.id.text_year_title);


        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait");
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        int year = getArguments().getInt("key");
        textTitle.setText("Year " + (1900 + year));

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.fetchYearTriviaData(year);
        viewModel.getTriviaYearLiveData().observe(this, triviaData -> {
            triviaDescription.setText(triviaData);
            dialog.dismiss();
        });
        return fragmentView;
    }
}
