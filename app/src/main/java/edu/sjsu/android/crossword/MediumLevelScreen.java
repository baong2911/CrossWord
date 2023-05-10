package edu.sjsu.android.crossword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MediumLevelScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MediumLevelScreen extends Fragment {

    public MediumLevelScreen() {
        // Required empty public constructor
    }

    public static MediumLevelScreen newInstance(String param1, String param2) {
        MediumLevelScreen fragment = new MediumLevelScreen();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medium_level_screen, container, false);
    }
}