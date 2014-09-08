package com.jdroid.android.sqlite;

import java.util.Date;
import android.content.ContentValues;
import android.database.Cursor;
import com.jdroid.android.utils.AndroidEncryptionUtils;
import com.jdroid.java.utils.DateUtils;

public enum DataType {
	TEXT("TEXT") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, (String)value);
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public String readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			return cursor.getString(columnIndex);
		}
	},
	INTEGER("INTEGER") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, Integer.valueOf(((Number)value).intValue()));
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Integer readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			return Integer.valueOf(cursor.getInt(columnIndex));
		}
	},
	REAL("REAL") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, Double.valueOf(((Number)value).doubleValue()));
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Double readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			return Double.valueOf(cursor.getDouble(columnIndex));
		}
	},
	BLOB("BLOB") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, (byte[])value);
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public byte[] readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			return cursor.getBlob(columnIndex);
		}
	},
	BOOLEAN("INTEGER") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, Integer.valueOf(((Boolean)value) ? 1 : 0));
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Boolean readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			return (cursor.getInt(columnIndex) == 0 ? Boolean.FALSE : Boolean.TRUE);
		}
	},
	DATE("TEXT") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, DateUtils.format((Date)value, DateUtils.YYYYMMDDHHMMSS_DATE_FORMAT));
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Date readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			String date = cursor.getString(columnIndex);
			return DateUtils.parse(date, DateUtils.YYYYMMDDHHMMSS_DATE_FORMAT);
		}
		
	},
	DATE_TZ("TEXT") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				
				values.put(columnName, DateUtils.format((Date)value, DateUtils.YYYYMMDDHHMMSSZ_DATE_FORMAT));
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Date readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			String date = cursor.getString(columnIndex);
			return DateUtils.parse(date, DateUtils.YYYYMMDDHHMMSSZ_DATE_FORMAT);
		}
		
	},
	ENCRYPTED_TEXT("TEXT") {
		
		@Override
		public <T> void writeValue(ContentValues values, String columnName, T value) {
			if (value != null) {
				values.put(columnName, AndroidEncryptionUtils.encrypt((String)value));
			} else {
				values.putNull(columnName);
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public String readValue(Cursor cursor, String columnName) {
			int columnIndex = cursor.getColumnIndex(columnName);
			if (cursor.isNull(columnIndex)) {
				return null;
			}
			return AndroidEncryptionUtils.decrypt(cursor.getString(columnIndex));
		}
	};
	
	private String type;
	
	private DataType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public abstract <T> void writeValue(ContentValues values, String columnName, T value);
	
	public abstract <T> T readValue(Cursor cursor, String columnName);
}