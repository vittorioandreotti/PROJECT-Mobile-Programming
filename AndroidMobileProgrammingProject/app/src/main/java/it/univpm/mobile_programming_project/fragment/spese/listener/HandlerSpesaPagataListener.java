package it.univpm.mobile_programming_project.fragment.spese.listener;


import java.util.ArrayList;
import java.util.List;

import it.univpm.mobile_programming_project.fragment.spese.recycler.InterfaceSpeseAdapter;
import it.univpm.mobile_programming_project.models.Spesa;

public class HandlerSpesaPagataListener {

    private List<OnSpesaPagataListener> listeners = new ArrayList<OnSpesaPagataListener>();

    public void addListener(OnSpesaPagataListener toAdd) {
        listeners.add(toAdd);
    }

    public void pagaSpesa(Spesa spesaPagata) {
        // Notify everybody that may be interested.
        for (OnSpesaPagataListener l : listeners)
            l.notifySpesaPagata(spesaPagata);
    }
}