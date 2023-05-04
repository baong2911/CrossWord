package edu.sjsu.android.crossword;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EasyLevelScreen extends Fragment {

    private StringBuilder selectedLetters = new StringBuilder();

    String correctWord = "BLUR";


    public EasyLevelScreen() {
        // Required empty public constructor
    }

    public static EasyLevelScreen newInstance(String param1, String param2) {
        EasyLevelScreen fragment = new EasyLevelScreen();
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
        View view = inflater.inflate(R.layout.fragment_easy_level_screen, container, false);

        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.bingo);
        TextView letterR = view.findViewById(R.id.e_R);
        TextView letterU = view.findViewById(R.id.e_U);
        TextView letterL = view.findViewById(R.id.e_L);
        TextView letterB = view.findViewById(R.id.e_B);
        TextView letterS = view.findViewById(R.id.e_S);
        TextView guessInput = view.findViewById(R.id.guessInput_e);
        Button submitButton = view.findViewById(R.id.submitBtn_e);

        letterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateGuessInput(guessInput, "R");
            }
        });

        letterU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateGuessInput(guessInput, "U");
            }
        });

        letterL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateGuessInput(guessInput, "L");
            }
        });

        letterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateGuessInput(guessInput, "B");
            }
        });

        letterS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateGuessInput(guessInput, "S");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = guessInput.getText().toString();
                if (!guess.isEmpty()){
                    if (guess.equals(correctWord)){
                        mp.start();
                        TextView e2_1 = view.findViewById(R.id.e_2_1);
                        TextView e2_2 = view.findViewById(R.id.e_2_2);
                        TextView e1_2 = view.findViewById(R.id.e_1_2);
                        TextView e2_4 = view.findViewById(R.id.e_2_4);
                        updateTextView(e2_1, "B");
                        updateTextView(e2_2, "L");
                        updateTextView(e1_2, "U");
                        updateTextView(e2_4, "R");
                    }
                    guessInput.setText("");
                    selectedLetters = new StringBuilder("");
                }
            }
        });


        return view;
    }

    private void populateGuessInput(TextView guessInput, String letter) {
        // Append the selected letter to the StringBuilder
        selectedLetters.append(letter);

        // Set the text of the guessInput TextView to the current selection
        guessInput.setText(selectedLetters.toString());
    }

    private void updateTextView(TextView textView, String text) {
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); // set text size to 20sp
        textView.setGravity(Gravity.CENTER); // center text horizontally and verticalll
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
    }

}