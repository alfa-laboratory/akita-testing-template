@objects
    registration-form         css      .d-md-flex .rounded-1
    description-title         css      .d-md-flex h1
    description-form          css      .d-md-flex .col-md-7



= Main section =
    @on desktop
        registration-form:
            image file  images/registration-form.png, error 10%

        description-form:
            left-of registration-form

        description-title:
            inside description-form
            text is "Built for developers"