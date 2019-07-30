# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/mr.chen/Documents/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontoptimize
-dontpreverify
-keepattributes Exceptions,InnerClasses,Signature,LineNumberTable,*Annotation*,SourceFile,Deprecated,EnclosingMethod

#========================jpush================================
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#========================jmessage================================
-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

 -keepclassmembers class ** {
     public void onEvent*(**);
 }

#========================gson================================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

#========================protobuf================================
-keep class com.google.protobuf.** {*;}


#========================JMRTC================================
-dontwarn cn.jiguang.jmrtc.**
-keep class cn.jiguang.jmrtc.api.** {*;}

#========================Agora================================
-dontwarn io.agora.rtc.**
-keep class io.agora.rtc.** {*;}