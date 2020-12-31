package contracts.post.controller;

import org.springframework.cloud.contract.spec.Contract;
import org.springframework.cloud.contract.verifier.util.ContractVerifierUtil;

import java.util.List;
import java.util.function.Supplier;

public class ShouldProvideAllPostsPost implements Supplier<Contract> {
  @Override
  public Contract get() {
    return Contract.make(c -> {
      c.description("Posts should be sent back in case of POST request");
      c.request(r -> {
        r.method(r.POST());
        r.url("/posts/export");
      });
      c.response(r -> {
        r.status(r.OK());
        r.body(
            ContractVerifierUtil.map()
                .entry("posts", List.of(ContractVerifierUtil.map()
                    .entry("id", r.value(r.client("1"), r.server(r.anyInteger())))
                    .entry("title", r.value(r.client("This is title"), r.server(r.anyNonBlankString())))
                    .entry("content", r.value(r.client("Content"), r.anyNonBlankString()))
                    .entry("created", r.value(r.client("2020-12-30"), r.server(r.regex("\\d{4}-\\d{2}-\\d{2}"))))
                ))
        );
        r.headers(h -> {
          h.contentType("application/json");
        });
      });
    });
  }
}
