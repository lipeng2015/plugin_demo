package com.example.plug_tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PlugUtils {

    public static final String TAG="PlugUtils";

    public static void msg(String tag, String msg){
        Log.i(tag, msg);
    }

    /**
     * 跳转到插件apk的页面中
     * @param activity
     * @param apkName 插件apk自定义的名字
     * @param className 插件AndroidManifest.xml定义的meta-data标签中的name
     */
    public static void goActivity(Activity activity, String apkName, String className){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(PlugConfig.ACTIVITY_URL));
        intent.setPackage(activity.getPackageName());
        intent.putExtra(PlugConfig.APK_NAME, apkName);
        intent.putExtra(PlugConfig.CLASS_TAG, className);
        msg(TAG, "goActivity=="+apkName+"=="+className);
        activity.startActivity(intent);
    }

    /**
     * 把插件apk写到本地目录中
     * @param context
     * @param name
     * @return
     */
    public static File getNativeApkPath(Context context, String name){
        File file=null;
        try {
            InputStream open = context.getAssets().open(name);
            //apk路径
            File my_cache = context.getDir("my_cache", Context.MODE_PRIVATE);
            file = new File(my_cache.getAbsolutePath(),name);
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            int len=-1;
            byte[] arr=new byte[1024];
            while ( (len=open.read(arr))!=-1 )
            {
                fileOutputStream.write(arr,0,len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            open.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Resources readApkRes(Context context, String apkPath){
        Resources resources1=null;
        try {
            AssetManager assetManager=AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, apkPath);
            Resources resources = context.getResources();
            resources1 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,""+e.getMessage());
        }
        return resources1;
    }

    public static String getOutDexpaPath(Context context, String apkPath){
        return context.getDir(apkPath ,Context.MODE_PRIVATE).getAbsolutePath();
    }

    public static PackageInfo getPackageInfo(Context context, String apkFilepath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageArchiveInfo(apkFilepath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES | PackageManager.GET_META_DATA);
            Log.i(TAG,"package obje=="+pkgInfo+"==path==="+apkFilepath);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,""+e.getMessage());
        }
        return pkgInfo;
    }

    public static PackageInfo getPackageInfo2(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES | PackageManager.GET_META_DATA);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,""+e.getMessage());
        }
        return pkgInfo;
    }

    public static Drawable getAppIcon(Context context, String apkFilepath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = getPackageInfo(context, apkFilepath);
        if (pkgInfo == null) {
            return null;
        }

        ApplicationInfo appInfo = pkgInfo.applicationInfo;
        if (Build.VERSION.SDK_INT >= 8) {
            appInfo.sourceDir = apkFilepath;
            appInfo.publicSourceDir = apkFilepath;
        }

        return pm.getApplicationIcon(appInfo);
    }

    /**
     * 初始化dexClassLoader
     */
    public static DexClassLoader readDexFile(Context context, String apkPath, String outDexPath){
        DexClassLoader dexClassLoader=null;
        try {
            dexClassLoader=new DexClassLoader(apkPath, getOutDexpaPath(context, outDexPath), context.getApplicationInfo().nativeLibraryDir, context.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,""+e.getMessage());
        }
        return dexClassLoader;
    }

    /**
     * 获取插件中相关信息
     * @param context
     * @param apkFilepath
     * @return
     */
    public static CharSequence getAppLabel(Context context, String apkFilepath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = getPackageInfo(context, apkFilepath);
        if (pkgInfo == null) {
            return null;
        }

        ApplicationInfo appInfo = pkgInfo.applicationInfo;
        if (Build.VERSION.SDK_INT >= 8) {
            appInfo.sourceDir = apkFilepath;
            appInfo.publicSourceDir = apkFilepath;
        }

        return pm.getApplicationLabel(appInfo);
    }

    //通过类加载器，实例化类
    public static Object getObj(String name, ClassLoader classLoader) {
        String imp = "Imp";
        Object object2 = null;
        try {
            String s = name + imp;
            Class<?> aClass = classLoader.loadClass(s);
            object2 = aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "" + e.getMessage());
        }
        return object2;
    }
}
