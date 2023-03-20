package cf.huzpsb.sample.session;

import cf.huzpsb.sample.Main;

public class SessionHTML {
    private static final String login = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>Hello World</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        header {\n" +
            "            background-color: #333;\n" +
            "            color: white;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "        h1 {\n" +
            "            margin: 0;\n" +
            "        }\n" +
            "        form {\n" +
            "            margin: 20px;\n" +
            "            border: 1px solid #ccc;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "        form label {\n" +
            "            display: block;\n" +
            "            margin-bottom: 5px;\n" +
            "        }\n" +
            "        form input[type=\"text\"],\n" +
            "        form input[type=\"password\"] {\n" +
            "            padding: 5px;\n" +
            "            border-radius: 3px;\n" +
            "            border: 1px solid #ccc;\n" +
            "            width: 200px;\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "        form input[type=\"submit\"] {\n" +
            "            padding: 5px 10px;\n" +
            "            border-radius: 3px;\n" +
            "            border: none;\n" +
            "            background-color: #333;\n" +
            "            color: white;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "        form input[type=\"submit\"]:hover {\n" +
            "            background-color: #666;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <header>\n" +
            "        <h1>你好，世界！</h1>\n" +
            "    </header>\n" +
            "    <form method=\"post\" action=\"" + Main.preUri + "/session/login\">\n" +
            "        <h2>登录:</h2>\n" +
            "        <label>用户: <input type=\"text\" name=\"username\" required></label><br>\n" +
            "        <label>密码: <input type=\"password\" name=\"password\" required></label><br>\n" +
            "        {msg}<br>\n" +
            "        <input type=\"submit\" value=\"确认\">\n" +
            "    </form>\n" +
            "</body>\n" +
            "</html>\n";

    private static final String user = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>Hello World</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        header {\n" +
            "            background-color: #333;\n" +
            "            color: white;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "        h1 {\n" +
            "            margin: 0;\n" +
            "        }\n" +
            "        form {\n" +
            "            margin: 20px;\n" +
            "            border: 1px solid #ccc;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "        form label {\n" +
            "            display: block;\n" +
            "            margin-bottom: 5px;\n" +
            "        }\n" +
            "        form input[type=\"submit\"] {\n" +
            "            padding: 5px 10px;\n" +
            "            border-radius: 3px;\n" +
            "            border: none;\n" +
            "            background-color: #333;\n" +
            "            color: white;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "        form input[type=\"submit\"]:hover {\n" +
            "            background-color: #666;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <header>\n" +
            "        <h1>你好，{user}！</h1>\n" +
            "    </header>\n" +
            "    <form method=\"post\" action=\"" + Main.preUri + "/session/login\">\n" +
            "        <input type=\"hidden\" name=\"logout\" value=\"true\">\n" +
            "        身份：{msg}<br>\n" +
            "        <input type=\"submit\" value=\"登出\">\n" +
            "    </form>\n" +
            "</body>\n" +
            "</html>\n";

    public static String getLogin(String msg) {
        return login.replace("{msg}", msg);
    }

    public static String getUser(String user, String msg) {
        return SessionHTML.user.replace("{user}", user).replace("{msg}", msg);
    }
}
