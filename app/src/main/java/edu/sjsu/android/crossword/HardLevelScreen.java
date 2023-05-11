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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HardLevelScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HardLevelScreen extends Fragment {

    private StringBuilder selectedLetters = new StringBuilder();
    Set<String> submittedAnswers = new HashSet<>();
    int correctAnswerSubmitted = 0;
    int score = 0;

    private CountDownTimer timer;

    String[] correctWords = {"UNREAL", "TUNER", "NATURE","LUNATE","ALERT","ULTRA","RENTAL","NEAT","NEUTRAL"};


    public HardLevelScreen() {
        // Required empty public constructor
    }
    public static HardLevelScreen newInstance(String param1, String param2) {
        HardLevelScreen fragment = new HardLevelScreen();
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
        View view = inflater.inflate(R.layout.fragment_hard_level_screen, container, false);
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.bingo);
        final MediaPlayer complete = MediaPlayer.create(getContext(), R.raw.complete);
        final MediaPlayer wrong = MediaPlayer.create(getContext(), R.raw.wrong_answer);
        final MediaPlayer contain = MediaPlayer.create(getContext(), R.raw.error_sound);

        TextView letterN = view.findViewById(R.id.h_N);
        TextView letterA = view.findViewById(R.id.h_A);
        TextView letterT = view.findViewById(R.id.h_T);
        TextView letterU = view.findViewById(R.id.h_U);
        TextView letterR = view.findViewById(R.id.h_R);
        TextView letterE = view.findViewById(R.id.h_E);
        TextView letterL = view.findViewById(R.id.h_L);
        TextView guessInput = view.findViewById(R.id.guessInput_h);
        Button submitButton = view.findViewById(R.id.submitBtn_h);
        TextView e_score = view.findViewById(R.id.h_score);
        TextView delete = view.findViewById(R.id.delete);
        TextView timerTextView = view.findViewById(R.id.h_timer);
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (selectedLetters.length() >0){
                    selectedLetters.deleteCharAt(selectedLetters.length()-1);
                    guessInput.setText(selectedLetters.toString());
                }
            }
        });

        letterR.setOnClickListener(letterClickListener);
        letterU.setOnClickListener(letterClickListener);
        letterL.setOnClickListener(letterClickListener);
        letterN.setOnClickListener(letterClickListener);
        letterA.setOnClickListener(letterClickListener);
        letterE.setOnClickListener(letterClickListener);
        letterT.setOnClickListener(letterClickListener);

        TextView one1 = view.findViewById(R.id.h_1_1);
        TextView one2 = view.findViewById(R.id.h_1_2);
        TextView one3 = view.findViewById(R.id.h_1_3);
        TextView one5 = view.findViewById(R.id.h_1_5);
        TextView one6 = view.findViewById(R.id.h_1_6);
        TextView two1 = view.findViewById(R.id.h_2_1);
        TextView two2 = view.findViewById(R.id.h_2_2);
        TextView two3 = view.findViewById(R.id.h_2_3);
        TextView two4 = view.findViewById(R.id.h_2_4);
        TextView two5 = view.findViewById(R.id.h_2_5);
        TextView three1 = view.findViewById(R.id.h_3_1);
        TextView three2 = view.findViewById(R.id.h_3_2);
        TextView three3 = view.findViewById(R.id.h_3_3);
        TextView three4 = view.findViewById(R.id.h_3_4);
        TextView three6 = view.findViewById(R.id.h_3_6);
        TextView four1 = view.findViewById(R.id.h_4_1);
        TextView four2 = view.findViewById(R.id.h_4_2);
        TextView four4 = view.findViewById(R.id.h_4_4);
        TextView four5 = view.findViewById(R.id.h_4_5);
        TextView five1 = view.findViewById(R.id.h_5_1);
        TextView five2 = view.findViewById(R.id.h_5_2);
        TextView five3 = view.findViewById(R.id.h_5_3);
        TextView five5 = view.findViewById(R.id.h_5_5);
        TextView six1 = view.findViewById(R.id.h_6_1);
        TextView six2 = view.findViewById(R.id.h_6_2);
        TextView six3 = view.findViewById(R.id.h_6_3);
        TextView six4 = view.findViewById(R.id.h_6_4);
        TextView seven1 = view.findViewById(R.id.h_7_1);
        TextView seven2 = view.findViewById(R.id.h_7_2);
        TextView seven3 = view.findViewById(R.id.h_7_3);
        TextView seven4 = view.findViewById(R.id.h_7_4);
        TextView seven5 = view.findViewById(R.id.h_7_5);
        TextView seven6 = view.findViewById(R.id.h_7_6);
        TextView eight1 = view.findViewById(R.id.h_8_1);
        TextView eight2 = view.findViewById(R.id.h_8_2);
        TextView eight3 = view.findViewById(R.id.h_8_3);
        TextView eight4 = view.findViewById(R.id.h_8_4);
        TextView nine1 = view.findViewById(R.id.h_9_1);
        TextView nine3 = view.findViewById(R.id.h_9_3);
        TextView nine5 = view.findViewById(R.id.h_9_5);
        TextView nine6 = view.findViewById(R.id.h_9_6);
        TextView nine7 = view.findViewById(R.id.h_9_7);
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
                        int remainingTime = Integer.parseInt(timerTextView.getText().toString().substring(6));
                        score += remainingTime * 10;
                        e_score.setText("Score : " + score);
                        switch (correctWords[correctIndex]){
                            case "ULTRA":
                                updateTextView(two1, "U");
                                updateTextView(two2, "L");
                                updateTextView(two3, "T");
                                updateTextView(two4, "R");
                                updateTextView(two5, "A");
                                break;
                            case "LUNATE":
                                updateTextView(one1, "L");
                                updateTextView(one2, "U");
                                updateTextView(one3, "N");
                                updateTextView(five1, "A");
                                updateTextView(one5, "T");
                                updateTextView(one6, "E");
                                break;
                            case "UNREAL":
                                updateTextView(three1, "U");
                                updateTextView(three2, "N");
                                updateTextView(three3, "R");
                                updateTextView(three4, "E");
                                updateTextView(six2, "A");
                                updateTextView(three6, "L");
                                break;
                            case "TUNER":
                                updateTextView(four1, "T");
                                updateTextView(four2, "U");
                                updateTextView(three2, "N");
                                updateTextView(four4, "E");
                                updateTextView(four5, "R");
                                break;
                            case "ALERT":
                                updateTextView(five1, "A");
                                updateTextView(five2, "L");
                                updateTextView(five3, "E");
                                updateTextView(two4, "R");
                                updateTextView(five5, "T");
                                break;
                            case "NATURE":
                                updateTextView(six1, "N");
                                updateTextView(six2, "A");
                                updateTextView(six3, "T");
                                updateTextView(six4, "U");
                                updateTextView(seven1, "R");
                                updateTextView(one6, "E");
                                break;
                            case "RENTAL":
                                updateTextView(seven1, "R");
                                updateTextView(seven2, "E");
                                updateTextView(seven3, "N");
                                updateTextView(seven4, "T");
                                updateTextView(seven5, "A");
                                updateTextView(seven6, "L");
                                break;
                            case "NEAT":
                                updateTextView(eight1, "N");
                                updateTextView(eight2, "E");
                                updateTextView(eight3, "A");
                                updateTextView(eight4, "T");
                                break;

                            case "NEUTRAL":
                                updateTextView(nine1, "N");
                                updateTextView(eight2, "E");
                                updateTextView(nine3, "U");
                                updateTextView(seven4, "T");
                                updateTextView(nine5, "R");
                                updateTextView(nine6, "A");
                                updateTextView(nine7, "L");
                                break;


                        }
                        if (correctAnswerSubmitted == correctWords.length){
                            complete.start();
                            timer.cancel();
                            ScoreFragment scoreFragment = new ScoreFragment();
                            // Set any data that you want to pass to the fragment using arguments
                            Bundle args = new Bundle();
                            args.putInt("score", score);
                            scoreFragment.setArguments(args);
                            // Show the fragment using the FragmentManager
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            scoreFragment.show(fragmentManager, "score");
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

    private void updateTextView(TextView textView, String text) {
        textView.setText(text);
        textView.setTextSize(15); // set text size to 20sp
        textView.setGravity(Gravity.CENTER); // center text horizontally and vertical
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setBackgroundResource(R.drawable.correct_border);
    }
}
