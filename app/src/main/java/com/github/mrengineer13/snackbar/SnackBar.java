/*
 * Copyright (c) 2014 MrEngineer13
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mrengineer13.snackbar;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livixa.client.R;

public class SnackBar {

    public static final short LONG_SNACK = 5000;

    public static final short MED_SNACK = 3500;

    public static final short SHORT_SNACK = 2000;

    public static final short PERMANENT_SNACK = 0;

    private SnackContainer mSnackContainer;

    private View mParentView;

    private TextView mSnackMsg;

    private TextView mSnackBtn;

    private OnMessageClickListener mClickListener;

    private OnVisibilityChangeListener mVisibilityChangeListener;

    private Context mContext;

    public interface OnMessageClickListener {

        void onMessageClick(Parcelable token);
    }

    public interface OnVisibilityChangeListener {

        void onShow(int stackSize);

        void onHide(int stackSize);
    }

    public SnackBar(Activity activity) {
        mContext = activity.getApplicationContext();
        ViewGroup container = (ViewGroup) activity.findViewById(android.R.id.content);
        View v = activity.getLayoutInflater().inflate(R.layout.sb__snack, container, false);
        init(container, v);
    }
    
    public SnackBar(Service activity) {
    	
    	try
    	{
        mContext = activity.getApplicationContext();
        DialogueMessage dialogueMessage=new DialogueMessage(mContext);
        
        
        dialogueMessage.show();
        
    	}catch(Exception ex)
    	{
    		
    	}
        //View v = dialogueMessage.getContainer();
        //init(container, v);
        
    }

    public SnackBar(Context context, View v) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sb__snack_container, ((ViewGroup) v));
        View snackLayout  = inflater.inflate(R.layout.sb__snack, ((ViewGroup) v), false);
        init((ViewGroup) v, snackLayout);
    }

    private void init(ViewGroup container, View v) {
        mSnackContainer = (SnackContainer) container.findViewById(R.id.snackContainer);
        if (mSnackContainer == null) {
            mSnackContainer = new SnackContainer(container);
        }

        mParentView = v;
        mSnackMsg = (TextView) v.findViewById(R.id.snackMessage);
        mSnackBtn = (TextView) v.findViewById(R.id.snackButton);
        mSnackBtn.setOnClickListener(mButtonListener);
    }

    public static class Builder{

        private SnackBar mSnackBar;

        private Context mContext;
        private String mMessage;
        private String mActionMessage;
        private int mActionIcon = 0;
        private Style mStyle = Style.DEFAULT;
        private Parcelable mToken;
        private short mDuration =  MED_SNACK;
        private ColorStateList mTextColor;


        public Builder(Activity activity){
            mContext = activity.getApplicationContext();
            mSnackBar = new SnackBar(activity);
        }
        
        public Builder(Service service){
            mContext = service.getApplicationContext();
            mSnackBar = new SnackBar(service);
        }

        public Builder(Context context, View v){
            mContext = context;
            mSnackBar = new SnackBar(context, v);
        }

        public Builder withMessage(String message){
            mMessage = message;
            return this;
        }

        public Builder withMessageId(int messageId){
            mMessage = mContext.getString(messageId);
            return this;
        }

        public Builder withActionMessage(String actionMessage){
            mActionMessage = actionMessage;
            return this;
        }

        public Builder withActionMessageId(int actionMessageResId){
            if (actionMessageResId > 0) {
                mActionMessage = mContext.getString(actionMessageResId);
            }

            return this;
        }

        public Builder withActionIconId(int id){
            mActionIcon = id;
            return this;
        }

        public Builder withStyle(Style style){
            mStyle = style;
            return this;
        }

        public Builder withToken(Parcelable token){
            mToken = token;
            return this;
        }

        public Builder withDuration(Short duration){
            mDuration = duration;
            return this;
        }

        public Builder withTextColorId(int colorId){
            ColorStateList color = mContext.getResources().getColorStateList(colorId);
            mTextColor = color;
            return this;
        }

        public Builder withOnClickListener(OnMessageClickListener onClickListener){
            mSnackBar.setOnClickListener(onClickListener);
            return this;
        }

        public Builder withVisibilityChangeListener(OnVisibilityChangeListener visibilityChangeListener){
            mSnackBar.setOnVisibilityChangeListener(visibilityChangeListener);
            return this;
        }

        public SnackBar show(){
            Snack message;
            if (mTextColor == null){
                message = new Snack(mMessage, (mActionMessage != null ? mActionMessage.toUpperCase() : null), mActionIcon, mToken, mDuration, mStyle);
            } else {
                message = new Snack(mMessage, (mActionMessage != null ? mActionMessage.toUpperCase() : null), mActionIcon, mToken, mDuration, mTextColor);
            }

            mSnackBar.showMessage(message);

            return mSnackBar;
        }
        
        
        public SnackBar show__(){
            Snack message;
            if (mTextColor == null){
                message = new Snack(mMessage, (mActionMessage != null ? mActionMessage.toUpperCase() : null), mActionIcon, mToken, mDuration, mStyle);
            } else {
                message = new Snack(mMessage, (mActionMessage != null ? mActionMessage.toUpperCase() : null), mActionIcon, mToken, mDuration, mTextColor);
            }

            mSnackBar.showMessage__(message);

            return mSnackBar;
        }
        
        
        
    }

    private void showMessage(Snack message){
        mSnackContainer.showSnack(message, mParentView, mVisibilityChangeListener);
    }
    
    
    private void showMessage__(Snack message){
        mSnackContainer.showSnack(message, mParentView, mVisibilityChangeListener,true);
    }

    public int getHeight() {
        mParentView.measure(View.MeasureSpec.makeMeasureSpec(mParentView.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(mParentView.getHeight(), View.MeasureSpec.AT_MOST));
        return mParentView.getMeasuredHeight();
    }

    public View getContainerView() {
        return mParentView;
    }

    private final View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mClickListener != null && mSnackContainer.isShowing()) {
                mClickListener.onMessageClick(mSnackContainer.peek().mToken);
            }
            mSnackContainer.hide();
        }
    };

    public SnackBar setOnClickListener(OnMessageClickListener listener) {
        mClickListener = listener;
        return this;
    }

    public SnackBar setOnVisibilityChangeListener(OnVisibilityChangeListener listener) {
        mVisibilityChangeListener = listener;
        return this;
    }

    public void clear(boolean animate) {
        mSnackContainer.clearSnacks(animate);
    }

    public void clear() {
        clear(true);
    }

    /**
     * All snacks will be restored using the view from this Snackbar
     */
    public void onRestoreInstanceState(Bundle state) {
        mSnackContainer.restoreState(state, mParentView);
    }

    public Bundle onSaveInstanceState() {
        return mSnackContainer.saveState();
    }

    public enum Style {
        DEFAULT,
        ALERT,
        CONFIRM,
        INFO
    }
}
