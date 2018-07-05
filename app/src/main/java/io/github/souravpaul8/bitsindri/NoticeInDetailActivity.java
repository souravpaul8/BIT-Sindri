package io.github.souravpaul8.bitsindri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NoticeInDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_in_detail);

        TextView noticeTitle = findViewById(R.id.detail_title);
        TextView fullDescription = findViewById(R.id.full_desc);
        ImageView imageNotice = findViewById(R.id.notice_image);
        Button dwnldBtn = findViewById(R.id.dwnld_btn);

        Bundle extras = getIntent().getExtras();
            String fullDesc = extras.getString("fullDesc");
            String attachNotice = extras.getString("attachNotice");
            String image = extras.getString("image");
            String title = extras.getString("title");


            noticeTitle.setText(title);
            fullDescription.setText(fullDesc);
            Picasso.get().load(image).fit().into(imageNotice);




    }
}
