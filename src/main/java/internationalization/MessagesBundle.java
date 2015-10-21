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

        return messages;
    }
}
