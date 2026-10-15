package linkedChainPlaylist;

/**
 * Runs the playlist menu loop and dispatches each command to the Playlist.
 *
 * @author Syed Rishad
 */
public class Main
{
    private static final int ADD = 1;
    private static final int REMOVE = 2;
    private static final int VIEW = 3;
    private static final int PLAY_NEXT = 4;
    private static final int SHUFFLE = 5;
    private static final int QUIT = 6;
    private static final int MAX_SONG_SECONDS = 24 * 60 * 60;

    /** The playlist this program manages. */
    private final Playlist playlist;

    /**
     * Creates a Main with an empty playlist.
     */
    public Main()
    {
        playlist = new Playlist();
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
                "Choose an option (" + ADD + "-" + QUIT + "): ",
                ADD,
                QUIT,
                "Please enter a whole number from " + ADD + " to " + QUIT
                    + ".");

            switch (choice)
            {
                case ADD:
                    addSong();
                    break;
                case REMOVE:
                    removeSong();
                    break;
                case VIEW:
                    viewPlaylist();
                    break;
                case PLAY_NEXT:
                    playNextSong();
                    break;
                case SHUFFLE:
                    shufflePlaylist();
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
     * Prints the menu of commands and the current number of songs.
     */
    private void printMenu()
    {
        System.out.println();
        System.out.println("===== Playlist Menu (" + playlist.size()
            + " song" + (playlist.size() == 1 ? "" : "s") + ") =====");
        System.out.println(ADD + ". Add a song");
        System.out.println(REMOVE + ". Remove a song");
        System.out.println(VIEW + ". View playlist");
        System.out.println(PLAY_NEXT + ". Play next song");
        System.out.println(SHUFFLE + ". Shuffle playlist");
        System.out.println(QUIT + ". Quit");
    }


    /**
     * Asks for a song's name, author, and length, then adds it to the end of
     * the playlist.
     */
    private void addSong()
    {
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
        System.out.println("Added: " + formatSong(song));
    }


    /**
     * Shows the numbered playlist and removes the song the user picks.
     * Prints a message instead if the playlist is empty.
     */
    private void removeSong()
    {
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty. There is nothing to "
                + "remove.");
            return;
        }

        printSongs();
        int number = InputHandler.getUserIntInput(
            "Number of the song to remove (1-" + playlist.size() + "): ",
            1,
            playlist.size(),
            "Please enter a number from 1 to " + playlist.size() + ".");

        Song song = playlist.getSong(number - 1);
        if (playlist.removeSong(song))
        {
            System.out.println("Removed: " + formatSong(song));
        }
        else
        {
            System.out.println("That song was not found in the playlist.");
        }
    }


    /**
     * Prints every song in the playlist, or a message if it is empty.
     */
    private void viewPlaylist()
    {
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty.");
            return;
        }

        printSongs();
    }


    /**
     * Shows the next song in the playlist, or a message if it is empty.
     */
    private void playNextSong()
    {
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty. Add a song first.");
            return;
        }

        System.out.println("Now playing: " + formatSong(playlist.nextSong()));
    }


    /**
     * Shuffles the playlist and shows the new order, or prints a message if
     * it is empty.
     */
    private void shufflePlaylist()
    {
        if (playlist.isEmpty())
        {
            System.out.println("The playlist is empty. There is nothing to "
                + "shuffle.");
            return;
        }

        playlist.shuffle();
        System.out.println("Playlist shuffled. New order:");
        printSongs();
    }


    /**
     * Prints the playlist as a numbered list starting at 1.
     */
    private void printSongs()
    {
        for (int i = 0; i < playlist.size(); i++)
        {
            System.out.println((i + 1) + ". " + formatSong(playlist.getSong(i)));
        }
    }


    /**
     * Formats a song for display, e.g. "Hey Jude by The Beatles (7:11)".
     *
     * @param song the song to format
     * @return the song's name, author, and length as a String
     */
    private static String formatSong(Song song)
    {
        long seconds = song.getSongLength();
        return song.getName() + " by " + song.getAuthor() + " ("
            + (seconds / 60) + ":" + String.format("%02d", seconds % 60) + ")";
    }
}