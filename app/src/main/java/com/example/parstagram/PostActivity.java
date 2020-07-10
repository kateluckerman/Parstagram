package com.example.parstagram;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.parstagram.fragments.ProfileFragment;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostActivity extends AppCompatActivity {

    Post post;

    private TextView tvUsername;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvUserBelow;
    private TextView tvTime;
    private ImageView ivProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setIcon(R.drawable.nav_logo_whiteout);
        actionBar.setDisplayShowHomeEnabled(true);

        post = getIntent().getParcelableExtra(Post.class.getSimpleName());

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvUserBelow = findViewById(R.id.tvUserBelow);
        tvTime = findViewById(R.id.tvTime);
        ivProfilePic = findViewById(R.id.ivProfilePic);

        tvDescription.setText(post.getDescription());
        tvUsername.setText(post.getUser().getUsername());
        tvUserBelow.setText(post.getUser().getUsername());
        tvTime.setText(post.getRelativeTimeAgo());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }
        ParseFile profileImage = post.getUser().getParseFile(ProfileFragment.KEY_PROFILE_IMAGE);
        if (profileImage != null) {
            Glide.with(this).load(profileImage.getUrl()).into(ivProfilePic);
        }

    }
}