package cf.huzpsb.sample.database;

public class DbHTML {
    public static final String HTML = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>学生信息管理系统</title>\n" +
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
            "        form input[type=\"number\"] {\n" +
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
            "        <h1>Student Database</h1>\n" +
            "    </header>\n" +
            "    <form method=\"post\" action=\"./db/create\" accept-charset=\"UTF-8\">\n" +
            "        <h2>创建新学生</h2>\n" +
            "        <label>姓名: <input type=\"text\" name=\"id\" required></label><br>\n" +
            "        <label>学号: <input type=\"number\" name=\"name\" required></label><br>\n" +
            "        <label>手机: <input type=\"number\" name=\"age\" required></label><br>\n" +
            "        <input type=\"submit\" value=\"确认创建\">\n" +
            "    </form>\n" +
            "    <form method=\"post\" action=\"./db/delete\" accept-charset=\"UTF-8\">\n" +
            "        <h2>删除学生</h2>\n" +
            "        <label>姓名<input type=\"text\" name=\"id\" required></label><br>\n" +
            "        <input type=\"submit\" value=\"确认删除\">\n" +
            "    </form>\n" +
            "    <form method=\"get\" action=\"./db/download\">\n" +
            "        <h2>下载信息表</h2>\n" +
            "        <input type=\"submit\" value=\"点击\">\n" +
            "    </form>\n" +
            "    <form method=\"get\" action=\"./db/query\" accept-charset=\"UTF-8\">\n" +
            "        <h2>查询学生JSON数据</h2>\n" +
            "        <label>姓名<input type=\"text\" name=\"id\" required></label><br>\n" +
            "        <input type=\"submit\" value=\"确认查询\">\n" +
            "    </form>\n" +
            "</body>\n" +
            "</html>\n";
}
