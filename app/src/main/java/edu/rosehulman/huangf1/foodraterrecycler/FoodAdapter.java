package edu.rosehulman.huangf1.foodraterrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by huangf1 on 1/6/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private Random mRandom = new Random();
    private ArrayList<Food> mFoods = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public FoodAdapter(Context context, RecyclerView view) {
        mContext = context;
        mRecyclerView = view;
        for (int i = 0; i < 5; i++) {
            mFoods.add(getRandomFood());
        }
    }

    private Food getRandomFood() {
        HashMap<String, Integer> sDefaultNamesAndIds = new HashMap<>();
        sDefaultNamesAndIds.put("banana", R.drawable.banana);
        sDefaultNamesAndIds.put("broccoli", R.drawable.broccoli);
        sDefaultNamesAndIds.put("homemade bread", R.drawable.bread);
        sDefaultNamesAndIds.put("chicken", R.drawable.chicken);
        sDefaultNamesAndIds.put("chocolate", R.drawable.chocolate);
        sDefaultNamesAndIds.put("ice cream", R.drawable.icecream);
        sDefaultNamesAndIds.put("lima beans", R.drawable.limabeans);
        sDefaultNamesAndIds.put("steak", R.drawable.steak);

        List<String> keys = new ArrayList<>(sDefaultNamesAndIds.keySet());
        String randomKey = keys.get(mRandom.nextInt(keys.size()));

        return new Food(randomKey, sDefaultNamesAndIds.get(randomKey), 0);
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Food food = mFoods.get(position);
        holder.mFoodImageView.setImageResource(food.getResourceID());
        holder.mNameTextView.setText(food.getmName());
        holder.mFoodRatingBar.setRating(food.getRating());
        holder.mFoodRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (b) {
                    food.setRating((int)v);
                }
            }
        });
    }

    public Food addFood() {
        Food food = getRandomFood();
        mFoods.add(0, food);
        notifyItemInserted(0);
        mRecyclerView.scrollToPosition(0);
        return food;
    }

    public void removeFood(int position) {
        mFoods.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mFoodImageView;
        private TextView mNameTextView;
        private RatingBar mFoodRatingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    removeFood(getAdapterPosition());
                    return false;
                }
            });

            mFoodImageView = (ImageView)itemView.findViewById(R.id.food_image_view);
            mNameTextView = (TextView)itemView.findViewById(R.id.food_name_text_view);
            mFoodRatingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
        }

    }
}
