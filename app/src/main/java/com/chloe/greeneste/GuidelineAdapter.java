package com.chloe.greeneste;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class GuidelineAdapter extends RecyclerView.Adapter<GuidelineAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<GuideDTO> guideList;
    public OnItemClickListener mOnItemClickListener = null;
    private ImageView img_thumb;

    public interface OnItemClickListener {
        void onItemClick(View view, GuideDTO guide);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GuidelineAdapter(ArrayList<GuideDTO> guideList, Context mContext) {
        this.guideList = guideList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.guideline_adapter, parent, false);

        return new ViewHolder(convertView);
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GuideDTO guideDTO = guideList.get(position);
/*
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://greeneste-92f66.appspot.com");
        StorageReference storageRef = storage.getReference();
        storageRef.child("guideline/"+position+1 +"/img.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시
                Glide.with(mContext)
                        .load(uri)
                        .into(img_thumb);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Toast.makeText(mContext, storageRef.child("guideline/"+position+1 +"/img.png").toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.txt_content.setText(guideDTO.getContent());
        holder.txt_title.setText(guideDTO.getTitle());
        holder.layout_guideline_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, guideDTO);
            }
        });
    }



    @Override

    public int getItemCount() {
        return guideList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout_guideline_panel;
        private TextView txt_title;
        private TextView txt_content;

        public ViewHolder(View convertView) {
            super(convertView);
            layout_guideline_panel = (LinearLayout) convertView.findViewById(R.id.layout_guideline_panel);
           // img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            txt_title = (TextView) convertView.findViewById(R.id.txt_title);
        }

    }

}