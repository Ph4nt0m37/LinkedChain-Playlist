package linkedChainPlaylist;

import java.util.Objects;

/**
 * Stores information about a single song.
 * 
 * @author Paulo Korowajczuk Nader
 */
public class Song
{
    private String name;
    private String author;
    private long songLength;
    private String songFile;

    /**
     * Creates a song.
     *
     * @param name the song's name
     * @param author the song's author
     * @param songLength the song's length in seconds
     * @param songFile the path to the song's file, or null if there is none
     */
    public Song(String name, String author, long songLength, String songFile)
    {
        this.name = name;
        this.author = author;
        this.songLength = songLength;
        this.songFile = songFile;
    }


    /**
     * Gets the song's name.
     *
     * @return the song's name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Sets the song's name.
     *
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Gets the song's author.
     *
     * @return the song's author
     */
    public String getAuthor()
    {
        return author;
    }


    /**
     * Sets the song's author.
     *
     * @param author the new author
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }


    /**
     * Gets the song's length.
     *
     * @return the song's length in seconds
     */
    public long getSongLength()
    {
        return songLength;
    }


    /**
     * Sets the song's length.
     *
     * @param songLength the new length in seconds
     */
    public void setSongLength(long songLength)
    {
        this.songLength = songLength;
    }


    /**
     * Gets the path to the song's file.
     *
     * @return the file path, or null if there is none
     */
    public String getSongFile()
    {
        return songFile;
    }


    /**
     * Sets the path to the song's file.
     *
     * @param songFile the new file path
     */
    public void setSongFile(String songFile)
    {
        this.songFile = songFile;
    }


    /**
     * Two songs are equal if they have the same name, author, and length.
     * The file path is not compared.
     *
     * @param obj the object to compare with
     * @return true if obj is a Song with the same name, author, and length
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Song))
        {
            return false;
        }
        Song other = (Song)obj;
        return songLength == other.songLength
            && Objects.equals(name, other.name)
            && Objects.equals(author, other.author);
    }


    /**
     * Returns a hash code consistent with equals.
     *
     * @return a hash code based on name, author, and length
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(name, author, songLength);
    }


    /**
     * Returns the song as text, e.g. "Hey Jude by The Beatles (7:11)".
     *
     * @return the song's name, author, and length
     */
    @Override
    public String toString()
    {
        return name + " by " + author + " (" + (songLength / 60) + ":"
            + String.format("%02d", songLength % 60) + ")";
    }
}