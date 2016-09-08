package com.jdroid.android.sample.ui.uri;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.jdroid.android.uri.AbstractUriHandler;

public class NoMatchSameActivityUriHandler extends AbstractUriHandler {
	@Override
	public Boolean matches(Uri uri) {
		return false;
	}

	@Override
	public Intent createMainIntent(Context context, Uri uri) {
		return new Intent(context, NoMatchSameActivity.class);
	}
}
