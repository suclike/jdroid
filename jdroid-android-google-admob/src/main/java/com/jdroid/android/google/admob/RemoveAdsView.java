package com.jdroid.android.google.admob;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jdroid.android.application.AbstractApplication;
import com.jdroid.android.context.UsageStats;
import com.jdroid.java.date.DateUtils;

public class RemoveAdsView extends RelativeLayout {
	
	public RemoveAdsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public RemoveAdsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public RemoveAdsView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(final Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.jdroid_remove_ads_view, this, true);
		((ImageView)view.findViewById(R.id.icon)).setImageResource(AbstractApplication.get().getLauncherIconResId());
	}
	
	@Override
	public void setOnClickListener(final OnClickListener removeAdsClickListener) {
		super.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				removeAdsClickListener.onClick(v);
				AdMobAppModule.get().getAnalyticsSender().trackRemoveAdsBannerClicked();
			}
		});
	}
	
	public static Boolean displayRemoveAdsView() {
		return DateUtils.millisecondsToDays(UsageStats.getFirstAppLoadTimestamp()) > 3;
	}
}
