package linkedChainPlaylist;

import student.TestCase;

/**
 * Tests the methods in the Song class.
 */
public class SongTest extends TestCase {
    private Song song;

    /**
     * Sets up the test fixture.
     */
    public void setUp() {
        song = new Song("Hey Jude", "The Beatles", 431, "heyjude.mp3");
    }


    /**
     * Tests the constructor and getter methods.
     */
    public void testConstructorAndGetters() {
        assertEquals("Hey Jude", song.getName());
        assertEquals("The Beatles", song.getAuthor());
        assertEquals(431, song.getSongLength());
        assertEquals("heyjude.mp3", song.getSongFile());
    }


    /**
     * Tests the constructor when the song file is null.
     */
    public void testConstructorNullFile() {
        Song songWithoutFile = new Song("Imagine", "John Lennon", 183, null);

        assertEquals("Imagine", songWithoutFile.getName());
        assertEquals("John Lennon", songWithoutFile.getAuthor());
        assertEquals(183, songWithoutFile.getSongLength());
        assertNull(songWithoutFile.getSongFile());
    }


    /**
     * Tests setting the song's name.
     */
    public void testSetName() {
        song.setName("Imagine");

        assertEquals("Imagine", song.getName());
    }


    /**
     * Tests setting the song's author.
     */
    public void testSetAuthor() {
        song.setAuthor("John Lennon");

        assertEquals("John Lennon", song.getAuthor());
    }


    /**
     * Tests setting the song's length.
     */
    public void testSetSongLength() {
        song.setSongLength(183);

        assertEquals(183, song.getSongLength());
    }


    /**
     * Tests setting the song's file path.
     */
    public void testSetSongFile() {
        song.setSongFile("imagine.mp3");

        assertEquals("imagine.mp3", song.getSongFile());

        song.setSongFile(null);

        assertNull(song.getSongFile());
    }


    /**
     * Tests equals when comparing a song with itself.
     */
    public void testEqualsSameObject() {
        assertTrue(song.equals(song));
    }


    /**
     * Tests equals when two songs have the same attributes.
     */
    public void testEqualsSameAttributes() {
        Song other = new Song("Hey Jude", "The Beatles", 431, "different.mp3");

        assertTrue(song.equals(other));
        assertTrue(other.equals(song));
    }


    /**
     * Tests equals when the song names are different.
     */
    public void testEqualsDifferentName() {
        Song other = new Song("Let It Be", "The Beatles", 431, "heyjude.mp3");

        assertFalse(song.equals(other));
    }


    /**
     * Tests equals when the authors are different.
     */
    public void testEqualsDifferentAuthor() {
        Song other = new Song("Hey Jude", "John Lennon", 431, "heyjude.mp3");

        assertFalse(song.equals(other));
    }


    /**
     * Tests equals when the song lengths are different.
     */
    public void testEqualsDifferentLength() {
        Song other = new Song("Hey Jude", "The Beatles", 432, "heyjude.mp3");

        assertFalse(song.equals(other));
    }


    /**
     * Tests equals when comparing with null.
     */
    public void testEqualsNull() {
        assertFalse(song.equals(null));
    }


    /**
     * Tests equals when comparing with a different type of object.
     */
    public void testEqualsDifferentType() {
        assertFalse(song.equals("Hey Jude"));
    }


    /**
     * Tests equals when the song names are null.
     */
    public void testEqualsNullName() {
        Song first = new Song(null, "The Beatles", 431, "song.mp3");
        Song second = new Song(null, "The Beatles", 431, "other.mp3");

        assertTrue(first.equals(second));
    }


    /**
     * Tests equals when the song authors are null.
     */
    public void testEqualsNullAuthor() {
        Song first = new Song("Hey Jude", null, 431, "song.mp3");
        Song second = new Song("Hey Jude", null, 431, "other.mp3");

        assertTrue(first.equals(second));
    }


    /**
     * Tests hashCode for two equal songs.
     */
    public void testHashCode() {
        Song other = new Song("Hey Jude", "The Beatles", 431, "different.mp3");

        assertEquals(song.hashCode(), other.hashCode());
    }


    /**
     * Tests toString with a song length containing seconds.
     */
    public void testToString() {
        assertEquals("Hey Jude by The Beatles (7:11)", song.toString());
    }


    /**
     * Tests toString when the seconds require a leading zero.
     */
    public void testToStringLeadingZero() {
        Song shortSong = new Song("Song", "Artist", 125, null);

        assertEquals("Song by Artist (2:05)", shortSong.toString());
    }


    /**
     * Tests toString when the song is less than one minute long.
     */
    public void testToStringLessThanOneMinute() {
        Song shortSong = new Song("Intro", "Artist", 9, null);

        assertEquals("Intro by Artist (0:09)", shortSong.toString());
    }
}
