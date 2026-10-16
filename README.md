## Reflection

### What changed from your specification?
- **Classes:**
  - Added `Library`, which stores a `LinkedChain<Playlist>`, so the chain holds a second element type and users can have several playlists. *(spec feedback)*
  - `Main` now runs the menu and `Playlist` only manages songs. The spec had Playlist doing both. *(spec feedback)*
  - `CustomSongHandler` isn't built yet.
- **Method signatures:**
  - `removeSong` now returns `boolean`. *(spec feedback)*
  - `nextSong` plays the next song in order instead of a random one. *(spec feedback)*
  - Added `previousSong`, `getCurrentSong`, `getSong`, `size` and `isEmpty` to `Playlist`.
  - Added `equals`, `hashCode` and `toString` to `Song`, so removing a song by value works. *(spec feedback*
  - Added `getNonBlankInput` to `InputHandler`. The spec's generic `getUserInput` wasn't built. 
- **Where validation lives:** same as the spec, with one change. Main checks for an empty playlist before calling Playlist, so it can show a clearer message. *(own reflection)*
- **Test cases:** the spec had one sentence. We wrote 91 JUnit tests across `SongTest`, `PlaylistTest`, `LibraryTest` and `InputHandlerTest`, including an empty playlist and removing a song that isn't there. *(spec feedback; InputHandlerTest and LibraryTest drafted with GenAI)*

### What changed in the LinkedChain?
We made the provided class doubly linked instead of adding a separate class, so every element type still goes through one `LinkedChain<T>`. We added:
- a `previous` link on each node
- a tail reference, which makes `addToEnd` O(1)
- a cursor (`moveNext`, `movePrevious`, `getCurrent`, `resetCursor`), so next and previous song are O(1)
- the missing `package` line

*(scope + GenAI)*

### What happened to your unknowns?
- **Figured out:** moving backwards through a playlist, using the doubly linked chain.
- **Still open:** audio playback with the Java Sound API, and loading songs from files.

### What doesn't work yet?
- No audio playback or file loading yet.
- `Main` has no tests.
- `Library.switchTo` selects the object passed in, not the matching stored playlist.
- Playlists aren't saved between runs.

## Next Steps

### What is left to finish the MVP?
1. Fix `Library.switchTo` and add a test for it. *(Rishad)*
2. Add a test for `Playlist.addSong(null)`. *(Maddie)*
3. Write `MainTest` using simulated input. *(Ryan)*
4. Add run instructions and the GenAI note to the README. *(All)*

### Which stretch goals will you attempt?
- **Committed:** `CustomSongHandler` to load songs from `.wav` files, and playback with the Java Sound API, which can't play MP3. *(Paulo and Rishad)*

### What new tests will you need?
`CustomSongHandlerTest` *(Ryan and Maddie)*:
- **Normal cases:** a valid `.wav` file; a folder with several songs.
- **Bad input:** a missing file, a non-audio or `.mp3` file, a null or blank path, and an empty or missing folder.
- **Playback:** a song with no file shows an error message instead of crashing.

### Who does what?
- **Paulo and Rishad:** `CustomSongHandler` and playback.
- **Ryan and Maddie:** `CustomSongHandlerTest` and `MainTest`.
- **Rishad:** the `switchTo` fix.
- **Maddie:** the `SongTest` and the `PlaylistTest` classes.

## Attribution

| Class | Person | Contribution |
| --- | --- | --- |
| Playlist.java | Paulo | Wrote |
| Song.java | Paulo | Wrote |
| InputHandler.java | Paulo | Wrote |
| Library.java | Rishad | Wrote |
| Main.java | Rishad | Wrote |
| InputHandlerTest.java | Paulo | Used GenAI to write test cases |
| LibraryTest.java | Paulo | Used GenAI to write test cases |
| SongTest.java | Maddie | Wrote |
| PlaylistTest.java | Maddie | Wrote |
| LinkedChain.java | Rishad | Modified |