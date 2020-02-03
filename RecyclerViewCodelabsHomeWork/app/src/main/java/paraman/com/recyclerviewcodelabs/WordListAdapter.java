package paraman.com.recyclerviewcodelabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {

    private final LinkedList<String> mWordList;
    private final LinkedList<String> mWordHeadingList;
    private LayoutInflater mInflater;
    private Context mContext;

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.wordlist_item,
                parent, false);
        Log.d("TAG","Inside create view holder ");
        return new WordViewHolder(mItemView, this);
    }

    public WordListAdapter(Context context,
                           LinkedList<String> wordList,LinkedList<String> wordHeadingList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mWordHeadingList = wordHeadingList;
        this.mContext = context;

    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(holder.getAdapterPosition());
        String currentHeading = mWordHeadingList.get(holder.getAdapterPosition());
        holder.wordItemView.setText(mCurrent);
        holder.wordItemViewHeading.setText(currentHeading);
        Log.d("TAG","Inside bind view holder "+ mWordList.get(position));
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        public final TextView wordItemViewHeading;

        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            wordItemViewHeading = itemView.findViewById(R.id.wordHeading);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }


        @Override
                public void onClick(View view) {
        // Get the position of the item that was clicked.
                    int mPosition = getLayoutPosition();
        // Use that to access the affected item in mWordList.
                    String element = mWordList.get(mPosition);
//        // Change the word in the mWordList.
//                    mWordList.set(mPosition, "Clicked! " + element);  //TODO Change as per your requirement
        // Notify the adapter, that the data has changed so it can
        // update the RecyclerView to display the data.
//                    mAdapter.notifyDataSetChanged();
            Intent intent = new Intent(mContext,ChildActivity.class);
            intent.putExtra("HEADING",mWordHeadingList.get(mPosition));
            Log.d("TAG","The desc passed "+mWordHeadingList.get(mPosition));

            mContext.startActivity(intent);
        }
    }
}

