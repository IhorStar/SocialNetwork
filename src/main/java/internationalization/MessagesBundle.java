package internationalization;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessagesBundle {
    private Map<String, String> messages = new HashMap<String, String>();

    public Map<String, String> getMessages(String language, String country) {
        String currentLanguage = null;
        String currentCountry = null;
        Locale locale = null;
        ResourceBundle bundle = null;

        if(language == null || country == null) {
            currentLanguage = "en";
            currentCountry = "US";
        }
        else {
            currentLanguage = language;
            currentCountry = country;
        }

        locale = new Locale(currentLanguage, currentCountry);
        bundle = ResourceBundle.getBundle("MessagesBundle", locale);

        messages.put("greetings", bundle.getString("greetings"));
        messages.put("yourEmail", bundle.getString("yourEmail"));
        messages.put("newsContainer", bundle.getString("newsContainer"));
        messages.put("delete", bundle.getString("delete"));
        messages.put("fieldForNews", bundle.getString("fieldForNews"));
        messages.put("enterNews", bundle.getString("enterNews"));
        messages.put("fieldForEditNews", bundle.getString("fieldForEditNews"));
        messages.put("selectNews", bundle.getString("selectNews"));
        messages.put("editNews", bundle.getString("editNews"));
        messages.put("fieldForAddComment", bundle.getString("fieldForAddComment"));
        messages.put("selectNewsToAddComment", bundle.getString("selectNewsToAddComment"));
        messages.put("addComment", bundle.getString("addComment"));
        messages.put("friendList", bundle.getString("friendList"));
        messages.put("addToFriend", bundle.getString("addToFriend"));
        messages.put("cancelFriendship", bundle.getString("cancelFriendship"));

        return messages;
    }
}
