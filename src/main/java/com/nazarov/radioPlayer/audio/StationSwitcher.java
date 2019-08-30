package com.nazarov.radioPlayer.audio;

import com.nazarov.radioPlayer.playlist.UrlMaker;

public class StationSwitcher {

    AudioPlayer audioPlayer = new AudioPlayer();

    private Thread musicThread = new Thread();
    static int uniurlNumber = 0;
    private String s;
    private int numIndex;

    public void setS(String s) {
        this.s = s;
    }
    public void setNumIndex(int numIndex) {
        this.numIndex = numIndex;
    }

    public void logoHello() {

           audioPlayer.playR(UrlMaker.logoURL(5));
    }

    public void playRadio(int uniurlNumber) {

            audioPlayer.playR(UrlMaker.logoURL(numIndex));

            musicThread.stop();

            musicThread = new Thread(() -> {

            UrlMaker.setFile(s);

            audioPlayer.playR(UrlMaker.getUrl(uniurlNumber));
        });

        musicThread.start();
    }

    public void nextStation() {

        musicThread.stop();

        uniurlNumber++;

        int maxUrlNumber = UrlMaker.getPlaylistSize();

        if (uniurlNumber > maxUrlNumber) {

                uniurlNumber = 0;
        }

        playRadio(uniurlNumber);
    }
}