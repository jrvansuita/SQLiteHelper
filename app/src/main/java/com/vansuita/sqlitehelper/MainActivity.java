package com.vansuita.sqlitehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vansuita.sqliteparser.SqlParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstExample((TextView) findViewById(R.id.first_output));
    }

    private void firstExample(TextView output) {
        output.setText(SqlParser.query()
                .col("A")
                .col("B")
                .col("C", "NICK")
                .col("ALIAS", "D", "NICK")
                .cols("E", "F", "G")
                .sum("H").count()
                .max("I")
                .table("YOUR_TABLE", "T")
                .build()
        );
    }

}
