package com.practice.myhome.fakeInstar.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.myhome.fakeInstar.R;
import com.practice.myhome.fakeInstar.dto.Article;
import com.practice.myhome.fakeInstar.util.Util;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterArticle extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private final int TYPE_FOOTER = 2;

    private List<Article> data;
    private View.OnClickListener onClickLoadMore;
    private View.OnClickListener onClickItem;

    public RecyclerViewAdapterArticle() {
        this.data = new ArrayList<>();
    }

    public Article getArticle(int index) {
        return data.get(index);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position == data.size() + 1)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article_header, parent, false);

            return new HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article_footer, parent, false);

            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article, parent, false);

            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;

        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            int articleIndex = position - 1;

            Article article = data.get(articleIndex);
            itemViewHolder.textViewId.setText(article.id + "번");
            itemViewHolder.textViewId.setTag(articleIndex);

            itemViewHolder.textViewTitle.setText(article.title);
            itemViewHolder.textViewTitle.setTag(articleIndex);

            itemViewHolder.imageViewThumb.setTag(articleIndex);
            Util.loadImageOn(article.getThumbImgUrl(), itemViewHolder.imageViewThumb);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }

    public void addArticles(List<Article> articles) {
        for (Article article : articles) {
            data.add(article);
        }

        int headerCount = 1;

        notifyItemRangeInserted(headerCount + getDataSize(), getLoadCount());
    }

    public void setOnClickLoadMore(View.OnClickListener onClickLoadMore) {
        this.onClickLoadMore = onClickLoadMore;
    }

    public void setOnClickItem(View.OnClickListener onClickItem) {
        this.onClickItem = onClickItem;
    }

    public int getLoadCount() {
        return 5;
    }

    public int getDataSize() {
        return data.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewTitle;
        public ImageView imageViewThumb;

        public ItemViewHolder(@NonNull View view) {
            super(view);

            textViewId = view.findViewById(R.id.item_article__textViewId);

            textViewId.setOnClickListener(onClickItem);

            textViewTitle = view.findViewById(R.id.item_article__textViewTitle);
            textViewTitle.setOnClickListener(onClickItem);

            imageViewThumb = view.findViewById(R.id.item_article__imageViewThumb);
            imageViewThumb.setOnClickListener(onClickItem);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View view) {
            super(view);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public Button buttonLoadMore;

        public FooterViewHolder(@NonNull View view) {
            super(view);
            buttonLoadMore = view.findViewById(R.id.item_article_footer__buttonLoadMore);
            buttonLoadMore.setOnClickListener(onClickLoadMore);
        }
    }
}