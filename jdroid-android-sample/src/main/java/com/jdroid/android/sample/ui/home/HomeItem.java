package com.jdroid.android.sample.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.jdroid.android.ActionItem;
import com.jdroid.android.activity.ActivityLauncher;
import com.jdroid.android.sample.R;
import com.jdroid.android.sample.ui.ads.AdsActivity;
import com.jdroid.android.sample.ui.analytics.AnalyticsActivity;
import com.jdroid.android.sample.ui.appinvite.AppInviteActivity;
import com.jdroid.android.sample.ui.cardview.CardViewActivity;
import com.jdroid.android.sample.ui.datetime.DateTimeActivity;
import com.jdroid.android.sample.ui.exceptions.ErrorDisplayerActivity;
import com.jdroid.android.sample.ui.fab.FabActivity;
import com.jdroid.android.sample.ui.facebook.FacebookSignInActivity;
import com.jdroid.android.sample.ui.firebase.database.FirebaseDatabaseActivity;
import com.jdroid.android.sample.ui.google.gcm.GcmActivity;
import com.jdroid.android.sample.ui.google.playservices.GooglePlayServicesActivity;
import com.jdroid.android.sample.ui.google.signin.GoogleSignInActivity;
import com.jdroid.android.sample.ui.hero.HeroActivity;
import com.jdroid.android.sample.ui.http.HttpActivity;
import com.jdroid.android.sample.ui.imageloader.ImageLoaderActivity;
import com.jdroid.android.sample.ui.loading.LoadingActivity;
import com.jdroid.android.sample.ui.maps.MapActivity;
import com.jdroid.android.sample.ui.navdrawer.NavDrawerActivity;
import com.jdroid.android.sample.ui.notifications.NotificationsActivity;
import com.jdroid.android.sample.ui.rateme.RateAppActivity;
import com.jdroid.android.sample.ui.recyclerview.RecyclerViewActivity;
import com.jdroid.android.sample.ui.service.ServiceActivity;
import com.jdroid.android.sample.ui.sqlite.SQLiteActivity;
import com.jdroid.android.sample.ui.tablets.LeftTabletActivity;
import com.jdroid.android.sample.ui.tablets.TabletActivity;
import com.jdroid.android.sample.ui.toasts.ToastsActivity;
import com.jdroid.android.sample.ui.twitter.TwitterActivity;
import com.jdroid.android.sample.ui.uri.UriMapperActivity;
import com.jdroid.android.sample.ui.usecases.UseCasesActivity;
import com.jdroid.android.utils.ScreenUtils;

public enum HomeItem implements ActionItem {
	
	ADMOB(R.string.adMob, R.drawable.ic_admob, AdsActivity.class),
	APP_INVITE(R.string.appInvite, R.drawable.ic_admob, AppInviteActivity.class),
	CARD_VIEW(R.string.cardView, R.drawable.ic_cardview, CardViewActivity.class),
	DATE_TIME(R.string.dateTime, R.drawable.ic_date_time, DateTimeActivity.class),
	ERROR_DISPLAYER(R.string.errorDisplayer, R.drawable.ic_exception_handling, ErrorDisplayerActivity.class),
	FAB(R.string.floatingActionButton, R.drawable.ic_firebase, FabActivity.class),
	FACEBOOK(R.string.jdroid_facebook, R.drawable.ic_firebase, FacebookSignInActivity.class),
	FIREBASE_DATABASE(R.string.firebaseDatabase, R.drawable.ic_firebase, FirebaseDatabaseActivity.class),
	GOOGLE_ANALYTCS(R.string.googleAnalytics, R.drawable.ic_analytics, AnalyticsActivity.class),
	GCM(R.string.gcm, R.drawable.ic_gcm, GcmActivity.class),
	GOOGLE_MAPS(R.string.googleMaps, R.drawable.ic_maps, MapActivity.class),
	GOOGLE_PLAY_SERVICES(R.string.googlePlayServices, R.drawable.ic_gcm, GooglePlayServicesActivity.class),
	GOOGLE_SIGN_IN(R.string.jdroid_googleSignIn, R.drawable.ic_sign_in_24dp, GoogleSignInActivity.class),
	HERO(R.string.hero, R.drawable.ic_photo, HeroActivity.class),
	HTTP(R.string.http, R.drawable.ic_http, HttpActivity.class),
	IN_APP_BILLING(R.string.inAppBilling, R.drawable.ic_inapp_billing, ImageLoaderActivity.class),
	LOADING(R.string.loading, R.drawable.ic_loading, LoadingActivity.class),
	NAVDRAWER(R.string.navDrawer, R.drawable.ic_nav_drawer, NavDrawerActivity.class),
	NOTIFICATIONS(R.string.jdroid_notifications, R.drawable.ic_notifications, NotificationsActivity.class),
	RATE_APP(R.string.jdroid_rateApp, R.drawable.ic_rate, RateAppActivity.class),
	RECYCLER_VIEW(R.string.recyclerView, R.drawable.ic_recycler_view, RecyclerViewActivity.class),
	SERVICE(R.string.service, R.drawable.ic_service, ServiceActivity.class),
	SQLITE(R.string.sqlite, R.drawable.ic_sqlite, SQLiteActivity.class),
	TABLETS(R.string.tablets, R.drawable.ic_tablets, ScreenUtils.is10Inches() ? TabletActivity.class : LeftTabletActivity.class),
	TOASTS(R.string.toasts, R.drawable.ic_toasts, ToastsActivity.class),
	TWITTER(R.string.jdroid_twitter, R.drawable.jdroid_ic_twitter, TwitterActivity.class),
	UNVERSAL_IMAGE_LOADER(R.string.universalImageLoader, R.drawable.ic_photo, ImageLoaderActivity.class),
	URI_MAPPER(R.string.jdroid_uriMapper, R.drawable.ic_photo, UriMapperActivity.class),
	USE_CASES(R.string.useCases, R.drawable.ic_service, UseCasesActivity.class);

	private Integer resourceId;
	private Integer iconId;
	private Class<? extends FragmentActivity> activityClass;
	
	HomeItem(Integer resourceId, Integer iconId, Class<? extends FragmentActivity> activityClass) {
		this.resourceId = resourceId;
		this.iconId = iconId;
		this.activityClass = activityClass;
	}
	
	@Override
	public Integer getNameResource() {
		return resourceId;
	}
	
	@Override
	public Integer getIconResource() {
		return iconId;
	}
	
	@Override
	public void startActivity(FragmentActivity fragmentActivity) {
		ActivityLauncher.launchActivity(activityClass);
	}
	
	@Override
	public Boolean matchesActivity(FragmentActivity fragmentActivity) {
		return activityClass.equals(fragmentActivity.getClass());
	}
	
	@Override
	public Fragment createFragment(Object args) {
		return null;
	}
	
	@Override
	public String getName() {
		return name();
	}
	
	@Override
	public Integer getDescriptionResource() {
		return null;
	}
}