package linkedChainPlaylist;
public class Song
{
    private String name;
    private String author;
    private long songLength;
    private String songFile;
    
    public Song(String name, String author, long songLength, String songFile) {
        this.name = name;
        this.author = author;
        this.songLength = songLength;
        this.songFile = songFile;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public long getSongLength()
    {
        return songLength;
    }
    
    public void setSongLength(long songLength)
    {
        this.songLength = songLength;
    }
    
    public String getSongFile()
    {
        return songFile;
    }
    
    public void setSongFile(String songFile)
    {
        this.songFile = songFile;
    }
    
}
