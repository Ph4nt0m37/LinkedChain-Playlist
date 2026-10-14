package linkedChainPlaylist;

import java.util.Random;

public class Playlist
{
    private LinkedChain<Song> playlist = new LinkedChain<>();
    
    public void addSong(Song song) {
        playlist.addToEnd(song);
    }
    
    public void removeSong(Song song) {
        playlist.remove(song);
    }
    
    public Song nextSong() {
        return playlist.get(0);
    }

    public void shuffle() {
        LinkedChain<Song> shuffledPlaylist = new LinkedChain<>();
        Random rand = new Random();
        int maxBound = playlist.size();
        
        while (maxBound>0) {
            Song randSong = playlist.get(rand.nextInt(maxBound));
            playlist.remove(randSong);
            shuffledPlaylist.addToEnd(randSong);
            maxBound--;
        }
        
        playlist = shuffledPlaylist;
    }
}
