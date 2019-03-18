package com.theacquits.mobile.theaquits.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theacquits.mobile.theaquits.MainActivity;
import com.theacquits.mobile.theaquits.R;
import com.theacquits.mobile.theaquits.adapters.TopicsAdapter;
import com.theacquits.mobile.theaquits.model.HeaderItem;
import com.theacquits.mobile.theaquits.model.ListItem;
import com.theacquits.mobile.theaquits.model.TopicHeader;
import com.theacquits.mobile.theaquits.model.TopicItem;
import com.theacquits.mobile.theaquits.model.Topics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversFragment extends Fragment {


    private static final Random RANDOM = new Random();

    @NonNull
    private List<ListItem> items = new ArrayList<>();


    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;

    TopicsAdapter topicsAdapter;


    public ConversFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_convers, container, false);

        ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Map<String, List<Topics>> events = toMap(loadTopics());


        for (String headers : events.keySet()) {
            HeaderItem header = new HeaderItem();
            header.setHeader(headers);
            items.add(header);
            for (Topics topics : events.get(headers)) {
                TopicItem item = new TopicItem();
                item.setTopics(topics);
                items.add(item);
            }
        }

        recyclerView.setAdapter(new TopicsAdapter(getContext(),items));


        return view;
    }

    @NonNull
    private String getRandomStringNumber() {
        return String.valueOf(RANDOM.nextInt(100000));

    }



    private List<Topics> loadTopics(){
        List<Topics> topics = new ArrayList<>();
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","General"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","General"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","General"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Info"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Info"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Party Time"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Party Time"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Make Money"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Make Money"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Live Large"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Live Large"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","Test Love"));
        topics.add(new Topics("Slavery","for everyone","492 Posts", "753 Comments","New Me"));

        return topics;
    }

    @NonNull
    private Map<String, List<Topics>> toMap(List<Topics> topics){
        Map<String , List<Topics>> map = new TreeMap<>();
        for (Topics topics1 : topics){
            List<Topics> value = map.get(topics1.getCategory());
            if (value == null) {
                value = new ArrayList<>();
                map.put(topics1.getCategory(), value);
            }
            value.add(topics1);
        }return map;
    }




}
