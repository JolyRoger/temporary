package home.torquemada.websocketdemo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.yaml.snakeyaml.nodes.Tag.STR;

@RestController("/")
public class DemoController {

    @GetMapping("date")
    public String getResponse() {
        return LocalDate.now().toString();
    }

    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("Message received: message.name()");
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.name()) + "!");
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
    public Flux<Greeting> greeting2(HelloMessage message) throws Exception {
            return Flux.interval(Duration.ofSeconds(1))
                    .map(sequence -> new Greeting("Flux - " + message.name() + "\t" + LocalTime.now().toString()));
        }
}
