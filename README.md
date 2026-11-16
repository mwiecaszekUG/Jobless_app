# Jobless_app

# Other team review of our code (Week 5)

We would like to start with that codebase follows good coding practices. For example using ResponseEntity to return data instead of raw entity's.
Another advantage of this application is error handling and input validation. This helps with keeping the data clean and prevents future errors.
However we found elements that could undesirable in the final version of the application.
We found that the database credentials are exposed directly in the applicationProperties file.
This may lead to data being compromised.
There is also a simple typo in the workplaceType enum. We think you might want to replace "onstie" with "onsite"
There is also redundant HelloWorld class in controler package. Maybe it should be removed.
To sum up the code provides a good foundation for valuable application.
