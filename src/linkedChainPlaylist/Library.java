package linkedChainPlaylist;

/**
 * Holds all of the user's playlists and tracks which one is selected.
 */
public class Library
{
    private LinkedChain<Playlist> playlists;
    private Playlist currentPlaylist;

    /**
     * Creates an empty library with no playlist selected.
     */
    public Library()
    {
        playlists = new LinkedChain<>();
        currentPlaylist = null;
    }


    /**
     * Creates a new playlist and selects it.
     *
     * @param name the new playlist's name
     * @return true if it was created, or false if a playlist with that name
     *             (ignoring case) already exists
     * @throws IllegalArgumentException if name is null or blank
     */
    public boolean createPlaylist(String name)
    {
        Playlist playlist = new Playlist(name);
        if (playlists.contains(playlist))
        {
            return false;
        }
        playlists.addToEnd(playlist);
        currentPlaylist = playlist;
        return true;
    }


    /**
     * Deletes a playlist. If it was selected, the first remaining playlist is
     * selected instead, or none if the library is now empty.
     *
     * @param playlist the playlist to delete
     * @return true if it was deleted, or false if it was not in the library
     */
    public boolean deletePlaylist(Playlist playlist)
    {
        if (!playlists.remove(playlist))
        {
            return false;
        }
        if (playlist.equals(currentPlaylist))
        {
            currentPlaylist = playlists.isEmpty() ? null : playlists.get(0);
        }
        return true;
    }


    /**
     * Selects a playlist.
     *
     * @param playlist the playlist to select
     * @return true if it was selected, or false if it is not in the library
     */
    public boolean switchTo(Playlist playlist)
    {
        if (!playlists.contains(playlist))
        {
            return false;
        }
        currentPlaylist = playlist;
        return true;
    }


    /**
     * Gets the selected playlist.
     *
     * @return the selected playlist, or null if the library is empty
     */
    public Playlist getCurrentPlaylist()
    {
        return currentPlaylist;
    }


    /**
     * Gets the playlist at a position.
     *
     * @param index the zero-based position
     * @return the playlist at that position
     * @throws IndexOutOfBoundsException if index is negative or not less than
     *             size()
     */
    public Playlist getPlaylist(int index)
    {
        return playlists.get(index);
    }


    /**
     * Gets the number of playlists.
     *
     * @return the number of playlists in the library
     */
    public int size()
    {
        return playlists.size();
    }


    /**
     * Checks whether the library has no playlists.
     *
     * @return true if the library is empty
     */
    public boolean isEmpty()
    {
        return playlists.isEmpty();
    }
}