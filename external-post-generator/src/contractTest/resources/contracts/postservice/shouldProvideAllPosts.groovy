package contracts.postservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("If all the posts are requested, they should be returned")
        method :POST()
        url "/posts"
    }
    response {
        status OK()
        body()
        headers(
                'Content-Type', producer('application/json')
        )
    }
}
