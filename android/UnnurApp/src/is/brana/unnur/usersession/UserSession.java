package is.brana.unnur.usersession;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import is.brana.unnur.utils.Keys;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 05/07/2014.
 */
public class UserSession {

    private static UserSession uniqInstance;
    private static Activity deviceActivity;

    private ArrayList<Long> favorites = new ArrayList<Long>();

    public static synchronized UserSession activeSession(Activity activity){
        if(uniqInstance == null){
            deviceActivity = activity;
            uniqInstance = userSession();
        }
        deviceActivity = activity;
        return uniqInstance;
    }

    public UserSession(){

    }

    public static UserSession userSession(){
        UserSession usersession = null;

        SharedPreferences mPrefs = deviceActivity.getSharedPreferences(Keys.APP_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("activesession", null);


        if(json != null){
            usersession = gson.fromJson(json, UserSession.class);
        }
        else{
            usersession = new UserSession();
        }

        return usersession;
    }

    private void archive()
    {
        SharedPreferences  mPrefs = deviceActivity.getSharedPreferences(Keys.APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        prefsEditor.putString("activesession", json);
        prefsEditor.commit();
    }

    public void addFavorite(Long id)
    {
        if(!favorites.contains(id))
        {
            favorites.add(id);
            archive();
        }
    }

    public void removeFavorite(Long id)
    {
        if(favorites.contains(id))
        {
            favorites.remove(id);
            archive();
        }
    }

    public ArrayList<Long> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Long> favorites) {
        this.favorites = favorites;
    }
}
