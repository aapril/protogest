package com.protogest.service.calendar.outlook.controller;

import com.protogest.service.calendar.outlook.auth.IdToken;
import com.protogest.service.calendar.outlook.service.Event;
import com.protogest.service.calendar.outlook.service.OutlookService;
import com.protogest.service.calendar.outlook.service.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.protogest.service.calendar.outlook.auth.AuthHelper.getTokenFromAuthCode;
import static com.protogest.service.calendar.outlook.service.OutlookServiceBuilder.getOutlookService;

@Controller
public class AuthorizeController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public RedirectView authorize(
            @RequestParam("code") String code,
            @RequestParam("id_token") String idToken,
            HttpServletRequest request) {

        String urlBase = request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) + request.getContextPath() + "/";

        // Make sure that the state query parameter returned matches
        // the expected state
        List<String> result = new ArrayList<>();
        IdToken idTokenObj = IdToken.parseEncodedToken(idToken);
        if (idTokenObj != null) {

            OutlookService outlookService = getOutlookService(getTokenFromAuthCode(code, idTokenObj.getTenantId(), urlBase, env).getAccessToken());
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String startDateTime = format.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 8);
            String endDateTime = format.format(calendar.getTime());
            String properties = "start,end";

            try {
                PagedResult<Event> events = outlookService.getEvents(
                        startDateTime, endDateTime, properties)
                        .execute().body();
                Arrays.stream(events.getValue()).forEach(e -> result.add(format.format(e.getStart().getDateTime())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(env.getProperty("msAuth.redirectUrl"));
        redirectView.addStaticAttribute("dates", String.join(", ", result));
        return redirectView;
    }
}
