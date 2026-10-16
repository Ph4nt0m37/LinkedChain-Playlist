package linkedChainPlaylist;

import student.TestCase;

/**
 * Tests the Library class.
 */
public class LibraryTest extends TestCase
{
    private Library library;
    private Playlist playlist1;
    private Playlist playlist2;
    private Playlist playlist3;


    /**
     * Sets up a library and playlists before each test.
     */
    public void setUp()
    {
        library = new Library();
        playlist1 = new Playlist("Favorites");
        playlist2 = new Playlist("Workout");
        playlist3 = new Playlist("Study");
    }


    /**
     * Tests that a new library is empty.
     */
    public void testConstructor()
    {
        assertTrue(library.isEmpty());
        assertEquals(0, library.size());
        assertNull(library.getCurrentPlaylist());
    }


    /**
     * Tests creating a playlist.
     */
    public void testCreatePlaylist()
    {
        assertTrue(library.createPlaylist("Favorites"));

        assertEquals(1, library.size());
        assertFalse(library.isEmpty());
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests creating multiple playlists.
     */
    public void testCreateMultiplePlaylists()
    {
        assertTrue(library.createPlaylist("Favorites"));
        assertTrue(library.createPlaylist("Workout"));
        assertTrue(library.createPlaylist("Study"));

        assertEquals(3, library.size());
        assertEquals("Study",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests that creating a duplicate playlist name fails.
     */
    public void testCreateDuplicatePlaylist()
    {
        assertTrue(library.createPlaylist("Favorites"));

        assertFalse(library.createPlaylist("Favorites"));

        assertEquals(1, library.size());
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests that duplicate playlist names are case-insensitive.
     */
    public void testCreateDuplicatePlaylistDifferentCase()
    {
        assertTrue(library.createPlaylist("Favorites"));

        assertFalse(library.createPlaylist("fAVORITES"));

        assertEquals(1, library.size());
    }


    /**
     * Tests creating a playlist with a null name.
     */
    public void testCreatePlaylistNullName()
    {
        try
        {
            library.createPlaylist(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(library.isEmpty());
        }
    }


    /**
     * Tests creating a playlist with a blank name.
     */
    public void testCreatePlaylistBlankName()
    {
        try
        {
            library.createPlaylist("   ");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(library.isEmpty());
        }
    }


    /**
     * Tests getting a playlist by index.
     */
    public void testGetPlaylist()
    {
        library.createPlaylist("Favorites");
        library.createPlaylist("Workout");

        assertEquals("Favorites", library.getPlaylist(0).getName());
        assertEquals("Workout", library.getPlaylist(1).getName());
    }


    /**
     * Tests getting a playlist with a negative index.
     */
    public void testGetPlaylistNegativeIndex()
    {
        try
        {
            library.getPlaylist(-1);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Tests getting a playlist with an index beyond the end.
     */
    public void testGetPlaylistIndexTooLarge()
    {
        library.createPlaylist("Favorites");

        try
        {
            library.getPlaylist(1);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Tests switching to a playlist in the library.
     */
    public void testSwitchTo()
    {
        library.createPlaylist("Favorites");
        library.createPlaylist("Workout");

        assertTrue(library.switchTo(playlist1));
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests switching to a playlist that is not in the library.
     */
    public void testSwitchToPlaylistNotInLibrary()
    {
        library.createPlaylist("Favorites");

        Playlist externalPlaylist = new Playlist("External");

        assertFalse(library.switchTo(externalPlaylist));
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests deleting a playlist that is not in the library.
     */
    public void testDeletePlaylistNotInLibrary()
    {
        library.createPlaylist("Favorites");

        assertFalse(library.deletePlaylist(playlist2));
        assertEquals(1, library.size());
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests deleting a playlist that is not currently selected.
     */
    public void testDeleteUnselectedPlaylist()
    {
        library.createPlaylist("Favorites");
        library.createPlaylist("Workout");

        Playlist selected = library.getCurrentPlaylist();

        assertTrue(library.deletePlaylist(playlist1));

        assertEquals(1, library.size());
        assertEquals(selected, library.getCurrentPlaylist());
    }


    /**
     * Tests deleting the selected playlist.
     */
    public void testDeleteSelectedPlaylist()
    {
        library.createPlaylist("Favorites");
        library.createPlaylist("Workout");

        Playlist selected = library.getCurrentPlaylist();

        assertTrue(library.deletePlaylist(selected));

        assertEquals(1, library.size());
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests deleting the only playlist.
     */
    public void testDeleteOnlyPlaylist()
    {
        library.createPlaylist("Favorites");

        Playlist selected = library.getCurrentPlaylist();

        assertTrue(library.deletePlaylist(selected));

        assertTrue(library.isEmpty());
        assertEquals(0, library.size());
        assertNull(library.getCurrentPlaylist());
    }


    /**
     * Tests deleting the selected playlist when multiple playlists remain.
     */
    public void testDeleteSelectedPlaylistWithMultipleRemaining()
    {
        library.createPlaylist("Favorites");
        library.createPlaylist("Workout");
        library.createPlaylist("Study");

        Playlist selected = library.getCurrentPlaylist();

        assertTrue(library.deletePlaylist(selected));

        assertEquals(2, library.size());
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }


    /**
     * Tests deleting a playlist using an equal playlist object.
     */
    public void testDeleteEqualPlaylist()
    {
        library.createPlaylist("Favorites");

        Playlist equivalentPlaylist = new Playlist("Favorites");

        assertTrue(library.deletePlaylist(equivalentPlaylist));
        assertTrue(library.isEmpty());
        assertNull(library.getCurrentPlaylist());
    }


    /**
     * Tests the size method.
     */
    public void testSize()
    {
        assertEquals(0, library.size());

        library.createPlaylist("Favorites");
        assertEquals(1, library.size());

        library.createPlaylist("Workout");
        assertEquals(2, library.size());

        library.deletePlaylist(library.getPlaylist(0));
        assertEquals(1, library.size());
    }


    /**
     * Tests the isEmpty method.
     */
    public void testIsEmpty()
    {
        assertTrue(library.isEmpty());

        library.createPlaylist("Favorites");
        assertFalse(library.isEmpty());

        library.deletePlaylist(library.getCurrentPlaylist());
        assertTrue(library.isEmpty());
    }


    /**
     * Tests getting the current playlist after creation and switching.
     */
    public void testGetCurrentPlaylist()
    {
        library.createPlaylist("Favorites");
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());

        library.createPlaylist("Workout");
        assertEquals("Workout",
            library.getCurrentPlaylist().getName());

        library.switchTo(library.getPlaylist(0));
        assertEquals("Favorites",
            library.getCurrentPlaylist().getName());
    }
}