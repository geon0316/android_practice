package com.example.helloandroid;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.util.List;

public class utilFunc {

    // 전화번호 유효성 검증
    public boolean validUserInfo(String phoneNumber)
    {
        boolean flag = false;

        // 전화번호 입력 정규표현식
        String regex = "^010\\d{8}$";
        if (phoneNumber.matches(regex))
        {
            flag = true;
        }
        return flag;
    }

    // 문자열 공백 검사 함수  / 문자열이 비어있다면 true 반환
    public static boolean isStrBlank(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    // 저장 버튼 클릭 했을때 입력 정보에 대한 처리
    public boolean inputValueEmpty(String name, String phone, String email, String company, String level)
    {
        // 1. 입력값을 모두 채우지 않았나 검사
        // 2. 입력한 정보가 유효한지 검사

        // 문자열 공백 검사

        // 이름
        boolean a = isStrBlank(name);
        // 전화번호
        boolean b = isStrBlank(phone);
        // 이메일
        boolean c = isStrBlank(email);
        // 회사
        boolean d = isStrBlank(company);
        // 직급
        boolean e = isStrBlank(level);

        if (a || b || c || d || e)
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    // ===== 휴대폰 정보 관련 함수 ===== //

    // 안드로이드 버전 반환
    public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release + ")";
    }

    // 와이파이 온오프 여부 반환
    public boolean isWifiEnabled(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    // LTE 사용 여부 반환
    public boolean isMobileDataEnabled(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    // 휴대폰 내부 저장소 용량
    public double[] getStorageInfo() {
        File path = Environment.getExternalStorageDirectory(); // 외부 저장소 경로
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        double totalSpaceGB = blockSize * totalBlocks / (double)(1024 * 1024 * 1024);
        double freeSpaceGB = blockSize * availableBlocks / (double)(1024 * 1024 * 1024);

        // 소수점 두 번째 자리까지 반올림
        totalSpaceGB = Math.round(totalSpaceGB * 100.0) / 100.0;
        freeSpaceGB = Math.round(freeSpaceGB * 100.0) / 100.0;

        return new double[]{totalSpaceGB, freeSpaceGB}; // 총 공간과 사용 가능한 공간 반환
    }

    // 설치 앱 수 반환
    public int getInstalledAppCount(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);
        return apps.size(); // 설치된 앱의 개수 반환
    }

    //24시간 내에 실행된 앱의 개수 반환
    public int getRecentAppCount(Context context) {
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*3600*24, time);
        if (appList != null) {
            return appList.size(); // 사용된 앱의 개수 반환
        }
        return 0;
    }

}
