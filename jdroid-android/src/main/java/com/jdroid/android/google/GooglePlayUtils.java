package com.jdroid.android.google;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import com.jdroid.android.R;
import com.jdroid.android.application.AbstractApplication;
import com.jdroid.android.dialog.AlertDialogFragment;
import com.jdroid.android.intent.IntentUtils;
import com.jdroid.android.utils.AppUtils;

public class GooglePlayUtils {
	
	private static final String GOOGLE_PLAY_DETAILS_LINK = "http://play.google.com/store/apps/details?id=";
	
	public static class UpdateAppDialogFragment extends AlertDialogFragment {
		
		@Override
		protected void onPositiveClick() {
			FragmentActivity fragmentActivity = (FragmentActivity)AbstractApplication.get().getCurrentActivity();
			launchAppDetails(fragmentActivity, AppUtils.getApplicationId());
			fragmentActivity.finish();
		}
	}
	
	public static class DownloadAppDialogFragment extends AlertDialogFragment {
		
		private static final String PACKAGE_NAME = "PACKAGE_NAME";
		
		@Override
		protected void onPositiveClick() {
			FragmentActivity fragmentActivity = (FragmentActivity)AbstractApplication.get().getCurrentActivity();
			launchAppDetails(fragmentActivity, getArguments().getString(PACKAGE_NAME));
		}
		
		public void setPackageName(String packageName) {
			addParameter(PACKAGE_NAME, packageName);
		}
	}
	
	public static void showUpdateDialog() {
		FragmentActivity fragmentActivity = (FragmentActivity)AbstractApplication.get().getCurrentActivity();
		String title = fragmentActivity.getString(R.string.jdroid_updateAppTitle);
		String message = fragmentActivity.getString(R.string.jdroid_updateAppMessage);
		AlertDialogFragment.show(fragmentActivity, new UpdateAppDialogFragment(), title, message, null, null,
			fragmentActivity.getString(R.string.jdroid_ok), false);
	}
	
	public static void showDownloadDialog(int appNameResId, String packageName) {
		FragmentActivity fragmentActivity = (FragmentActivity)AbstractApplication.get().getCurrentActivity();
		String appName = fragmentActivity.getString(appNameResId);
		String title = fragmentActivity.getString(R.string.jdroid_installAppTitle, appName);
		String message = fragmentActivity.getString(R.string.jdroid_installAppMessage, appName);
		DownloadAppDialogFragment fragment = new DownloadAppDialogFragment();
		fragment.setPackageName(packageName);
		AlertDialogFragment.show(fragmentActivity, fragment, title, message, fragmentActivity.getString(R.string.jdroid_no),
			null, fragmentActivity.getString(R.string.jdroid_yes), true);
	}
	
	public static void launchAppDetails(Context context) {
		launchAppDetails(context, AppUtils.getApplicationId());
	}
	
	public static void launchAppDetails(Context context, String packageName) {
		Uri uri = Uri.parse("market://details?id=" + packageName);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		if (IntentUtils.isIntentAvailable(intent)) {
			context.startActivity(intent);
		} else {
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(getGooglePlayLink(packageName)));
			context.startActivity(intent);
		}
	}
	
	public static String getGooglePlayLink(String packageName, String referrer) {
		StringBuilder builder = new StringBuilder();
		builder.append(GOOGLE_PLAY_DETAILS_LINK);
		builder.append(packageName);
		if (referrer != null) {
			builder.append("&referrer=");
			builder.append(referrer);
		}
		return builder.toString();
	}
	
	public static String getGooglePlayLink(String packageName) {
		return getGooglePlayLink(packageName, null);
	}
	
	public static String getGooglePlayLink() {
		return getGooglePlayLink(AppUtils.getApplicationId());
	}
}
