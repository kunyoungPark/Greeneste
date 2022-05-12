package com.chloe.greeneste;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.Collection;

public class GuidelineAdapter extends RecyclerView.Adapter<GuidelineAdapter.ViewHolder> implements Filterable {
    public Context mContext;
    private ArrayList<GuideDTO> guideList;
    private ArrayList<GuideDTO> filtered;
    public OnItemClickListener mOnItemClickListener = null;
    private ImageView img_thumb;

    public interface OnItemClickListener {
        void onItemClick(View view, GuideDTO guide);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GuidelineAdapter(ArrayList<GuideDTO> guideList, Context mContext  ) {
        this.guideList = this.filtered = guideList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.guideline_adapter, parent, false) ;
        GuidelineAdapter.ViewHolder vh = new GuidelineAdapter.ViewHolder(view) ;
        return vh ;

    }

    @Override

    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GuideDTO guideDTO = guideList.get(position);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://greeneste-92f66.appspot.com");
        StorageReference storageRef = storage.getReference();
        storageRef.child("guideline/"+ guideDTO.key +"/img.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
                Toast.makeText(mContext, storageRef.child("guideline/"+guideDTO.key+"/img.png").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.txt_content.setText(guideDTO.getThumbContent());
        holder.txt_title.setText(guideDTO.getTitle());
        holder.layout_guideline_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, guideDTO);
            }
        });
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<GuideDTO> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(guideList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (GuideDTO item : guideList) {
                    //TODO filter 대상 setting
                    if (item.getTitle().toLowerCase().contains(filterPattern) || item.getContent().toLowerCase().contains(filterPattern)) {
                        Log.e("SEARCH", item.toString());
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            Log.w("results", results.values.toString());
            return results;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                filtered.clear();
                filtered.addAll((ArrayList<GuideDTO>) results.values);
                notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return guideList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
          LinearLayout layout_guideline_panel;
          TextView txt_title;
          TextView txt_content;

        public ViewHolder(View convertView) {
            super(convertView);
            layout_guideline_panel = (LinearLayout) convertView.findViewById(R.id.layout_guideline_panel);
           img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            txt_title = (TextView) convertView.findViewById(R.id.txt_title);
        }

    }

}