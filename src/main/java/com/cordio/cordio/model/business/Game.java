package com.cordio.cordio.model.business;

import com.cordio.cordio.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table
public class Game extends AbstractEntity {

    @Column
    private Integer wonPlayerId;

    @Column
    private Integer lousePlayerId;

    @Column
    private String wonPlayerSets;

    @Column
    private String lousePlayerSets;

    public List<Integer> getWonPlayerSets() {
        List<String> listStrings = Arrays.asList(wonPlayerSets.split(","));
        List<Integer> listIntegers = new ArrayList<Integer>(listStrings.size());
        for(String current:listStrings){
            listIntegers.add(Integer.parseInt(current));
        }
        return listIntegers;
    }

    public String setWonPlayerSets(List<Integer> integers) {
        StringBuilder st = new StringBuilder();
        for(Integer current:integers){
            st.append(current);
            if (!current.equals(integers.get(integers.size() - 1))) {
                st.append(", ");
            }
        }
        return st.toString();
    }

    public List<Integer> getLousePlayerSets() {
        List<String> listStrings = Arrays.asList(wonPlayerSets.split(","));
        List<Integer> listIntegers = new ArrayList<Integer>(listStrings.size());
        for(String current:listStrings){
            listIntegers.add(Integer.parseInt(current));
        }
        return listIntegers;
    }

    public String setLousePlayerSets(List<Integer> integers) {
        StringBuilder st = new StringBuilder();
        for(Integer current:integers){
            st.append(current);
            if (!current.equals(integers.get(integers.size() - 1))) {
                st.append(", ");
            }
        }
        return st.toString();
    }
}
