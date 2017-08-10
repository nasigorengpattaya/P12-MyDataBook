package sg.edu.rp.c346.p12_mydatabook;

import java.io.Serializable;

/**
 * Created by 15017420 on 10/8/2017.
 */

public class Bio implements Serializable{

    String bio;

    public Bio() {
    }

    public Bio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
