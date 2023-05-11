package edu.sjsu.android.crossword;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MediumLevelScreen extends Fragment {

    private StringBuilder selectedLetters = new StringBuilder();
    Set<String> submittedAnswers = new HashSet<>();
    int correctAnswerSubmitted = 0;
    int score = 0;

    CrosswordHelper helper;

    private CountDownTimer timer;

    String[] correctWords = {"BATCH", "BRACT", "CHART", "BAT", "CHAT", "CART", "ACT"};
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
        View view = inflater.inflate(R.layout.fragment_medium_level_screen, container, false);

        helper = new CrosswordHelper(getContext());
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.bingo);
        final MediaPlayer complete = MediaPlayer.create(getContext(), R.raw.complete);
        final MediaPlayer wrong = MediaPlayer.create(getContext(), R.raw.wrong_answer);
        final MediaPlayer contain = MediaPlayer.create(getContext(), R.raw.error_sound);
        TextView letterR = view.findViewById(R.id.m_R);
        TextView letterC = view.findViewById(R.id.m_C);
        TextView letterH = view.findViewById(R.id.m_H);
        TextView letterA = view.findViewById(R.id.m_A);
        TextView letterT = view.findViewById(R.id.m_T);
        TextView letterB = view.findViewById(R.id.m_B);
        TextView guessInput = view.findViewById(R.id.guessInput_m);
        Button submitButton = view.findViewById(R.id.submitBtn_m);
        TextView e_score = view.findViewById(R.id.m_score);
        TextView timerTextView = view.findViewById(R.id.m_timer);
        TextView delete = view.findViewById(R.id.delete);

        timer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                // Update the timer display with the remaining time
                timerTextView.setText("Time:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                TimeOutFragment dialogFragment = new TimeOutFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                dialogFragment.show(fragmentManager, "TimeOutFragment");
            }
        };
        timer.start();

        View.OnClickListener letterClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letter = ((TextView) v).getText().toString();
                populateGuessInput(guessInput, letter);
            }
        };

        letterR.setOnClickListener(letterClickListener);
        letterC.setOnClickListener(letterClickListener);
        letterA.setOnClickListener(letterClickListener);
        letterB.setOnClickListener(letterClickListener);
        letterT.setOnClickListener(letterClickListener);
        letterH.setOnClickListener(letterClickListener);

        TextView one1 = view.findViewById(R.id.m_1_1);
        TextView one2 = view.findViewById(R.id.m_1_2);
        TextView one3 = view.findViewById(R.id.m_1_3);
        TextView two1 = view.findViewById(R.id.m_2_1);
        TextView two2 = view.findViewById(R.id.m_2_2);
        TextView two4 = view.findViewById(R.id.m_2_4);
        TextView three1 = view.findViewById(R.id.m_3_1);
        TextView three2 = view.findViewById(R.id.m_3_2);
        TextView three3 = view.findViewById(R.id.m_3_3);
        TextView three4 = view.findViewById(R.id.m_3_4);
        TextView three5 = view.findViewById(R.id.m_3_5);
        TextView three6 = view.findViewById(R.id.m_3_6);
        TextView four1 = view.findViewById(R.id.m_4_1);
        TextView four2 = view.findViewById(R.id.m_4_2);
        TextView four3 = view.findViewById(R.id.m_4_3);
        TextView four4 = view.findViewById(R.id.m_4_4);
        TextView five1 = view.findViewById(R.id.m_5_1);
        TextView five2 = view.findViewById(R.id.m_5_2);
        TextView five4 = view.findViewById(R.id.m_5_4);
        TextView five5 = view.findViewById(R.id.m_5_5);
        TextView six1 = view.findViewById(R.id.m_6_1);
        TextView six2 = view.findViewById(R.id.m_6_2);
        TextView six3 = view.findViewById(R.id.m_6_3);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (selectedLetters.length() >0){
                    selectedLetters.deleteCharAt(selectedLetters.length()-1);
                    guessInput.setText(selectedLetters.toString());
                }
            }
        });
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
                        if (correctAnswerSubmitted != correctWords.length){
                            mp.start();
                        }
                        int remainingTime = Integer.parseInt(timerTextView.getText().toString().substring(6));
                        score += (remainingTime * 10 + guess.length() * 10);
                        e_score.setText("Score : " + score);
                        switch (correctWords[correctIndex]){
                            case "BAT":
                                helper.updateTextView(one1, "B");
                                helper.updateTextView(one2, "A");
                                helper.updateTextView(one3, "T");
                                break;
                            case "CHAT":
                                helper.updateTextView(two1, "C");
                                helper.updateTextView(two2, "H");
                                helper.updateTextView(four2, "A");
                                helper.updateTextView(two4, "T");
                                break;
                            case "BRACT":
                                helper.updateTextView(three1, "B");
                                helper.updateTextView(three2, "R");
                                helper.updateTextView(three3, "A");
                                helper.updateTextView(three4, "C");
                                helper.updateTextView(one3, "T");
                                break;
                            case "BATCH":
                                helper.updateTextView(three1, "B");
                                helper.updateTextView(three5, "A");
                                helper.updateTextView(four4, "T");
                                helper.updateTextView(five1, "C");
                                helper.updateTextView(three6, "B");
                                break;
                            case "CART":
                                helper.updateTextView(four1, "C");
                                helper.updateTextView(four2, "A");
                                helper.updateTextView(four3, "R");
                                helper.updateTextView(four4, "T");
                                break;
                            case "CHART":
                                helper.updateTextView(five1, "C");
                                helper.updateTextView(five2, "H");
                                helper.updateTextView(six1, "A");
                                helper.updateTextView(five4, "R");
                                helper.updateTextView(five5, "T");
                                break;
                            case "ACT":
                                helper.updateTextView(six1, "A");
                                helper.updateTextView(six2, "C");
                                helper.updateTextView(six3, "T");
                                break;
                        }
                        if (correctAnswerSubmitted == correctWords.length){
                            getScoreFragment();
                        }
                    } else {
                        wrong.start();
                    }
                } else {
                    contain.start();
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

    private void getScoreFragment(){
        timer.cancel();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        helper.showScoreFragment(fragmentManager, score);
    }
}