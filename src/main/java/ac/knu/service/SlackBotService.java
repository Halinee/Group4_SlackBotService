package ac.knu.service;

import ac.knu.service.Exception.*;
import lombok.extern.slf4j.Slf4j;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Profile("slack")
@Service
@JBot
@Slf4j
public class SlackBotService extends Bot {
    private HashMap<String, Friend> database = new HashMap<>();
    @Controller(events = {EventType.DIRECT_MENTION})
    public void onReceiveDM(WebSocketSession session, Event event)
            throws UnprocessableCommandException, FriendAddAgeParameterError, WrongNameException, FriendAlreayNameExist, FriendDataBaseEmptyError, EmptyListException, FriendAddNameParameterError, FriendAddGenderParameterError, NotFoundException, FriendDataBaseSizeOver {
        String text = event.getText();
        log.info(text);
        CommandParsingAndCallingService commandParsingAndCallingService = new CommandParsingAndCallingService();
        String result = commandParsingAndCallingService.parseAndCallCommand(database,text);

        reply(session, event, result);
    }

    @Value("${slackBotToken}")
    private String slackToken;

    @PostConstruct
    public void init() {
        System.out.println(slackToken);
    }

    @Override
    public String getSlackToken() {
        return this.slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }
}
