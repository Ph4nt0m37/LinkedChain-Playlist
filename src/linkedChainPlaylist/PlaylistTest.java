package linkedChainPlaylist;

import student.TestCase;

/**
 * Tests the methods in the Playlist class.
 */
public class PlaylistTest extends TestCase {
    private Playlist playlist;
    private Song song1;
    private Song song2;
    private Song song3;

    /**
     * Sets up the test fixture.
     */
    public void setUp() {
        playlist = new Playlist("Gym");
        song1 = new Song("Hey Jude", "The Beatles", 431, "heyjude.mp3");
        song2 = new Song("Imagine", "John Lennon", 183, "imagine.mp3");
        song3 = new Song("Let It Be", "The Beatles", 243, "letitbe.mp3");
    }


    /**
     * Tests the constructor and getter method.
     */
    public void testConstructorAndGetName() {
        assertEquals("Gym", playlist.getName());
        assertTrue(playlist.isEmpty());
        assertEquals(0, playlist.size());
    }


    /**
     * Tests the constructor with a name containing whitespace.
     */
    public void testConstructorTrimName() {
        Playlist other = new Playlist("  Workout  ");

        assertEquals("Workout", other.getName());
    }


    /**
     * Tests the constructor with a null name.
     */
    public void testConstructorNullName() {
        Exception thrown = null;

        try {
            new Playlist(null);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }

        assertNotNull(thrown);
    }


    /**
     * Tests the constructor with a blank name.
     */
    public void testConstructorBlankName() {
        Exception thrown = null;

        try {
            new Playlist("   ");
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }

        assertNotNull(thrown);
    }


    /**
     * Tests adding a song to the playlist.
     */
    public void testAddSong() {
        playlist.addSong(song1);

        assertEquals(1, playlist.size());
        assertFalse(playlist.isEmpty());
        assertEquals(song1, playlist.getSong(0));
    }


    /**
     * Tests adding multiple songs to the playlist.
     */
    public void testAddMultipleSongs() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        assertEquals(3, playlist.size());
        assertEquals(song1, playlist.getSong(0));
        assertEquals(song2, playlist.getSong(1));
        assertEquals(song3, playlist.getSong(2));
    }


    /**
     * Tests removing a song from the playlist.
     */
    public void testRemoveSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        assertTrue(playlist.removeSong(song1));
        assertEquals(1, playlist.size());
        assertEquals(song2, playlist.getSong(0));
    }


    /**
     * Tests removing a song that is not in the playlist.
     */
    public void testRemoveSongNotFound() {
        playlist.addSong(song1);

        assertFalse(playlist.removeSong(song2));
        assertEquals(1, playlist.size());
    }


    /**
     * Tests removing a song from an empty playlist.
     */
    public void testRemoveSongEmpty() {
        assertFalse(playlist.removeSong(song1));
        assertTrue(playlist.isEmpty());
    }


    /**
     * Tests getting the first and last songs.
     */
    public void testGetSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        assertEquals(song1, playlist.getSong(0));
        assertEquals(song2, playlist.getSong(1));
    }


    /**
     * Tests getting a song with a negative index.
     */
    public void testGetSongNegativeIndex() {
        Exception thrown = null;

        try {
            playlist.getSong(-1);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }

        assertNotNull(thrown);
    }


    /**
     * Tests getting a song with an index equal to the playlist size.
     */
    public void testGetSongIndexOutOfBounds() {
        playlist.addSong(song1);

        Exception thrown = null;

        try {
            playlist.getSong(1);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }

        assertNotNull(thrown);
    }


    /**
     * Tests nextSong when the playlist is empty.
     */
    public void testNextSongEmpty() {
        assertNull(playlist.nextSong());
        assertNull(playlist.getCurrentSong());
    }


    /**
     * Tests playing the next song.
     */
    public void testNextSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        assertEquals(song1, playlist.nextSong());
        assertEquals(song1, playlist.getCurrentSong());
        assertEquals(song2, playlist.nextSong());
        assertEquals(song2, playlist.getCurrentSong());
    }


    /**
     * Tests nextSong wrapping from the last song to the first.
     */
    public void testNextSongWrapAround() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        playlist.nextSong();
        playlist.nextSong();

        assertEquals(song1, playlist.nextSong());
        assertEquals(song1, playlist.getCurrentSong());
    }


    /**
     * Tests previousSong before any song has been played.
     */
    public void testPreviousSongBeforePlayback() {
        playlist.addSong(song1);

        assertNull(playlist.previousSong());
    }


    /**
     * Tests previousSong when the current song is the first song.
     */
    public void testPreviousSongFirstSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        playlist.nextSong();

        assertNull(playlist.previousSong());
    }


    /**
     * Tests moving to the previous song.
     */
    public void testPreviousSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        playlist.nextSong();
        playlist.nextSong();

        assertEquals(song1, playlist.previousSong());
        assertEquals(song1, playlist.getCurrentSong());
    }


    /**
     * Tests getCurrentSong before playback begins.
     */
    public void testGetCurrentSongBeforePlayback() {
        playlist.addSong(song1);

        assertNull(playlist.getCurrentSong());
    }


    /**
     * Tests getCurrentSong after playback begins.
     */
    public void testGetCurrentSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        playlist.nextSong();

        assertEquals(song1, playlist.getCurrentSong());
    }


    /**
     * Tests shuffling an empty playlist.
     */
    public void testShuffleEmpty() {
        playlist.shuffle();

        assertTrue(playlist.isEmpty());
        assertEquals(0, playlist.size());
    }


    /**
     * Tests shuffling a playlist with one song.
     */
    public void testShuffleOneSong() {
        playlist.addSong(song1);

        playlist.shuffle();

        assertEquals(1, playlist.size());
        assertEquals(song1, playlist.getSong(0));
    }


    /**
     * Tests shuffling a playlist with multiple songs.
     */
    public void testShuffleMultipleSongs() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        playlist.shuffle();

        assertEquals(3, playlist.size());
        assertTrue(playlist.getSong(0).equals(song1) || playlist.getSong(0)
            .equals(song2) || playlist.getSong(0).equals(song3));
        assertTrue(playlist.getSong(1).equals(song1) || playlist.getSong(1)
            .equals(song2) || playlist.getSong(1).equals(song3));
        assertTrue(playlist.getSong(2).equals(song1) || playlist.getSong(2)
            .equals(song2) || playlist.getSong(2).equals(song3));
    }


    /**
     * Tests equals when comparing a playlist with itself.
     */
    public void testEqualsSameObject() {
        assertTrue(playlist.equals(playlist));
    }


    /**
     * Tests equals when two playlists have the same name.
     */
    public void testEqualsSameName() {
        Playlist other = new Playlist("Gym");

        assertTrue(playlist.equals(other));
        assertTrue(other.equals(playlist));
    }


    /**
     * Tests equals when playlist names differ only in case.
     */
    public void testEqualsDifferentCase() {
        Playlist other = new Playlist("gYm");

        assertTrue(playlist.equals(other));
    }


    /**
     * Tests equals when playlist names are different.
     */
    public void testEqualsDifferentName() {
        Playlist other = new Playlist("Study");

        assertFalse(playlist.equals(other));
    }


    /**
     * Tests equals when comparing with null.
     */
    public void testEqualsNull() {
        assertFalse(playlist.equals(null));
    }


    /**
     * Tests equals when comparing with a different type of object.
     */
    public void testEqualsDifferentType() {
        assertFalse(playlist.equals("Gym"));
    }


    /**
     * Tests hashCode for two equal playlists.
     */
    public void testHashCode() {
        Playlist other = new Playlist("gYm");

        assertEquals(playlist.hashCode(), other.hashCode());
    }


    /**
     * Tests toString for an empty playlist.
     */
    public void testToStringEmpty() {
        assertEquals("Gym (0 songs)", playlist.toString());
    }


    /**
     * Tests toString for a playlist with one song.
     */
    public void testToStringOneSong() {
        playlist.addSong(song1);

        assertEquals("Gym (1 song)", playlist.toString());
    }


    /**
     * Tests toString for a playlist with multiple songs.
     */
    public void testToStringMultipleSongs() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        assertEquals("Gym (2 songs)", playlist.toString());
    }
}
