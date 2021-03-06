/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat3.jpademo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author jobe
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_id;
   
    private String name;
    private int year;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    
    @OneToMany(mappedBy = "person",  cascade = CascadeType.PERSIST)
    private List<Fee> fees;
    
    @ManyToMany(mappedBy = "persons",  cascade = CascadeType.PERSIST)
    private List<SwimStyle> styles;
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<LinkTeamPerson> teams;
    

    public List<SwimStyle> getStyles() {
        return styles;
    }
    
    public List<Fee> getFees() {
        return fees;
    }

    public void addFee(Fee fee) {
        this.fees.add(fee);
        if (fee != null){
            fee.setPerson(this);
        }
    }
    
    public void addSwimStyle(SwimStyle style){
        if (style != null){
            this.styles.add(style);
            style.getPersons().add(this);
        }
    }
    
    public void removeSwimStyle(SwimStyle swimStyle){
        if (swimStyle != null){
            styles.remove(swimStyle);
            swimStyle.getPersons().remove(this);    
        }
    
    }
   
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if(address != null){
            address.setPerson(this);
            
        }
    }
    
    public Person() {
    }

    public Long getP_id() {
        return p_id;
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
        this.fees = new ArrayList<>();
        this.styles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person(String name, int year, Address address, List<Fee> fees, List<SwimStyle> styles, List<LinkTeamPerson> teams) {
        this.name = name;
        this.year = year;
        this.address = address;
        this.fees = fees;
        this.styles = styles;
        this.teams = teams;
    }

    public List<LinkTeamPerson> getTeams() {
        return teams;
    }

    public void setTeams(List<LinkTeamPerson> teams) {
        this.teams = teams;
    }
    
    
    
}
