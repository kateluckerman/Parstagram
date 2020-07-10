package com.example.parstagram;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram.fragments.ProfileFragment;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvUserBelow;
        private TextView tvTime;
        private ImageView ivProfilePic;
        LinearLayout llUserInfo;

        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

        public final String KEY_PROFILE_IMAGE = "profileImage";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUserBelow = itemView.findViewById(R.id.tvUserBelow);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            llUserInfo = itemView.findViewById(R.id.llUserInfo);
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvUserBelow.setText(post.getUser().getUsername());
            tvTime.setText(post.getRelativeTimeAgo());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            ParseFile profileImage = post.getUser().getParseFile(KEY_PROFILE_IMAGE);
            if (profileImage != null) {
                Glide.with(context).load(profileImage.getUrl()).into(ivProfilePic);
            }

            final ParseUser user = post.getUser();

            llUserInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment userProfile = new ProfileFragment(user);
                    fragmentManager.beginTransaction().replace(R.id.flContainer, userProfile).commit();
                }
            });
            }
        }

    }
