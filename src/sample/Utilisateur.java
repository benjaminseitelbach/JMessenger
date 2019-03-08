package sample;

public class Utilisateur {
    private String pseudo;
    private String mdp;

    public Utilisateur () {

    }

    public Utilisateur (String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public String getPseudo () {
        return this.pseudo;
    }

    public String getMdp () {
        return this.mdp;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMdp (String mdp) {
        this.mdp = mdp;
    }
}
