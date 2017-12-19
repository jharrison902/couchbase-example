# couchbase-example
An example of couchbase with spring data

# Installation
Things you'll need:

* Java 8
* Couchbase Installed

Once you've installed java 8 and couchbase, configure your bucket and create a user with the same name as the bucket and a password. In Application.java you can set these values. In my example I use "note" and "password".

I recommend setting up a full text search index on the body field, as well. You can find guides on setting this up here https://developer.couchbase.com/documentation/server/current/sdk/java/full-text-searching-with-sdk.html

# Running

simply use mvn spring-boot:run to launch the application and a tool such as Postman to test it out.
