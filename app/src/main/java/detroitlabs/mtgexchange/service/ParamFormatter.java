package detroitlabs.mtgexchange.service;

import java.util.Iterator;
import java.util.List;

public final class ParamFormatter {

    private ParamFormatter() {}

    public static String formatList(List<?> list) {

        String value = null;

        if (list != null) {

            StringBuffer sb = new StringBuffer();

            Iterator<?> i = list.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                sb.append(o.toString());
                if (i.hasNext()) {
                    sb.append(",");
                }
            }

            value = sb.toString();
        }

        return value;
    }
}
