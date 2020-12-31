package contracts.comment.generator;

import org.springframework.cloud.contract.spec.Contract;
import org.springframework.cloud.contract.verifier.util.ContractVerifierUtil;

import java.util.function.Supplier;

public class CommentSenderContract implements Supplier<Contract> {
  @Override
  public Contract get() {
    return Contract.make(c -> {
      c.description("Send a new comment to the channel");
      c.outputMessage(msg -> {
        msg.sentTo("output");
        msg.body(ContractVerifierUtil.map()
            .entry("id", 42)
            .entry("author", "Test author")
            .entry("text", "Comment text"));
      });
    });
  }
}
