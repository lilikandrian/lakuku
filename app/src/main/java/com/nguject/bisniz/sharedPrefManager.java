package com.nguject.bisniz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dossy on 12/25/2017.
 */

public class sharedPrefManager {

    public static final String SP_LAKUKU_APP = "spLAKUKUApp";

    public static final String SP_NAMA = "spNama";
    public static final String SP_PASSWORD = "spPASSWORD";

    public static final String SP_HAS_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public sharedPrefManager(Context context){

        sp = context.getSharedPreferences(SP_LAKUKU_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
        
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPPASSWORD(){
        return sp.getString(SP_PASSWORD, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_HAS_LOGIN, false);
    }

}