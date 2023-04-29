package edu.sjsu.android.crossword;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LevelSelection extends Fragment {

    public LevelSelection() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_level_selection, container, false);
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.click);
        TextView easy = view.findViewById(R.id.easy);
        TextView medium = view.findViewById(R.id.medium);
        TextView hard = view.findViewById(R.id.hard);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                int destinationId = 0;
                switch (view.getId()) {
                    case R.id.easy:
                        destinationId = R.id.action_levelSelection_to_easyLevelScreen;
                        break;
                    case R.id.medium:
                        destinationId = R.id.action_levelSelection_to_mediumLevelScreen;
                        break;
                    case R.id.hard:
                        destinationId = R.id.action_levelSelection_to_hardLevelScreen;
                        break;
                }
                Navigation.findNavController(view).navigate(destinationId);
            }
        };

        easy.setOnClickListener(onClickListener);
        medium.setOnClickListener(onClickListener);
        hard.setOnClickListener(onClickListener);
        return view;
    }
}