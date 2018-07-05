package io.github.souravpaul8.bitsindri;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class NoticesActivity extends AppCompatActivity {

    private RecyclerView mNoticesRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<
            Notice,NoticesActivity.NoticeViewHolder> mNoticesRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        setTitle("Notices");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Notices");
        mDatabase.keepSynced(true);

        mNoticesRV = findViewById(R.id.noticesRecycleView);

        DatabaseReference noticesRef = FirebaseDatabase.getInstance().getReference().child("Notices");
        Query noticesQuery =noticesRef.orderByKey();

        mNoticesRV.hasFixedSize();
        mNoticesRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions noticesOptions = new FirebaseRecyclerOptions.Builder<Notice>().setQuery(noticesQuery,Notice.class).build();

        mNoticesRVAdapter = new FirebaseRecyclerAdapter<Notice, NoticesActivity.NoticeViewHolder>(noticesOptions) {
            @Override
            protected void onBindViewHolder(@NonNull NoticesActivity.NoticeViewHolder holder, final int position, @NonNull final Notice model) {
                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(getBaseContext(),model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String title =model.getTitle();
                        final String attachment = model.getAttachNotice();
                        final String fullDesc = model.getFullDesc();
                        final String image = model.getImage();
                        Intent intent = new Intent(getApplicationContext(), NoticeInDetailActivity.class);
                        intent.putExtra("fullDesc",fullDesc);
                        intent.putExtra("attachment",attachment);
                        intent.putExtra("image",image);
                        intent.putExtra("title",title);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public NoticesActivity.NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.notices_row,parent,false);

                return new NoticesActivity.NoticeViewHolder(view);
            }
        };

        mNoticesRV.setAdapter(mNoticesRVAdapter);

    }

    public void onStart() {

        super.onStart();
        mNoticesRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mNoticesRVAdapter.stopListening();
    }


    public static class NoticeViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NoticeViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.get().load(image).fit().into(post_image);
        }
    }
}
