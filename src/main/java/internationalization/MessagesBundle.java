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
        messages.put("addCommentFailed", bundle.getString("addCommentFailed"));
        messages.put("saveNewsFailed", bundle.getString("saveNewsFailed"));
        messages.put("friendRequestSuccess", bundle.getString("friendRequestSuccess"));
        messages.put("friendRequestFailed", bundle.getString("friendRequestFailed"));
        messages.put("deleteCommentFailed", bundle.getString("deleteCommentFailed"));
        messages.put("cancelFriendshipSuccess", bundle.getString("cancelFriendshipSuccess"));
        messages.put("cancelFriendshipFailed", bundle.getString("cancelFriendshipFailed"));
        messages.put("deleteNewsFailed", bundle.getString("deleteNewsFailed"));
        messages.put("deleteUserFailed", bundle.getString("deleteUserFailed"));
        messages.put("emptyName", bundle.getString("emptyName"));
        messages.put("emptyEmail", bundle.getString("emptyEmail"));
        messages.put("emptyPassword", bundle.getString("emptyPassword"));
        messages.put("emailOrPasswordInvalid", bundle.getString("emailOrPasswordInvalid"));
        messages.put("noUserFound", bundle.getString("noUserFound"));
        messages.put("registrationFailed", bundle.getString("registrationFailed"));
        messages.put("updateCommentFailed", bundle.getString("updateCommentFailed"));
        messages.put("updateNewsFailed", bundle.getString("updateNewsFailed"));


        return messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
}
