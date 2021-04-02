package com.practice.myhome.fakeInstar.util;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.myhome.fakeInstar.R;
import com.practice.myhome.fakeInstar.ui.BaseActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Util {
    private static Application application;
    private static BaseActivity currentActivity;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;

    public static void init(Application application) {
        Util.application = application;

        sp = PreferenceManager.getDefaultSharedPreferences(application);
        spEditor = sp.edit();
    }

    public static void toast(int msg) {
        toast(msg + "");
    }

    public static void toast(String msg) {
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }

    public static void spPut(String key, int value) {
        spEditor.putInt(key, value);
    }

    public static void spPut(String key, boolean value) {
        spEditor.putBoolean(key, value);
    }

    public static void spPut(String key, String value) {
        spEditor.putString(key, value);
    }

    public static void spPut(String key, Object obj) {
        spPut(key, objToJsonString(obj));
    }

    public static void spCommit() {
        spEditor.commit();
    }

    public static int spGetInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static boolean spGetBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static String spGetString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static void log(String msg) {
        Log.d("AA1", msg);
    }

    public static String objToJsonString(Object obj) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static <T> T spGetObj(String key, Class<T> cls) {
        String jsonString = spGetString(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T) om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T spGetObj(String key, TypeReference<T> cls) {
        String jsonString = spGetString(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T) om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static float dipToPixels(float borderRadius) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderRadius, metrics);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView) {
        loadImageOn(imgUrl, imageView, 0);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView, int borderRadius) {
        GlideUrl url = new GlideUrl(imgUrl, new LazyHeaders.Builder()
                .addHeader("Usr-Agent", "your-user-agent")
                .build());

        if (borderRadius > 0) {
            GlideApp.with(application)
                    .load(url)
                    .transform(new CenterCrop(), new RoundedCorners((int) dipToPixels(borderRadius)))
                    .into(imageView);
        } else {
            GlideApp.with(application)
                    .load(url)
                    .into(imageView);
        }

    }

    public static void loadImageOnByFinalUrl(String imgUrl, ImageView imageView) {
        loadImageOnByFinalUrl(imgUrl, imageView, 0);
    }

    public static void loadImageOnByFinalUrl(String imgUrl, ImageView imageView, int borderRadius) {

        Observable<String> observable = Observable.create(subscriber -> {
            // 여기는 별도의 쓰레드에서 실행됨
            subscriber.onNext(getRedirectFinalUrl(imgUrl));
            subscriber.onComplete();
        });

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        // 여기는 UI의 쓰레드에서 실행됨
                        loadImageOn(s, imageView, borderRadius);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static String getRedirectFinalUrl(String url) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setInstanceFollowRedirects(false);
            con.connect();
            con.getInputStream();

            if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
                String redirectUrl = con.getHeaderField("Location");
                return getRedirectFinalUrl(redirectUrl);
            }
        } catch (IOException e) {

        }
        return url;
    }

    public static void setTimeout(Runnable r, int delay) {
        new android.os.Handler(Looper.getMainLooper()).postDelayed(r, delay);

    }

    public static void setCurrentActivity(BaseActivity baseActivity) {
        Util.currentActivity = baseActivity;
    }

    public static <T extends BaseActivity> T getCurrentActivity() {
        return (T) Util.currentActivity;
    }

    public static NavController getNavController(){
        return Navigation.findNavController(currentActivity, R.id.nav_host_fragment);
    }
}
