package linkedChainPlaylist;

import java.util.Random;

public class Playlist
{
    private LinkedChain<Song> playlist = new LinkedChain<>();
    
    public void addSong(Song song) {
        playlist.addToEnd(song);
    }
    
    public boolean removeSong(Song song) {
        return playlist.remove(song);
    }

    public Song getSong(int index) {
        return playlist.get(index);
    }

    public int size() {
        return playlist.size();
    }

    public boolean isEmpty() {
        return playlist.isEmpty();
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