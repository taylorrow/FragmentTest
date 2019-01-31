package com.midigame.fragmenttest.fragment_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.midigame.fragmenttest.R;
import com.squareup.picasso.Picasso;

public class FullScreenItemFragmentTwo extends AppCompatActivity {

    private String title, url, number;
    private ImageView imageView;
    private TextView text, textNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_two);

        imageView = (ImageView) findViewById(R.id.imageView_frag_2);
        text = (TextView) findViewById(R.id.textView_frag_2);
        textNumber = (TextView) findViewById(R.id.textView_number_frag_2);

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        number = getIntent().getStringExtra("number");

        Picasso.with(this).load(url).placeholder(R.drawable.ic_launcher_background).into(imageView);
        text.setText(title);
        textNumber.setText(number);
    }
}
