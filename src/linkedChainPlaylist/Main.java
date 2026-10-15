package linkedChainPlaylist;

/**
 * Runs the playlist menu loop and dispatches each command to the Library or
 * the selected Playlist.
 *
 * @author Syed Rishad
 */
public class Main
{
    private static final int ADD_SONG = 1;
    private static final int REMOVE_SONG = 2;
    private static final int VIEW_SONGS = 3;
    private static final int PLAY_NEXT = 4;
    private static final int PLAY_PREVIOUS = 5;
    private static final int SHUFFLE = 6;
    private static final int CREATE_PLAYLIST = 7;
    private static final int SWITCH_PLAYLIST = 8;
    private static final int DELETE_PLAYLIST = 9;
    private static final int QUIT = 10;
    private static final int MAX_SONG_SECONDS = 24 * 60 * 60;
    private static final String DEFAULT_PLAYLIST = "My Playlist";

    /** All of the user's playlists. */
    private final Library library;

    /**
     * Creates a Main whose library starts with one empty playlist.
     */
    public Main()
    {
        library = new Library();
        library.createPlaylist(DEFAULT_PLAYLIST);
    }


    /**
     * Starts the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        new Main().run();
    }


    /**
     * Shows the menu and runs the chosen command until the user quits.
     */
    public void run()
    {
        System.out.println("Welcome to the LinkedChain Playlist!");

        boolean running = true;
        while (running)
        {
            printMenu();
            int choice = InputHandler.getUserIntInput(
                "Choose an option (" + ADD_SONG + "-" + QUIT + "): ",
                ADD_SONG,
                QUIT,
                "Please enter a whole number from " + ADD_SONG + " to "
                    + QUIT + ".");

            switch (choice)
            {
                case ADD_SONG:
                    addSong();
                    break;
                case REMOVE_SONG:
                    removeSong();
                    break;
                case VIEW_SONGS:
                    viewSongs();
                    break;
                case PLAY_NEXT:
                    playNextSong();
                    break;
                case PLAY_PREVIOUS:
                    playPreviousSong();
                    break;
                case SHUFFLE:
                    shufflePlaylist();
                    break;
                case CREATE_PLAYLIST:
                    createPlaylist();
                    break;
                case SWITCH_PLAYLIST:
                    switchPlaylist();
                    break;
                case DELETE_PLAYLIST:
                    deletePlaylist();
                    break;
                case QUIT:
                    running = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("Goodbye!");
    }


    /**
     * Prints the menu of commands and the selected playlist.
     */
    private void printMenu()
    {
        Playlist current = library.getCurrentPlaylist();
        System.out.println();
        System.out.println("===== Current playlist: "
            + (current == null ? "none" : current.toString()) + " =====");
        System.out.println(ADD_SONG + ". Add a song");
        System.out.println(REMOVE_SONG + ". Remove a song");
        System.out.println(VIEW_SONGS + ". View songs");
        System.out.println(PLAY_NEXT + ". Play next song");
        System.out.println(PLAY_PREVIOUS + ". Play previous song");
        System.out.println(SHUFFLE + ". Shuffle playlist");
        System.out.println(CREATE_PLAYLIST + ". Create a playlist");
        System.out.println(SWITCH_PLAYLIST + ". Switch playlist");
        System.out.println(DELETE_PLAYLIST + ". Delete a playlist");
        System.out.println(QUIT + ". Quit");
    }


    /**
     * Gets the selected playlist, printing a message if there is none.
     *
     * @return the selected playlist, or null if the library is empty
     */
    private Playlist requirePlaylist()
    {
        Playlist current = library.getCurrentPlaylist();
        if (current == null)
        {
            System.out.println("There is no playlist. Create one first.");
        }
        return current;
    }


    /**
     * Asks for a song's name, author, and length, then adds it to the end of
     * the selected playlist.
     */
    private void addSong()
    {
        Playlist playlist = requirePlaylist();
        if (playlist == null)
        {
            return;
        }

        String name = InputHandler.getNonBlankInput(
            "Song name: ",
            "Song name cannot be blank.");
        String author = InputHandler.getNonBlankInput(
            "Song author: ",
            "Song author cannot be blank.");
        int length = InputHandler.getUserIntInput(
            "Song length in seconds: ",
            1,
            MAX_SONG_SECONDS,
            "Song length must be a whole number from 1 to "
                + MAX_SONG_SECONDS + ".");

        Song song = new Song(name, author, length, null);
        playlist.addSong(song);
        System.out.println("Added: " + song);
    }


    /**
     * Shows the numbered songs and removes the one the user picks.
     */
    private void removeSong()
    {
        Playlist playlist = requirePlaylist();
        if (playlist == null)
        {
            return;
        }
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty. There is nothing to "
                + "remove.");
            return;
        }

        printSongs(playlist);
        int number = InputHandler.getUserIntInput(
            "Number of the song to remove (1-" + playlist.size() + "): ",
            1,
            playlist.size(),
            "Please enter a number from 1 to " + playlist.size() + ".");

        Song song = playlist.getSong(number - 1);
        if (playlist.removeSong(song))
        {
            System.out.println("Removed: " + song);
        }
        else
        {
            System.out.println("That song was not found in the playlist.");
        }
    }


    /**
     * Prints the selected playlist's songs and the song that is playing.
     */
    private void viewSongs()
    {
        Playlist playlist = requirePlaylist();
        if (playlist == null)
        {
            return;
        }
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty.");
            return;
        }

        printSongs(playlist);
        Song current = playlist.getCurrentSong();
        if (current != null)
        {
            System.out.println("Now playing: " + current);
        }
    }


    /**
     * Plays the next song in the selected playlist.
     */
    private void playNextSong()
    {
        Playlist playlist = requirePlaylist();
        if (playlist == null)
        {
            return;
        }

        Song song = playlist.nextSong();
        if (song == null)
        {
            System.out.println("The playlist is empty. Add a song first.");
        }
        else
        {
            System.out.println("Now playing: " + song);
        }
    }


    /**
     * Plays the song before the current one in the selected playlist.
     */
    private void playPreviousSong()
    {
        Playlist playlist = requirePlaylist();
        if (playlist == null)
        {
            return;
        }

        Song song = playlist.previousSong();
        if (song == null)
        {
            System.out.println("There is no previous song.");
        }
        else
        {
            System.out.println("Now playing: " + song);
        }
    }


    /**
     * Shuffles the selected playlist and shows the new order.
     */
    private void shufflePlaylist()
    {
        Playlist playlist = requirePlaylist();
        if (playlist == null)
        {
            return;
        }
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty. There is nothing to "
                + "shuffle.");
            return;
        }

        playlist.shuffle();
        System.out.println("Playlist shuffled. New order:");
        printSongs(playlist);
    }


    /**
     * Asks for a name and creates a new playlist, which becomes selected.
     */
    private void createPlaylist()
    {
        String name = InputHandler.getNonBlankInput(
            "Playlist name: ",
            "Playlist name cannot be blank.");

        if (library.createPlaylist(name))
        {
            System.out.println("Created and switched to: " + name);
        }
        else
        {
            System.out.println("A playlist named \"" + name
                + "\" already exists.");
        }
    }


    /**
     * Shows the numbered playlists and selects the one the user picks.
     */
    private void switchPlaylist()
    {
        if (library.isEmpty())
        {
            System.out.println("There are no playlists. Create one first.");
            return;
        }

        Playlist playlist = choosePlaylist("switch to");
        library.switchTo(playlist);
        System.out.println("Switched to: " + playlist);
    }


    /**
     * Shows the numbered playlists and deletes the one the user picks after
     * asking for confirmation.
     */
    private void deletePlaylist()
    {
        if (library.isEmpty())
        {
            System.out.println("There are no playlists to delete.");
            return;
        }

        Playlist playlist = choosePlaylist("delete");
        boolean confirmed = InputHandler.getUserBoolInput(
            "Delete " + playlist + "? (y/n): ",
            "Please enter y or n.");

        if (!confirmed)
        {
            System.out.println("Nothing was deleted.");
        }
        else if (library.deletePlaylist(playlist))
        {
            System.out.println("Deleted: " + playlist.getName());
        }
        else
        {
            System.out.println("That playlist was not found.");
        }
    }


    /**
     * Prints the numbered playlists and asks the user to pick one.
     *
     * @param action what will be done with the playlist, used in the prompt
     * @return the playlist the user picked
     */
    private Playlist choosePlaylist(String action)
    {
        for (int i = 0; i < library.size(); i++)
        {
            System.out.println((i + 1) + ". " + library.getPlaylist(i));
        }
        int number = InputHandler.getUserIntInput(
            "Number of the playlist to " + action + " (1-" + library.size()
                + "): ",
            1,
            library.size(),
            "Please enter a number from 1 to " + library.size() + ".");
        return library.getPlaylist(number - 1);
    }


    /**
     * Prints a playlist's songs as a numbered list starting at 1.
     *
     * @param playlist the playlist to print
     */
    private void printSongs(Playlist playlist)
    {
        for (int i = 0; i < playlist.size(); i++)
        {
            System.out.println((i + 1) + ". " + playlist.getSong(i));
        }
    }
}