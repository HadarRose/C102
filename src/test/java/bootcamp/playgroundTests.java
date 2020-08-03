package bootcamp;
import bootcamp.model.ErrorMessage;
import bootcamp.model.Message;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*This tests suite does not run with maven packaging/testing etc. It's here for me to practice new concepts, and is not
* ignored simply so that I don't accidentally lose it for now.*/
public class playgroundTests {

    // understanding how filter works
    @Test
    public void learningStreamsTestFilter(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("this message contains hello!"));
        messages.add(new Message("this message doesn't"));
        messages.add(new Message("This message also contains hello"));
        messages.add(new Message("hello"));

        List<Message> filtered = messages.stream()
                .filter( message -> message.getMessage().contains("hello"))
                .collect(Collectors.toList());
        assertEquals(messages.get(0), filtered.get(0));
        assertEquals(messages.get(2), filtered.get(1));
        assertEquals(messages.get(3), filtered.get(2));

    }

    // understanding how map works
    @Test
    public void learningStreamsTestMap(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("this message contains hello!"));
        messages.add(new Message("this message doesn't"));
        messages.add(new Message("This message also contains hello"));
        messages.add(new Message("hello"));

        List<ErrorMessage> errorMessages = messages.stream()
                .map(message -> new ErrorMessage(200, message.getMessage()))
                .collect(Collectors.toList());
        for(int i = 0; i < 4; i++){
            assertTrue(errorMessages.get(i) instanceof ErrorMessage);
            assertEquals(errorMessages.get(i).getMessage(), messages.get(i).getMessage());
        }
    }

    @Test
    public void learningStreamsTestBadFilter(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("this message contains hello!"));
        messages.add(new Message("this message doesn't"));
        messages.add(new Message("This message also contains hello"));
        messages.add(new Message("hello"));

        List<ErrorMessage> filtered = messages.stream()
                .filter( message -> message.getMessage().contains("banana"))
                .map(message -> new ErrorMessage(200, message.getMessage()))
                .collect(Collectors.toList());
        assertTrue(filtered.isEmpty());
    }


}
