package com.theacquits.mobile.theaquits.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theacquits.mobile.theaquits.MainActivity;
import com.theacquits.mobile.theaquits.R;
import com.theacquits.mobile.theaquits.model.HeaderItem;
import com.theacquits.mobile.theaquits.model.ListItem;
import com.theacquits.mobile.theaquits.model.TopicHeader;
import com.theacquits.mobile.theaquits.model.TopicItem;
import com.theacquits.mobile.theaquits.model.Topics;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 3/18/19.
 */

public class TopicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LAYOUT_HEADER = 0;
    private static final int LAYOUT_CHILD = 1;



    private LayoutInflater inflater;
    private Context context;
    private List<ListItem> listItemArrayList;

    public TopicsAdapter(Context context, List<ListItem> listItemArrayList) {

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listItemArrayList = listItemArrayList;
    }

    @Override
    public int getItemCount() {
        return listItemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listItemArrayList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        if (viewType == LAYOUT_HEADER) {
            View view = inflater.inflate(R.layout.topic_header, parent, false);
            holder = new MyViewHolderHeader(view);
        } else {
            View view = inflater.inflate(R.layout.topics, parent, false);
            holder = new MyViewHolderChild(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder.getItemViewType() == LAYOUT_HEADER) {
            HeaderItem topicHeader = (HeaderItem) listItemArrayList.get(position);
            MyViewHolderHeader vaultItemHolder = (MyViewHolderHeader) holder;
            vaultItemHolder.header.setText(topicHeader.getHeader());

        } else {
            TopicItem topics = (TopicItem) listItemArrayList.get(position);
            MyViewHolderChild vaultItemHolder = (MyViewHolderChild) holder;
            vaultItemHolder.topic.setText(topics.getTopics().getTopic());
            vaultItemHolder.description.setText(topics.getTopics().getDescription());
            vaultItemHolder.posts.setText(topics.getTopics().getPosts());
            vaultItemHolder.comments.setText(topics.getTopics().getComments());


        }

    }


    class MyViewHolderHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        ImageView icon;

        @BindView(R.id.header)
        TextView header;


        public MyViewHolderHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

    }

    class MyViewHolderChild extends RecyclerView.ViewHolder {


        @BindView(R.id.topic)
        TextView topic;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.posts)
        TextView posts;

        @BindView(R.id.comments)
        TextView comments;
        public MyViewHolderChild(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

    }
}
