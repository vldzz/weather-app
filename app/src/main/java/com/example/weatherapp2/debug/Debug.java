package com.example.weatherapp2.debug;

import android.app.AlertDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class Debug {

    public static void error(AppCompatActivity context, String msg, String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(msg);
        dialog.setTitle(title);
        dialog.create().show();
    }

    public static void error(AppCompatActivity context, String msg) {
        error(context, msg, "Error");
    }

    public static void info(AppCompatActivity context, String msg) {
        error(context, msg, "Info");
    }

    public static void debug(AppCompatActivity context, String msg) {
        error(context, msg, "Debug");
    }

}
