package de.supercode.friends.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String vorname;
    private String nachname;
    private LocalDate geburtstag;
    private String handynummer;
    private String email;
    private String beruf;
    private double verdienst;
    private boolean istSelbständig;
    private boolean warSchonmalKunde;

    @JsonIgnoreProperties("friends")
    @ManyToOne
    @JoinColumn(name = "group_id")
    private FriendGroup group;
    // Constructors
    public Friend() {}

    public Friend(String vorname, String nachname, LocalDate geburtstag, String handynummer, String email, String beruf, double verdienst, boolean istSelbständig, boolean warSchonmalKunde) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.handynummer = handynummer;
        this.email = email;
        this.beruf = beruf;
        this.verdienst = verdienst;
        this.istSelbständig = istSelbständig;
        this.warSchonmalKunde = warSchonmalKunde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(LocalDate geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getHandynummer() {
        return handynummer;
    }

    public void setHandynummer(String handynummer) {
        this.handynummer = handynummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBeruf() {
        return beruf;
    }

    public void setBeruf(String beruf) {
        this.beruf = beruf;
    }

    public double getVerdienst() {
        return verdienst;
    }

    public void setVerdienst(double verdienst) {
        this.verdienst = verdienst;
    }

    public boolean isIstSelbständig() {
        return istSelbständig;
    }

    public void setIstSelbständig(boolean istSelbständig) {
        this.istSelbständig = istSelbständig;
    }

    public boolean isWarSchonmalKunde() {
        return warSchonmalKunde;
    }

    public void setWarSchonmalKunde(boolean warSchonmalKunde) {
        this.warSchonmalKunde = warSchonmalKunde;
    }


    public void setGroup(FriendGroup group) {
        this.group = group;
    }

    public FriendGroup getGroup() {
        return group;
    }
}
