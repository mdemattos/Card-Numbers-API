package mattos.matheus.bloomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mattos.matheus.bloomtest.ui.fragment.CardsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CardsFragment.newInstance())
                    .commitNow();
        }
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 1) {
            getSupportFragmentManager().beginTransaction().remove(
                    getSupportFragmentManager().getFragments().get(
                            getSupportFragmentManager().getFragments().size() - 1)).commit();
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


}
