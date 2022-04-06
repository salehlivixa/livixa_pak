package com.github.mrengineer13.snackbar;

import android.content.res.ColorStateList;
import android.os.Parcel;
import android.os.Parcelable;

import com.github.mrengineer13.snackbar.SnackBar.Style;

class My_Snack implements Parcelable {

    final String mMessage;

    final String mActionMessage;

    final int mActionIcon;

    final Parcelable mToken;

    

    final ColorStateList mBtnTextColor;

    final Style mStyle;

    My_Snack(String message, String actionMessage, int actionIcon,
                 Parcelable token, short duration, ColorStateList textColor) {
        mMessage = message;
        mActionMessage = actionMessage;
        mActionIcon = actionIcon;
        mToken = token;
       
        mBtnTextColor = textColor;
        mStyle = Style.DEFAULT;
    }

    My_Snack(String message, String actionMessage, int actionIcon,
                 Parcelable token, short duration, Style style) {
        mMessage = message;
        mActionMessage = actionMessage;
        mActionIcon = actionIcon;
        mToken = token;
       
        mStyle = style;
        mBtnTextColor = null;
    }

    // reads data from parcel
    My_Snack(Parcel p) {
        mMessage = p.readString();
        mActionMessage = p.readString();
        mActionIcon = p.readInt();
        mToken = p.readParcelable(p.getClass().getClassLoader());
        
        mBtnTextColor = p.readParcelable(p.getClass().getClassLoader());
        mStyle = Style.valueOf(p.readString());
    }

    // writes data to parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mMessage);
        out.writeString(mActionMessage);
        out.writeInt(mActionIcon);
        out.writeParcelable(mToken, 0);
       
        out.writeParcelable(mBtnTextColor, 0);
        out.writeString(mStyle.name());
    }

    public int describeContents() {
        return 0;
    }

    // creates snack array
    public static final Creator<My_Snack> CREATOR = new Creator<My_Snack>() {
        public My_Snack createFromParcel(Parcel in) {
            return new My_Snack(in);
        }

        public My_Snack[] newArray(int size) {
            return new My_Snack[size];
        }
    };
}
