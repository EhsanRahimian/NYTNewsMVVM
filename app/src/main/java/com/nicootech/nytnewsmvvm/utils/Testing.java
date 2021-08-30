package com.nicootech.nytnewsmvvm.utils;

import android.util.Log;

import com.nicootech.nytnewsmvvm.models.Docs;

import java.util.List;

public class Testing {
    public static void print(List<Docs>list,String tag){
        for(Docs doc : list){
            Log.d(tag, "onChanged: "+doc.getHeadline().getMain());
        }
    }
}
