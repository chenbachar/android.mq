package app.cbdev.motivationquotes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Quote extends Fragment implements View.OnClickListener {

    private ArrayList<String> quotes;
    private int current;
    private String quoteOfTheDay;

    public Quote() {
        quotes = new ArrayList<>();
        current = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quote, container, false);

        final Button btnNext = (Button) view.findViewById(R.id.buttonNext);
        final Button btnPrev = (Button) view.findViewById(R.id.buttonPrev);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        MainActivity activity = (MainActivity) getActivity();
        quotes = new ArrayList<>(activity.getQuotes());

        if (quotes.size() > 0) {
            int idx = getQuoteOfTheDay();
            this.quoteOfTheDay = activity.getQuotes().get(idx);
            quoteOfTheDay = quoteOfTheDay.replaceAll("#", System.getProperty("line.separator"));
        }

        //update cache if needed.
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (quotes.size() > 0) {
            TextView textView = (TextView) getView().findViewById(R.id.textViewQuote);
            textView.setText("Quote of the day:\n" + quoteOfTheDay);
            Collections.shuffle(quotes);
        }
    }

    /* Returns the index of "Quote of the day" */
    private int getQuoteOfTheDay() {
        return getIndexBasedOnDate(getDate());
    }

    private int getIndexBasedOnDate(String date) {
        int idx = 0;
        for (int i = 0; i < date.length(); i++)
            idx += date.charAt(i);
        return idx % quotes.size();
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
        return sdf.format(cal.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNext: {
                onClickBtnNext();
                break;
            }
            case R.id.buttonPrev: {
                onClickBtnPrev();
                break;
            }

        }
    }

    public void onClickBtnNext() {
        if (current < quotes.size()) {
            this.current++;
            setText(current, null);
        }
    }

    public void onClickBtnPrev() {
        if (current > 0) {
            this.current--;
            setText(current, null);
        }
    }

    public void setText(int index, String addition) {
        if (index < quotes.size()) {
            TextView textView = (TextView) getView().findViewById(R.id.textViewQuote);
            String q = quotes.get(index);

            q = q.replaceAll("#", System.getProperty("line.separator"));

            if (addition != null)
                textView.setText(addition + q);
            else
                textView.setText(q);

        }
    }
}
