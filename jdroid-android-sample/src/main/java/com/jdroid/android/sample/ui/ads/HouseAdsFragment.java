package com.jdroid.android.sample.ui.ads;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdSize;
import com.jdroid.android.google.admob.AdHelper;
import com.jdroid.android.application.AppModule;
import com.jdroid.android.fragment.AbstractFragment;
import com.jdroid.android.fragment.FragmentDelegate;
import com.jdroid.android.google.admob.AdMobAdHelper;
import com.jdroid.android.google.admob.AdMobAppModule;
import com.jdroid.android.google.admob.AdMobFragmentDelegate;
import com.jdroid.android.google.admob.HouseAdBuilder;
import com.jdroid.android.sample.R;

public class HouseAdsFragment extends AbstractFragment {

	@Override
	public Integer getContentFragmentLayout() {
		return R.layout.house_ads_fragment;
	}

	@Override
	public FragmentDelegate createFragmentDelegate(AppModule appModule) {
		if (appModule instanceof AdMobAppModule) {
			return new AdMobFragmentDelegate(this) {
				@Override
				public void initAdHelper(AdHelper adHelper) {
					AdMobAdHelper adMobAdHelper = (AdMobAdHelper)adHelper;
					adMobAdHelper.setAdSize(AdSize.BANNER);
					adMobAdHelper.setHouseAdBuilder(new HouseAdBuilder() {
						@Override
						public View build(Activity activity) {
							View view = super.build(activity);
							if (view == null) {
								view = new TextView(getActivity());
								((TextView)view).setText("House ad");
							}
							return view;
						}
					});
				}
			};
		} else {
			return super.createFragmentDelegate(appModule);
		}
	}
}
