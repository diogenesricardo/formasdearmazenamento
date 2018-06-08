package com.example.diogenes.formasdestore.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class Preferences {

    public static final String ID_BASE = "listapessoas";

    public static void setString(Context context, String chave, String valor) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    public static void setInterger(Context context, String chave, int valor) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(chave, valor);
        editor.commit();
    }

    public static void setBoolean(Context context, String chave, boolean on) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(chave, on);
        editor.commit();
    }

    public static  boolean getBoolean(Context context, String chave) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE,0);
        boolean b = preferences.getBoolean(chave,true);
        return b;
    }

    public static String getInterger(Context context, String chave) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE,0);
        String b = String.valueOf(preferences.getInt(chave,0));
        return b;
    }

    public static String getString(Context context, String chave) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE,0);
        String b = preferences.getString(chave,"");
        return b;
    }

    public static Map<String, ?> getAllKeys(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE,0);
        Map<String, ?> listkeys = preferences.getAll();
        return listkeys;
    }

    public static void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ID_BASE,0);
        preferences.edit().clear().commit();
    }

    public static boolean isKeyExists(Context context, String key) {        ;
        return context.getSharedPreferences(ID_BASE,0).contains(key);
    }



}
