package com.example.guest.wednesday;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 12/2/15.
 */
public class FormAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mMadlibHints;

    public FormAdapter(Context context, ArrayList<String> madlibHints) {
        mContext = context;
        mMadlibHints = madlibHints;
    }

    @Override
    public int getCount() {
        return mMadlibHints.size();
    }

    @Override
    public Object getItem(int position) {
        return mMadlibHints.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<String> getItems() {
        return new ArrayList<String>(mMadlibHints);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            //list item empty
            convertView = LayoutInflater.from(mContext).inflate(R.layout.form_item, null);
            holder = new ViewHolder();
            holder.editText = (EditText) convertView.findViewById(R.id.editText);
            holder.watcher = new EditTextWatcher();
            holder.editText.addTextChangedListener(holder.watcher);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.watcher.setTarget(position);
        String hint = mMadlibHints.get(position);
        holder.editText.setHint(hint);

        return convertView;
    }

    private static class ViewHolder {
        EditText editText;
        EditTextWatcher watcher;
    }

    private class EditTextWatcher implements TextWatcher {

        private int target;

        public void setTarget(int target) {
            this.target = target;
        }

        @Override
        public void afterTextChanged(Editable s) {
            mMadlibHints.set(target, s.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

    }

}
