package mattos.matheus.bloomtest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import mattos.matheus.bloomtest.R;
import mattos.matheus.bloomtest.model.Card;
import mattos.matheus.bloomtest.ui.fragment.YearTriviaFragment;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Card> cardsDescriptionList;

    public CardAdapter(Context context, List<Card>cardsDescriptionList) {
        this.context = context;
        this.cardsDescriptionList = cardsDescriptionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);
        return new CardViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String title = cardsDescriptionList.get(position).getTitle();
        String description = cardsDescriptionList.get(position).getDescription();

        CardViewHolder viewHolder = (CardViewHolder) holder;
        viewHolder.cardTitle.setText(title);
        viewHolder.cardDescription.setText(description);

        viewHolder.cardContainer.setOnClickListener(view ->
                navigateToYearTriviaActivity(cardsDescriptionList.get(position).getNumber())
        );
    }

    @Override
    public int getItemCount() {
        return cardsDescriptionList.size();
    }

    private void navigateToYearTriviaActivity(int year) {
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, YearTriviaFragment.newInstance(year))
                .commitNow();
    }


    class CardViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardContainer;
        TextView cardTitle;
        TextView cardDescription;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardContainer = itemView.findViewById(R.id.card_container);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardDescription = itemView.findViewById(R.id.card_description);
        }
    }
}
