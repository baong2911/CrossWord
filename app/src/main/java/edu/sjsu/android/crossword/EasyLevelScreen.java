package edu.sjsu.android.crossword;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

public class EasyLevelScreen extends Fragment {

    private StringBuilder selectedLetters = new StringBuilder();
    Set<String> submittedAnswers = new HashSet<>();
    int correctAnswerSubmitted = 0;
    int score = 0;


    String[] correctWords = {"BLUR", "BURL", "SLUR", "RUB", "BUS"};


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
        final MediaPlayer complete = MediaPlayer.create(getContext(), R.raw.complete);
        TextView letterR = view.findViewById(R.id.e_R);
        TextView letterU = view.findViewById(R.id.e_U);
        TextView letterL = view.findViewById(R.id.e_L);
        TextView letterB = view.findViewById(R.id.e_B);
        TextView letterS = view.findViewById(R.id.e_S);
        TextView guessInput = view.findViewById(R.id.guessInput_e);
        Button submitButton = view.findViewById(R.id.submitBtn_e);
        TextView e_score = view.findViewById(R.id.e_score);

        View.OnClickListener letterClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letter = ((TextView) v).getText().toString();
                populateGuessInput(guessInput, letter);
            }
        };

        letterR.setOnClickListener(letterClickListener);
        letterU.setOnClickListener(letterClickListener);
        letterL.setOnClickListener(letterClickListener);
        letterB.setOnClickListener(letterClickListener);
        letterS.setOnClickListener(letterClickListener);

        TextView e2_1 = view.findViewById(R.id.e_2_1);
        TextView e2_2 = view.findViewById(R.id.e_2_2);
        TextView e1_2 = view.findViewById(R.id.e_1_2);
        TextView e2_4 = view.findViewById(R.id.e_2_4);

        TextView e1_1 = view.findViewById(R.id.e_1_1);
        TextView e1_3 = view.findViewById(R.id.e_1_3);
        TextView e3_1 = view.findViewById(R.id.e_3_1);
        TextView e3_2 = view.findViewById(R.id.e_3_2);
        TextView e3_3 = view.findViewById(R.id.e_3_3);
        TextView e4_1 = view.findViewById(R.id.e_4_1);
        TextView e4_2 = view.findViewById(R.id.e_4_2);
        TextView e5_1 = view.findViewById(R.id.e_5_1);
        TextView e5_2 = view.findViewById(R.id.e_5_2);
        TextView e5_3 = view.findViewById(R.id.e_5_3);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = guessInput.getText().toString();
                if (!guess.isEmpty() && !submittedAnswers.contains(guess)){
                    int correctIndex = -1;
                    for (int i = 0; i < correctWords.length; i++){
                        if (guess.equals(correctWords[i])){
                            correctIndex = i;
                            correctAnswerSubmitted +=1;
                            submittedAnswers.add(guess);
                            break;
                        }
                    }

                    if (correctIndex != -1){
                        mp.start();
                        score += 150;
                        e_score.setText("Score : " + score);
                        switch (correctWords[correctIndex]){
                            case "BLUR":

                                updateTextView(e2_1, "B");
                                updateTextView(e2_2, "L");
                                updateTextView(e1_2, "U");
                                updateTextView(e2_4, "R");
                                break;
                            case "BURL":
                                updateTextView(e1_1, "B");
                                updateTextView(e1_2, "U");
                                updateTextView(e1_3, "R");
                                updateTextView(e3_2, "L");
                                break;
                            case "SLUR":
                                updateTextView(e3_1, "S");
                                updateTextView(e3_2, "L");
                                updateTextView(e3_3, "U");
                                updateTextView(e4_1, "R");
                                break;
                            case "RUB":
                                updateTextView(e4_1, "R");
                                updateTextView(e4_2, "U");
                                updateTextView(e5_1, "B");
                                break;
                            case "BUS":
                                updateTextView(e5_1, "B");
                                updateTextView(e5_2, "U");
                                updateTextView(e5_3, "S");
                                break;

                        }
                        if (correctAnswerSubmitted == correctWords.length){
                            complete.start();
                            ScoreFragment scoreFragment = new ScoreFragment();
                            // Set any data that you want to pass to the fragment using arguments
                            Bundle args = new Bundle();
                            args.putInt("score", score);
                            scoreFragment.setArguments(args);
                            // Show the fragment using the FragmentManager
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            scoreFragment.show(fragmentManager, "score");
                        }
                    }
                }
                guessInput.setText("");
                selectedLetters = new StringBuilder("");
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
        textView.setTextSize(15); // set text size to 20sp
        textView.setGravity(Gravity.CENTER); // center text horizontally and vertical
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
    }
}