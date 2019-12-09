package mattos.matheus.bloomtest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mattos.matheus.bloomtest.R;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<String> cardsDescriptionList;

    public CardAdapter(Context context, ArrayList<String> cardsDescriptionList) {
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
        String string = cardsDescriptionList.get(position);
        CardViewHolder viewHolder = (CardViewHolder) holder;

        viewHolder.cardTitle.setText(string);
        viewHolder.cardDescription.setText(string);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        TextView cardDescription;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardDescription = itemView.findViewById(R.id.card_description);
        }
    }
}
