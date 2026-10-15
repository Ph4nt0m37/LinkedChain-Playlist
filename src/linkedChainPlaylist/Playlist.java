package linkedChainPlaylist;

import java.util.Random;

/**
 * A named list of songs that can be played forward and backward and
 * shuffled.
 */
public class Playlist
{
    private final String name;
    private LinkedChain<Song> songs;
    private Random random;

    /**
     * Creates an empty playlist.
     *
     * @param name the playlist's name
     * @throws IllegalArgumentException if name is null or blank
     */
    public Playlist(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Playlist name must not be blank");
        }
        this.name = name.trim();
        songs = new LinkedChain<>();
        random = new Random();
    }


    /**
     * Gets the playlist's name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Adds a song to the end of the playlist.
     *
     * @param song the song to add
     * @throws IllegalArgumentException if song is null
     */
    public void addSong(Song song)
    {
        songs.addToEnd(song);
    }


    /**
     * Removes the first occurrence of a song. If it was the song playing,
     * the song before it becomes the current song.
     *
     * @param song the song to remove
     * @return true if the song was removed, or false if it was not in the
     *             playlist
     */
    public boolean removeSong(Song song)
    {
        return songs.remove(song);
    }


    /**
     * Gets the song at a position.
     *
     * @param index the zero-based position
     * @return the song at that position
     * @throws IndexOutOfBoundsException if index is negative or not less than
     *             size()
     */
    public Song getSong(int index)
    {
        return songs.get(index);
    }


    /**
     * Gets the number of songs.
     *
     * @return the number of songs in the playlist
     */
    public int size()
    {
        return songs.size();
    }


    /**
     * Checks whether the playlist has no songs.
     *
     * @return true if the playlist is empty
     */
    public boolean isEmpty()
    {
        return songs.isEmpty();
    }


    /**
     * Moves to the next song, wrapping from the last song back to the first.
     *
     * @return the next song, or null if the playlist is empty
     */
    public Song nextSong()
    {
        Song next = songs.moveNext();
        if (next == null)
        {
            songs.resetCursor();
            next = songs.moveNext();
        }
        return next;
    }


    /**
     * Moves back to the song before the current one.
     *
     * @return the previous song, or null if no song has been played yet or
     *             the current song is the first one
     */
    public Song previousSong()
    {
        return songs.movePrevious();
    }


    /**
     * Gets the song that is currently playing.
     *
     * @return the current song, or null if no song has been played yet
     */
    public Song getCurrentSong()
    {
        return songs.getCurrent();
    }


    /**
     * Puts the songs in a random order and resets playback to the start.
     * Does nothing to an empty playlist.
     */
    public void shuffle()
    {
        LinkedChain<Song> shuffled = new LinkedChain<>();
        int remaining = songs.size();

        while (remaining > 0)
        {
            Song randSong = songs.get(random.nextInt(remaining));
            songs.remove(randSong);
            shuffled.addToEnd(randSong);
            remaining--;
        }

        songs = shuffled;
    }


    /**
     * Two playlists are equal if their names match, ignoring case.
     *
     * @param obj the object to compare with
     * @return true if obj is a Playlist with the same name
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Playlist))
        {
            return false;
        }
        return name.equalsIgnoreCase(((Playlist)obj).name);
    }


    /**
     * Returns a hash code consistent with equals.
     *
     * @return a hash code based on the lowercase name
     */
    @Override
    public int hashCode()
    {
        return name.toLowerCase().hashCode();
    }


    /**
     * Returns the playlist as text, e.g. "Gym (3 songs)".
     *
     * @return the name and number of songs
     */
    @Override
    public String toString()
    {
        return name + " (" + songs.size() + " song"
            + (songs.size() == 1 ? "" : "s") + ")";
    }
}