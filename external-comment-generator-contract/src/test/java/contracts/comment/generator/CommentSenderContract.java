package contracts.comment.generator;

import org.springframework.cloud.contract.spec.Contract;
import org.springframework.cloud.contract.verifier.util.ContractVerifierUtil;

import java.util.function.Supplier;

public class CommentSenderContract implements Supplier<Contract> {
  @Override
  public Contract get() {
    return Contract.make(c -> {
      c.description("Send a new comment to the channel");
      c.input(i -> {
        i.triggeredBy("send()");
      });
      c.outputMessage(msg -> {
        msg.sentTo("comments.destination");
        msg.body(ContractVerifierUtil.map()
            .entry("id", msg.value(msg.client(42), msg.anyNumber()))
            .entry("author", msg.value(msg.client("Test author"), msg.anyNonBlankString()))
            .entry("text", msg.value(msg.client("Comment text"), msg.anyNonBlankString())));
      });
    });
  }
}
