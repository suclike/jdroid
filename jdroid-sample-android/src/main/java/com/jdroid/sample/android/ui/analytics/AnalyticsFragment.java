package com.jdroid.sample.android.ui.analytics;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jdroid.android.fragment.AbstractFragment;
import com.jdroid.sample.android.R;
import com.jdroid.sample.android.analytics.AndroidAnalyticsSender;

public class AnalyticsFragment extends AbstractFragment {
	
	@Override
	public Integer getContentFragmentLayout() {
		return R.layout.analytics_fragment;
	}
	
	/**
	 * @see com.jdroid.android.fragment.AbstractFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		findView(R.id.sendExampleEvent).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AndroidAnalyticsSender.get().trackExampleEvent();
			}
		});

		findView(R.id.sendExampleTransaction).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AndroidAnalyticsSender.get().trackExampleTransaction();
			}
		});

		findView(R.id.sendExampleTiming).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AndroidAnalyticsSender.get().trackExampleTiming();
			}
		});
	}
}
