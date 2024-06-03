package com.vegasega.amnews.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import com.vegasega.amnews.R;
import com.vegasega.amnews.models.Item;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>{


    private List<Item> onBoardingItems;

    public OnboardingAdapter(List<Item> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card_item_view, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {

//        private AppCompatTextView textTitle;
////        private TextView textDescription;
//        private AppCompatImageView imageOnboarding;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
//            textTitle = itemView.findViewById(R.id.textTitle0);
////            textDescription = itemView.findViewById(R.id.textDescription);
//            imageOnboarding = itemView.findViewById(R.id.imageLogo);

        }

        void setOnBoardingData(Item onBoardingItem){
//            textTitle.setText(onBoardingItem.getName());
////            textDescription.setText(onBoardingItem.getName());
//            imageOnboarding.setImageResource(onBoardingItem.getImage());
        }
    }
}
