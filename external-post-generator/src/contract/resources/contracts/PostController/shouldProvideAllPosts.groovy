package contracts.postservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("If all the posts are requested, they should be returned")
        method('GET')
        url "/posts"
    }
    response {
        status OK()
        body('{}')
        headers {
            client('application/json')
            server('application/json')
        }
    }
}
