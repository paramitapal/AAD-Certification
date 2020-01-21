/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package paraman.com.recyclerviewbasic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import paraman.com.recyclerviewbasic.dbUtil.Post;


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<Post> mDataSet;
    private static OnDeleteButtonClickListener onDeleteButtonClickListener;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private Button btnDelete;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
            void bind(final Post post) {
                if (post != null) {
                    textView.setText(post.getTitle());
                    // Define click listener for the ViewHolder's View.
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                    if (onDeleteButtonClickListener != null)
                                        onDeleteButtonClickListener.onDeleteButtonClicked(post);
                            Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                        }
                    });
                }
            }



        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param listener String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(OnDeleteButtonClickListener listener) {
        mDataSet = new ArrayList<>();
        this.onDeleteButtonClickListener = listener;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.bind(mDataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

        public void setData(List<Post> newData) {
            if (mDataSet != null) {
                Log.d(TAG,"Data in adapter obseving "+mDataSet.toString());
                PostDiffCallback postDiffCallback = new PostDiffCallback(mDataSet, newData);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

                mDataSet.clear();
                mDataSet.addAll(newData);
                diffResult.dispatchUpdatesTo(this);
            } else {
                // first initialization
                mDataSet = newData;
            }
        }

    class PostDiffCallback extends DiffUtil.Callback {

        private final List<Post> oldPosts, newPosts;

        public PostDiffCallback(List<Post> oldPosts, List<Post> newPosts) {
            this.oldPosts = oldPosts;
            this.newPosts = newPosts;
        }

        @Override
        public int getOldListSize() {
            return oldPosts.size();
        }

        @Override
        public int getNewListSize() {
            return newPosts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).getId() == newPosts.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }




    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClicked(Post post);
    }

}
