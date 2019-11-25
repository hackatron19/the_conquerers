import android.app.Application;

import com.firebase.client.Firebase;

public class Foodish extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
