# LinkedChain-Playlist

## Reflection
### What changed from your specification?
We added a new class, the Library class, to store multiple playlists for a user. We added this because being able to support multiple playlists is extremely useful and allows us to implement two different LinkedChains.
We also added a couple or extra methods that we didn't think we would need. For example, in the InputHandler class, we added the getNonBlankInput method to get a non-blank input.
### What changed in the LinkedChain?
We modified the LinkedChain to be a doubly LinkedChain so that we could play the next song and the previous song.
### What happened to your unknowns?
We haven't quite started with implementing the song playing functionality, so that is our task for this week.
### What doesn't work yet?
Most of our MVP features are done, so the last thing that doesn't work is our stretch goal of playing songs from their audio file.

## Next Steps
### What is left to finish the MVP? The remaining features and fixes, in the order you will do them.
The MVP is mostly done at this point. Whilst we have our test cases written, we might do some further program testing to ensure everything works as intended.
### Which stretch goals will you attempt? The ones from your scope you are committing to, and the ones you are dropping.
We are still committing to our stretch goal of playing songs with the Java Sound API.
### What new tests will you need? The methods and bad-input cases the added work must cover.
Once we finish adding the Sound API, we need to add test cases to make sure that all of the sound files are being loaded and played correctly. We need to be able to catch if a song file doesn't exist or we don't have permission to play it and report the error to the user.
### Who does what? Which team member owns each remaining item.
Paulo and Rishad - CustomSongHandler.java
Ryan and Maddie - CustomSongHandlerTest.java and general testing of program

## Attribution
| Class                 | Person | Conribution                    |
|-----------------------|--------|--------------------------------|
| Playlist.java         | Paulo  | Wrote                          |
| Song.java             | Paulo  | Wrote                          |
| InputHandler.java     | Paulo  | Wrote                          |
| Library.java          | Rishad | Wrote                          |
| Main.java             | Rishad | Wrote                          |
| InputHandlerTest.java | Paulo  | Used GenAI to write test cases |
| LibraryTest.java      | Paulo  | Used GenAI to write test cases |
| SongTest.java         | Maddie | Wrote                          |
| PlaylistTest.java     | Maddie | Wrote                          |
| LinkedChain.java      | Rishad | Modified                       |
