<activity android:name=".WalletAddCardActivity" android:exported="true">
    <intent-filter>
        <action android:name="com.example.mockwalletapp.ACTION_ADD_CARD_TO_WALLET" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/walletMainText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Mock Wallet App"
        android:textSize="24sp"
        android:textAlignment="center"
        android:paddingBottom="24dp" />

    <Button
        android:id="@+id/addCardButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Add Card" />

</LinearLayout>


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/walletAddCardText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Add Card to Wallet"
        android:textSize="18sp"
        android:textAlignment="center"
        android:paddingBottom="24dp" />

    <!-- Additional UI elements can be added here -->

    <Button
        android:id="@+id/confirmButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Confirm" />

    <Button
        android:id="@+id/cancelButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Cancel"
        android:layout_marginTop="8dp" />

</LinearLayout>
