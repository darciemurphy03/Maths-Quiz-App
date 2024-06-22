I used Android Studio to create the UI and Firebase for the realtime database.
Firebase was used to populate questions, and authenticate users.

The realtime database was populated
by inputting the data into google sheets
and exporting the sheet as a JSON file
with a JSON converter Chrome
extension. The JSON file was then
imported into the database.
The parent nodes acted as a method of
accessing a question and all related
choices.

When the app is opened, the log in
screen is inflated and the user is
presented with three options for
signing in. This feature was initially
added with the intention of storing
each user’s high score based on
their UID on Firebase, but it wasn’t
implemented. Instead, it just acts as
a method of viewing who has
logged in by looking at the
authentication database
