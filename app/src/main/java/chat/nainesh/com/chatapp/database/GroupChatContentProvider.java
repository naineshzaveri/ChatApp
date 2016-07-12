package chat.nainesh.com.chatapp.database;

public class GroupChatContentProvider extends android.content.ContentProvider {

	// database
	private GroupChatDatabaseHelper database;

	// used for the UriMacher

	private static final int GROUP_CHAT = 10;

	private static final android.content.UriMatcher sURIMatcher = new android.content.UriMatcher(
			android.content.UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(chat.nainesh.com.chatapp.database.tables.GroupChatTable.AUTHORITY,
				chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.BASE_PATH, GROUP_CHAT);
	}

	@Override
	public boolean onCreate() {
		database = new GroupChatDatabaseHelper(getContext());
		return false;
	}

	@Override
	public android.database.Cursor query(android.net.Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		android.util.Log.i("ContentProvider"," query");
		int uriType = sURIMatcher.match(uri);
		android.database.sqlite.SQLiteDatabase db = database.getWritableDatabase();
		android.database.Cursor cursor = null;

		if (uriType == GROUP_CHAT) {
			cursor = db
					.query(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.TABLE_NAME,
							projection, selection, selectionArgs, null, null,
							sortOrder);
		}
		// make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public android.net.Uri insert(android.net.Uri uri, android.content.ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		android.util.Log.i("ContentProvider"," insert");
		android.database.sqlite.SQLiteDatabase sqlDB = database.getWritableDatabase();
		long id = 0;
		if (uriType == GROUP_CHAT) {
			id = sqlDB.insert(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.TABLE_NAME, null,
					values);
			return android.net.Uri
					.parse(chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.BASE_PATH + "/" + id);
		}
		getContext().getContentResolver().notifyChange(uri, null);

		return android.net.Uri.parse("");
	}

	@Override
	public int delete(android.net.Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		android.util.Log.i("ContentProvider"," delete");
		android.database.sqlite.SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;

		if (uriType == GROUP_CHAT) {
			rowsDeleted = sqlDB.delete(
					chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.TABLE_NAME, selection,
					selectionArgs);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public int update(android.net.Uri uri, android.content.ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		android.util.Log.i("ContentProvider"," update");
		android.database.sqlite.SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsUpdated = 0;

		if (uriType == GROUP_CHAT) {
			rowsUpdated = sqlDB.update(
					chat.nainesh.com.chatapp.database.tables.GroupChatTable.Chat.TABLE_NAME, values,
					selection, selectionArgs);
		}
		getContext().getContentResolver().notifyChange(uri, null);

		return rowsUpdated;
	}

	@Override
	public String getType(android.net.Uri uri) {
		return null;
	}

}
