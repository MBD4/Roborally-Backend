# Group 16
### Implementation Notes

First off, we would like to preface this by stating that we tried to follow the user stories as closely as possible to avoid deviating too much from the spec. Even so, we still had a number of considerations regarding the implementation of the project. One of the first things that we noticed is that Spring Boot (for better or worse) does a lot for you. One such feature is database management, where we are able to force uniqueness simply by adding a `@Column(unique=true)` annotation.

Something we discussed as well—since we now have a backend and a frontend—is where to perform our checks. Do we request the data we need from the server and perform them client-side, or do we act like a production environment where you keep many of the checks in the backend? We decided on the path of least resistance: if we needed data from the server, we ran the check server-side; if we could make do with checking client-side, we did that.

We experimented with using derived queries, which automatically generate the JPQL behind the scenes. They were very useful in our project, specifically for our createPlayer() method in PlayerService, being able to call the custom query method from the PlayerRepository class. We actually added some derived queries to all Repository classes, even though they feel a bit 'cursed' to use, we still found them convenient.

---

### Testing

We "only" did manual testing for this project, which actually worked very well. We, for example, found the bug where minimum players can be bigger than maximum players when making a game. (We then noticed that there was also a `TODO` comment added there, but still—TESTING ROCKS!) We tried to be meticulous by trying every single functionality in every way, and that worked well for us.

---

### Extras

We also implemented some optional functionality, like the aforementioned min-max condition where the minimum player count cannot be more than the maximum player count when creating a game. We also implemented functionality where, if an owner of a game starts the board, the rest of the joined players can join or start a board of the same type locally.

One thing we are a bit unsure of is the status line, where it says that the current player is the owner even on the other players' clients. We just chalked that up to not implementing the actual game functionality; since we cannot start the game, it might actually make sense that the current player never changes. We thought about removing it, but following the screenshots from the assignment details, we decided to let it stay.

An implementation-specific part where we were indecisive regarding best practice was whether or not to print "Start" or "Play" to the screen depending on if you are an owner or a player. The way we ended up doing it was just making a button with the "Start" name, and after the fact, making an `if` statement that overrides the button text to say "Play." We actually did not know if it was better to just have the two categories of buttons be their own thing, but "if it works, it works," I guess. It was interesting to think about, though.

---

### Things we are not sure of
In the `createschema.sql` file there is a TODO that says "still some stuff missing here". We made everything work without it, so i hope we arent missing anything lol.

We also deleted the db files even though it wasnt explicitly mentioned. I'm guessing you don't want our endless supply of test games in the online games section.